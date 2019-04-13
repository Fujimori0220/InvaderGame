import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//-----------------
//�C��
//-----------------
class Houdai extends JLabel implements Runnable, Observer{

	//�t�B�[���h---------------------------------
	private int x;	//x���W
	private int y;	//y���W
	private int w;	//����
	private int h;	//����
	private boolean life;		//�������
	private boolean right;	//�E�L
	private boolean left;		//���L�[
	private boolean space;	//�X�y�[�X�L�[
  private PlayClip pc;

	private ImageIcon img1;
	private ImageIcon img0;

	private GamePanel gp;
	private HoudaiMissile hm;


	//�R���X�g���N�^----------------------------
	Houdai(GamePanel gp, HoudaiMissile hm){
		//�l��}��
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
    pc = new PlayClip("oto/���񂾂Ƃ��̉�.wav");

		//�ݒ�
		setBounds(x,y,w,h);
		setIcon(img1);
	}


	//���\�b�h---------------------------------
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
					//�~�T�C���A�C��̐悩���ɂ�����
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
		//System.out.printf("�yUFO�z(%d,%d)\n",mx,my);
		//HoudaiMissile����
		if(x <= mx+Screen.IM_W && (x + w) >= mx && y < my && (y + h) >= my){
			if(life = true){
				setIcon(img0);
				gp.gm.setStock(gp.gm.getStock()-1);
				life = false;
				((InvaderMissile)s).hit();
				System.out.println("�C��ɖ����I�c�胉�C�t�F" + gp.gm.getStock());
			}
		}
	}

	//�ړ����\�b�h
	public void move(int k, boolean status){
		if(k == KeyEvent.VK_RIGHT){
			right = status;
		}
		if(k == KeyEvent.VK_LEFT){
			left = status;
		}
	}
	//���˃��\�b�h
	public void hasha(boolean status){
		space = status;
	}

}
