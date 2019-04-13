import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//-----------------
//砲台
//-----------------
class Houdai extends JLabel implements Runnable, Observer{

	//フィールド---------------------------------
	private int x;	//x座標
	private int y;	//y座標
	private int w;	//横幅
	private int h;	//高さ
	private boolean life;		//生存状態
	private boolean right;	//右キ
	private boolean left;		//左キー
	private boolean space;	//スペースキー
  private PlayClip pc;

	private ImageIcon img1;
	private ImageIcon img0;

	private GamePanel gp;
	private HoudaiMissile hm;


	//コンストラクタ----------------------------
	Houdai(GamePanel gp, HoudaiMissile hm){
		//値を挿入
		x = Screen.WIDTH/2;
		y = Screen.HOUDAI_Y;
		w = Screen.HOUDAI_W;
		h = Screen.HOUDAI_H;
		life = true;
		right = false;
		left = false;
		space = false;
		pc = null;
		this.gp = gp;
		this.hm = hm;
		img1 = new ImageIcon("gazou/houdai.gif");
		img0 = new ImageIcon("gazou/taihaHd.gif");
    pc = new PlayClip("oto/死んだときの音.wav");

		//設定
		setBounds(x,y,w,h);
		setIcon(img1);
	}


	//メソッド---------------------------------
	public void run(){
		while(gp.getLife() == true){
			if(life){
				if(right){
					if(x < Screen.RIGHT - w)
						x += 1;
				}
				if(left){
					if(x > Screen.LEFT)
						x -= 1;
				}
				if(space){
					//ミサイル、砲台の先から上にあがれ
					if(hm.getLife()==false){
						hm.setPos(this.x);
						hm.setLife(true);
						hm.pc.play();
					}
				}
			}else{
	      pc.play();
				try{Thread.sleep(500);}catch(Exception e){}
	      pc.stop();
	      pc.reset();
				setIcon(img1);
				life = true;
			}
			setLocation(x, y);
			try{Thread.sleep(3);}catch(Exception e){}
		}
	}

	public void update(Subject s){
		int mx = ((InvaderMissile)s).getX();
		int my = ((InvaderMissile)s).getY()+Screen.IM_H;
		//System.out.printf("【UFO】(%d,%d)\n",mx,my);
		//HoudaiMissile命中
		if(x <= mx+Screen.IM_W && (x + w) >= mx && y < my && (y + h) >= my){
			if(life = true){
				setIcon(img0);
				gp.gm.setStock(gp.gm.getStock()-1);
				life = false;
				((InvaderMissile)s).hit();
				System.out.println("砲台に命中！残りライフ：" + gp.gm.getStock());
			}
		}
	}

	//移動メソッド
	public void move(int k, boolean status){
		if(k == KeyEvent.VK_RIGHT){
			right = status;
		}
		if(k == KeyEvent.VK_LEFT){
			left = status;
		}
	}
	//発射メソッド
	public void hasha(boolean status){
		space = status;
	}

}
