import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class HoudaiMissile extends Subject implements Runnable{

	//フィールド-----------------------------
	private int x = -Screen.HM_W;
	private int y = 0;//Screen.HM_Y;
	private int w = Screen.HM_W;
	private int h = Screen.HM_H;
	private boolean life;

	private GamePanel gp;
	public PlayClip pc;


	//コンストラクタ-------------------------
	HoudaiMissile(GamePanel gp){
		life = false;
		this.gp = gp;
		pc = new PlayClip("oto/hasya.wav");

		//座標、サイズ
		setBounds(x,y,w,h);

		//画像貼りつけ
		setIcon(new ImageIcon("gazou/houdaiMissile.png"));


	}


	//メソッド--------------------------------
	//移動メソッド
	public void run(){
		while(gp.getLife() == true){
			try{Thread.sleep(1);}catch(Exception e){}

			if(Screen.TOP-h >= y){
				life = false;
				pc.stop();
				pc.reset();
			}
			//System.out.println("ミサイル上昇中");
			if(life == true){
				y -= 1;
				setLocation(x,y);
				notifyObservers();
			}

		}
	}

	//ミサイル命中時、画面外に飛ばす
	public void hit(){
		y = Screen.TOP-h;
		life = false;
		pc.stop();
		pc.reset();
		setLocation(x,y);
	}

	//ミサイルを発射位置にセット
	public void setPos(int x){	//砲台の今のｘ座標
		this.x = x+(Screen.HOUDAI_W-w)/2;
		this.y = Screen.HM_Y;
	}

	//Lifeのsetとget
	public void setLife(boolean l){
		life = l;
	}
	public boolean getLife(){
		return life;
	}

}
