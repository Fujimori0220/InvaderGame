import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Random;

class Invader extends JLabel implements Runnable{

  //�t�B�[���h-------------------------------------
  private int x;        //x���W
  private int y;        //y���W
  private int w;        //�C���x�[�_�[�̕�
  private int h;        //�C���x�[�_�[�̍���
  private boolean life; //�������
  private int score;    //�X�R�A
  private PlayClip pc = null;

  private ImageIcon img1; //�������摜
  private ImageIcon img0; //�j�󎞉摜

  private GamePanel gp;
  //private Thread tiv;

  Random r = new Random();


  //�R���X�g���N�^---------------------------------
  Invader(GamePanel gp, int i, int j){
    //�l��}��
    w = Screen.IV_W;
    h = Screen.IV_H;
    x = i*(w+Screen.IP_WNEXT);
    y = j*(h+Screen.IP_HNEXT);
    life = true;
    this.gp = gp;
    pc = new PlayClip("oto/���񂾂Ƃ��̉�.wav");

    //�ݒ�
    setBounds(x,y,w,h);

    //�摜�Ɠ��_���Z�b�g
    if(j == 0){
      img1 = new ImageIcon("gazou/invader1.gif");
      score = 150;
    }
    else if(j == 1){
      img1 = new ImageIcon("gazou/invader2.gif");
      score = 130;
    }
    else if(j == 2){
      img1 = new ImageIcon("gazou/invader3.gif");
      score = 120;
    }
    else if(j == 3){
      img1 = new ImageIcon("gazou/invader4.gif");
      score = 110;
    }
    else if(j == 4){
      img1 = new ImageIcon("gazou/invader5.gif");
      score = 100;
    }
    img0 = new ImageIcon("gazou/taihaIv.gif");
    setIcon(img1);

  }


  //���\�b�h--------------------------------------
  public void run(){
    while(life == true && gp.getLife() == true){
      try{Thread.sleep(10);}catch(Exception e){}
    }
    pc.play();
    try{Thread.sleep(500);}catch(Exception e){}
    pc.stop();
    pc.reset();
    setVisible(false);
  }

  //HoudaiMissile����
  public void hit(Subject s){
    setIcon(img0);
    life = false;
    Thread tiv = new Thread(this);
    tiv.start();
    ((HoudaiMissile)s).hit();
    gp.gm.setScore(gp.gm.getScore()+score);
    System.out.println("�C���x�[�_�[�ɖ���");
    System.out.println("�X�R�A�F" + gp.gm.getScore());
  }
  //get&set���\�b�h
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public boolean getLife(){
    return life;
  }
  /*public void setLife(boolean life){
    this.life = life;
  }*/
}
