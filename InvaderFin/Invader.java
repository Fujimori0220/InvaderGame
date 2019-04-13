import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Random;

class Invader extends JLabel implements Runnable{

  //フィールド-------------------------------------
  private int x;        //x座標
  private int y;        //y座標
  private int w;        //インベーダーの幅
  private int h;        //インベーダーの高さ
  private boolean life; //生存状態
  private int score;    //スコア
  private PlayClip pc = null;

  private ImageIcon img1; //生存時画像
  private ImageIcon img0; //破壊時画像

  private GamePanel gp;
  //private Thread tiv;

  Random r = new Random();


  //コンストラクタ---------------------------------
  Invader(GamePanel gp, int i, int j){
    //値を挿入
    w = Screen.IV_W;
    h = Screen.IV_H;
    x = i*(w+Screen.IP_WNEXT);
    y = j*(h+Screen.IP_HNEXT);
    life = true;
    this.gp = gp;
    pc = new PlayClip("oto/死んだときの音.wav");

    //設定
    setBounds(x,y,w,h);

    //画像と得点をセット
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


  //メソッド--------------------------------------
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

  //HoudaiMissile命中
  public void hit(Subject s){
    setIcon(img0);
    life = false;
    Thread tiv = new Thread(this);
    tiv.start();
    ((HoudaiMissile)s).hit();
    gp.gm.setScore(gp.gm.getScore()+score);
    System.out.println("インベーダーに命中");
    System.out.println("スコア：" + gp.gm.getScore());
  }
  //get&setメソッド
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
