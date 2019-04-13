import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Random;

//-----------------
//砲台ミサイル
//-----------------
class InvaderMissile extends Subject implements Runnable{

  //フィールド--------------------------
	private int x = 0;
	private int y = Screen.BOTTOM;
	private int w = Screen.IM_W;
	private int h = Screen.IM_H;
	private boolean life;
	private int dis;
	private boolean ivLife;

	private GamePanel gp;

  Random r = new Random();


	//コンストラクタ----------------------
	InvaderMissile(GamePanel gp){
		//値を挿入
		life = true;
		ivLife = true;
		this.gp = gp;
		if(gp.gm.getLevel() <= 8){
			dis = 11-gp.gm.getLevel();
		}
		else{
			dis = 3;
		}

		//設定
		setBounds(x,y,w,h);

		//画像貼りつけ
		setIcon(new ImageIcon("gazou/invaderMissile.png"));

	}


	//メソッド-----------------------------
	public void run(){
		while(gp.getLife() == true && ivLife == true){
			if(Screen.BOTTOM <= y){
				try{Thread.sleep(r.nextInt(dis)*1000);}catch(Exception e){}
				life = false;
			}
      if(life == true){
				y += 1;
				setLocation(x,y);
				notifyObservers();
			}
			try{Thread.sleep(dis);}catch(Exception e){}
		}
		y = Screen.BOTTOM;
		setLocation(x,y);
		setVisible(false);
	}

	//ミサイル命中時、画面外に飛ばす
	public void hit(){
		y = Screen.BOTTOM-50;
		//life = false;
		setLocation(x,y);
	}

	//ミサイルを発射位置にセット
	public void setPos(int x, int y){	//砲台の今のｘ座標
		this.x = x+(Screen.IV_W-w)/2;
		this.y = y+Screen.IV_H;
	}

	//Lifeのsetとget
	public void setLife(boolean l){
		life = l;
	}
	public boolean getLife(){
		return life;
	}

}
