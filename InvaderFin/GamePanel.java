import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

  //フィールド----------------------------
 	private boolean life;	//パネルが表示されているか

	public GameMain gm;
	private Houdai hd;
	private HoudaiMissile hm;
	private InvaderPanel ip;
	private InvaderMissile[][] im = new InvaderMissile[Screen.IP_WNUM][Screen.IP_HNUM];
	private Ufo ufo;
	private WallPanel wp;
	private JLabel scl;	//スコアラベル
	private JLabel stl;
	private JLabel startl;
	private JLabel cl;
  private PlayClip pc = null;

	//画像読み込み
  private Image bg = Toolkit.
    getDefaultToolkit().
    getImage("gazou/backGp.jpg");


  //コンストラクタ------------------------
  GamePanel(GameMain gm){
		//値を挿入
		life = true;
		this.gm = gm;

		//ラベルを設置
		scl = new JLabel("SCORE : " + String.valueOf(gm.getScore()));
		scl.setBounds(Screen.LEFT, Screen.TOP, 200, 50);
		scl.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		//scl.setHorizontalAlignment(JLabel.RIGHT);
		scl.setForeground(Color.white);
		add(scl);

		stl = new JLabel("STOCK × " + String.valueOf(gm.getStock()-1));
		stl.setBounds(Screen.RIGHT-200, Screen.TOP, 200, 50);
		stl.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		scl.setHorizontalAlignment(JLabel.RIGHT);
		stl.setForeground(Color.white);
		add(stl);

    startl = new JLabel();
    startl.setLayout(null);
		startl.setBounds(Screen.WIDTH/2-320 ,200 ,640 ,206);
    startl.setIcon(new ImageIcon("gazou/crear.gif"));
    add(startl);

    cl = new JLabel();
		cl.setBounds(Screen.WIDTH/2-250 ,250 ,500 ,200);
		cl.setFont(new Font("Palatino Linotype", Font.BOLD, 80));
	  cl.setHorizontalAlignment(JLabel.CENTER);
		cl.setForeground(Color.red);
		cl.setVisible(false);
    add(cl);

		//ミサイルインスタンス作成
		hm = new HoudaiMissile(this);
		add(hm);
		//砲台インスタンス生成
		hd = new Houdai(this, hm);
		add(hd);
		//Ufoインスタンス作成
		ufo = new Ufo(this);
		add(ufo);
		//壁インスタンス作成
		wp = new WallPanel();
		add(wp);
		//インベーダーミサイル作成、Observerへの登録
		for(int i = 0; i < Screen.IP_WNUM; i++){
			for(int j = 0; j < Screen.IP_HNUM; j++){
				im[i][j] = new InvaderMissile(this);
				add(im[i][j]);
				im[i][j].addObserver(hd);
				im[i][j].addObserver(wp);
			}
		}
    pc = new PlayClip("oto/lup.wav");
		//インベーダーパネルインスタンス作成
		ip = new InvaderPanel(this, im);
		add(ip);
		//Observerへの登録
		hm.addObserver(ufo);
		hm.addObserver(ip);
		hm.addObserver(wp);

		//スレッドスタート
		Thread tgp = new Thread(this);
		tgp.start();
		Thread tHm = new Thread(hm);
		tHm.start();
		for(int i = 0; i < Screen.IP_WNUM; i++){
			for(int j = 0; j < Screen.IP_HNUM; j++){
				Thread tim = new Thread(ip.im[i][j]);
				tim.start();
			}
		}

		//設定
    setLayout(null);
    setBounds(0, 0, Screen.WIDTH, Screen.HEIGHT);
    gm.addKeyListener(this);
    setVisible(true);
    System.out.println("レベル：" + gm.getLevel());

  }


  //メソッド----------------------------
	//再描画されたときに呼ばれるメソッド
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, Screen.WIDTH, Screen.HEIGHT, this);
		repaint();
	}

	public void run(){
    try{Thread.sleep(3000);}catch(Exception e){}
		startl.setVisible(false);
		Thread tHd = new Thread(hd);
		tHd.start();
		Thread tUfo = new Thread(ufo);
		tUfo.start();
		Thread tip = new Thread(ip);
		tip.start();

		while(life == true){
			scl.setText("SCORE : " + String.valueOf(gm.getScore()));
			stl.setText("STOCK × " + String.valueOf(gm.getStock()-1));
			//残り残機確認
			if(gm.getStock() <= 0){
				life = false;
	      gm.removeKeyListener(this);
				gm.drawEnd(this);
			}
			//クリア確認
			int check = 0;
      for(int i = 0; i < ip.ivNum.length; i++){
				check += ip.ivNum[i];
			}
			if(check == 0){
				//restert();
				gm.setLevel(gm.getLevel()+1);
				//life = false;
	      //gm.removeKeyListener(this);
				//gm.drawGame(this);
				restert();
			}
	    try{Thread.sleep(10);}catch(Exception e){}
		}
	}

  //キーが押された
  public void keyPressed(KeyEvent e){
    int kc = e.getKeyCode();
    //右キー
    if(kc == e.VK_RIGHT){
			hd.move(KeyEvent.VK_RIGHT, true);
    }
    //左キー
    if(kc == e.VK_LEFT){
			hd.move(KeyEvent.VK_LEFT, true);
    }
    //スペースキー
    if(kc == e.VK_SPACE){
			hd.hasha(true);
    }
    //エンターキー
    if(kc == e.VK_ENTER){
			life = false;
			gm.removeKeyListener(this);
	    gm.drawEnd(this);
    }
  }
  //キーが放された
  public void keyReleased(KeyEvent e){
    int kc = e.getKeyCode();
    //右キー
    if(kc == e.VK_RIGHT){
			hd.move(KeyEvent.VK_RIGHT, false);
    }
    //左キー
    if(kc == e.VK_LEFT){
			hd.move(KeyEvent.VK_LEFT, false);
    }
    //スペースキー
    if(kc == e.VK_SPACE){
			hd.hasha(false);
    }
  }
  public void keyTyped(KeyEvent e){
    //処理なし
  }

	//クリア時リスタート
	public void restert(){
	try{Thread.sleep(500);}catch(Exception e){}
		ip.setLife(false);
		ip.setVisible(false);
		remove(ip);
		ip = new InvaderPanel(this, im);
		add(ip);
		hm.addObserver(ip);
		wp.reset();
		cl.setText("LEVEL " + String.valueOf(gm.getLevel()));
		cl.setVisible(true);
		pc.play();
		try{Thread.sleep(2000);}catch(Exception e){}
    pc.stop();
    pc.reset();
		cl.setVisible(false);
		Thread tip = new Thread(ip);
		tip.start();
    System.out.println("レベル：" + gm.getLevel());
	}

  //set,getメソッド
  public boolean getLife(){
    return life;
  }
}
