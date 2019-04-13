import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Random;

//-----------------
//�C��~�T�C��
//-----------------
class InvaderMissile extends Subject implements Runnable{

  //�t�B�[���h--------------------------
	private int x = 0;
	private int y = Screen.BOTTOM;
	private int w = Screen.IM_W;
	private int h = Screen.IM_H;
	private boolean life;
	private int dis;
	private boolean ivLife;

	private GamePanel gp;

  Random r = new Random();


	//�R���X�g���N�^----------------------
	InvaderMissile(GamePanel gp){
		//�l��}��
		life = true;
		ivLife = true;
		this.gp = gp;
		if(gp.gm.getLevel() <= 8){
			dis = 11-gp.gm.getLevel();
		}
		else{
			dis = 3;
		}

		//�ݒ�
		setBounds(x,y,w,h);

		//�摜�\���
		setIcon(new ImageIcon("gazou/invaderMissile.png"));

	}


	//���\�b�h-----------------------------
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

	//�~�T�C���������A��ʊO�ɔ�΂�
	public void hit(){
		y = Screen.BOTTOM-50;
		//life = false;
		setLocation(x,y);
	}

	//�~�T�C���𔭎ˈʒu�ɃZ�b�g
	public void setPos(int x, int y){	//�C��̍��̂����W
		this.x = x+(Screen.IV_W-w)/2;
		this.y = y+Screen.IV_H;
	}

	//Life��set��get
	public void setLife(boolean l){
		life = l;
	}
	public boolean getLife(){
		return life;
	}

}
