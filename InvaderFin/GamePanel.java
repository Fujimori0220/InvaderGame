import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

  //�t�B�[���h----------------------------
 	private boolean life;	//�p�l�����\������Ă��邩

	public GameMain gm;
	private Houdai hd;
	private HoudaiMissile hm;
	private InvaderPanel ip;
	private InvaderMissile[][] im = new InvaderMissile[Screen.IP_WNUM][Screen.IP_HNUM];
	private Ufo ufo;
	private WallPanel wp;
	private JLabel scl;	//�X�R�A���x��
	private JLabel stl;
	private JLabel startl;
	private JLabel cl;
  private PlayClip pc = null;

	//�摜�ǂݍ���
  private Image bg = Toolkit.
    getDefaultToolkit().
    getImage("gazou/backGp.jpg");


  //�R���X�g���N�^------------------------
  GamePanel(GameMain gm){
		//�l��}��
		life = true;
		this.gm = gm;

		//���x����ݒu
		scl = new JLabel("SCORE : " + String.valueOf(gm.getScore()));
		scl.setBounds(Screen.LEFT, Screen.TOP, 200, 50);
		scl.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
		//scl.setHorizontalAlignment(JLabel.RIGHT);
		scl.setForeground(Color.white);
		add(scl);

		stl = new JLabel("STOCK �~ " + String.valueOf(gm.getStock()-1));
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

		//�~�T�C���C���X�^���X�쐬
		hm = new HoudaiMissile(this);
		add(hm);
		//�C��C���X�^���X����
		hd = new Houdai(this, hm);
		add(hd);
		//Ufo�C���X�^���X�쐬
		ufo = new Ufo(this);
		add(ufo);
		//�ǃC���X�^���X�쐬
		wp = new WallPanel();
		add(wp);
		//�C���x�[�_�[�~�T�C���쐬�AObserver�ւ̓o�^
		for(int i = 0; i < Screen.IP_WNUM; i++){
			for(int j = 0; j < Screen.IP_HNUM; j++){
				im[i][j] = new InvaderMissile(this);
				add(im[i][j]);
				im[i][j].addObserver(hd);
				im[i][j].addObserver(wp);
			}
		}
    pc = new PlayClip("oto/lup.wav");
		//�C���x�[�_�[�p�l���C���X�^���X�쐬
		ip = new InvaderPanel(this, im);
		add(ip);
		//Observer�ւ̓o�^
		hm.addObserver(ufo);
		hm.addObserver(ip);
		hm.addObserver(wp);

		//�X���b�h�X�^�[�g
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

		//�ݒ�
    setLayout(null);
    setBounds(0, 0, Screen.WIDTH, Screen.HEIGHT);
    gm.addKeyListener(this);
    setVisible(true);
    System.out.println("���x���F" + gm.getLevel());

  }


  //���\�b�h----------------------------
	//�ĕ`�悳�ꂽ�Ƃ��ɌĂ΂�郁�\�b�h
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
			stl.setText("STOCK �~ " + String.valueOf(gm.getStock()-1));
			//�c��c�@�m�F
			if(gm.getStock() <= 0){
				life = false;
	      gm.removeKeyListener(this);
				gm.drawEnd(this);
			}
			//�N���A�m�F
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

  //�L�[�������ꂽ
  public void keyPressed(KeyEvent e){
    int kc = e.getKeyCode();
    //�E�L�[
    if(kc == e.VK_RIGHT){
			hd.move(KeyEvent.VK_RIGHT, true);
    }
    //���L�[
    if(kc == e.VK_LEFT){
			hd.move(KeyEvent.VK_LEFT, true);
    }
    //�X�y�[�X�L�[
    if(kc == e.VK_SPACE){
			hd.hasha(true);
    }
    //�G���^�[�L�[
    if(kc == e.VK_ENTER){
			life = false;
			gm.removeKeyListener(this);
	    gm.drawEnd(this);
    }
  }
  //�L�[�������ꂽ
  public void keyReleased(KeyEvent e){
    int kc = e.getKeyCode();
    //�E�L�[
    if(kc == e.VK_RIGHT){
			hd.move(KeyEvent.VK_RIGHT, false);
    }
    //���L�[
    if(kc == e.VK_LEFT){
			hd.move(KeyEvent.VK_LEFT, false);
    }
    //�X�y�[�X�L�[
    if(kc == e.VK_SPACE){
			hd.hasha(false);
    }
  }
  public void keyTyped(KeyEvent e){
    //�����Ȃ�
  }

	//�N���A�����X�^�[�g
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
    System.out.println("���x���F" + gm.getLevel());
	}

  //set,get���\�b�h
  public boolean getLife(){
    return life;
  }
}
