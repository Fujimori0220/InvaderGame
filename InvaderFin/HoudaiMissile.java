import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class HoudaiMissile extends Subject implements Runnable{

	//�t�B�[���h-----------------------------
	private int x = -Screen.HM_W;
	private int y = 0;//Screen.HM_Y;
	private int w = Screen.HM_W;
	private int h = Screen.HM_H;
	private boolean life;

	private GamePanel gp;
	public PlayClip pc;


	//�R���X�g���N�^-------------------------
	HoudaiMissile(GamePanel gp){
		life = false;
		this.gp = gp;
		pc = new PlayClip("oto/hasya.wav");

		//���W�A�T�C�Y
		setBounds(x,y,w,h);

		//�摜�\���
		setIcon(new ImageIcon("gazou/houdaiMissile.png"));


	}


	//���\�b�h--------------------------------
	//�ړ����\�b�h
	public void run(){
		while(gp.getLife() == true){
			try{Thread.sleep(1);}catch(Exception e){}

			if(Screen.TOP-h >= y){
				life = false;
				pc.stop();
				pc.reset();
			}
			//System.out.println("�~�T�C���㏸��");
			if(life == true){
				y -= 1;
				setLocation(x,y);
				notifyObservers();
			}

		}
	}

	//�~�T�C���������A��ʊO�ɔ�΂�
	public void hit(){
		y = Screen.TOP-h;
		life = false;
		pc.stop();
		pc.reset();
		setLocation(x,y);
	}

	//�~�T�C���𔭎ˈʒu�ɃZ�b�g
	public void setPos(int x){	//�C��̍��̂����W
		this.x = x+(Screen.HOUDAI_W-w)/2;
		this.y = Screen.HM_Y;
	}

	//Life��set��get
	public void setLife(boolean l){
		life = l;
	}
	public boolean getLife(){
		return life;
	}

}
