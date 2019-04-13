import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Random;

class Ufo extends JLabel implements Runnable, Observer{

  //�t�B�[���h-------------------------------------
  public int x = -Screen.UFO_W;
  public int y = Screen.UFO_Y;
  public int w = Screen.UFO_W;
  public int h = Screen.UFO_H;
  public boolean life = false;
  public int dir;  //�ړ�����
  private PlayClip pc = null;

  public ImageIcon img1 = new ImageIcon("gazou/ufo.gif");
  public ImageIcon img2 = new ImageIcon("gazou/taihaUfo.gif");

  public GamePanel gp;

  Random r = new Random();


  //�R���X�g���N�^---------------------------------
  Ufo(GamePanel gp){
    pc = new PlayClip("oto/���񂾂Ƃ��̉�.wav");
    setBounds(x,y,w,h);
    setIcon(img1);
    setOpaque(false);
    this.gp = gp;
  }


  //���\�b�h--------------------------------------
  public void run(){
    while(gp.getLife() == true){
      try{Thread.sleep(10);}catch(Exception e){}
      if(life == false){
        if(!(x > Screen.RIGHT || x < Screen.LEFT-Screen.UFO_W)){
          pc.play();
        }
        try{Thread.sleep(500);}catch(Exception e){}
        if(!(x > Screen.RIGHT || x < Screen.LEFT-Screen.UFO_W)){
          pc.stop();
          pc.reset();
        }
        dir = r.nextInt(2);
        if(dir == Screen.DIR_R)
          x = Screen.LEFT-w;
        else if(dir == Screen.DIR_L)
          x = Screen.RIGHT;
        setLocation(x,y);
        setIcon(img1);
        life = true;
        try{Thread.sleep(r.nextInt(4)*1000);}catch(Exception e){}

      }
      if(dir == Screen.DIR_R){
        x += 1;
      }else if(dir == Screen.DIR_L){
        x -= 1;
      }
      setLocation(x,y);
      //��ʊO�ɏo��
      if(x > Screen.RIGHT || x < Screen.LEFT-Screen.UFO_W){
        life = false;
      }
    }
  }

  public void update(Subject s){
    int mx = ((HoudaiMissile)s).getX();
    int my = ((HoudaiMissile)s).getY();
    //System.out.printf("�yUFO�z(%d,%d)\n",mx,my);
    //HoudaiMissile����
    if(x <= mx+Screen.HM_W && (x + w) >= mx && y < my && (y + h) >= my){
      if(life){
        setIcon(img2);
        life = false;
        ((HoudaiMissile)s).hit();
        gp.gm.setScore(gp.gm.getScore()+500);
        System.out.println("Ufo�ɖ���");
        System.out.println("�X�R�A�F" + gp.gm.getScore());
      }
    }
  }
}
