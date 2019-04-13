import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Random;

class Wall extends JLabel{

  //フィールド-------------------------------------
  private int w = Screen.WALL_W;
  private int h = Screen.WALL_H;
  private int x;
  private int y;
  private boolean life = true;

  public ImageIcon img1 = new ImageIcon("gazou/wall2.png");


  //コンストラクタ---------------------------------
  Wall(int i, int j, int k){
    x += w*j+(((Screen.WIDTH-w*Screen.WP_WNUM*4)/5)*(i+1)+w*Screen.WP_WNUM*i);
    y = h*k;
    setBounds(x,y,w,h);
    setIcon(img1);
    setOpaque(false);
  }


  //メソッド--------------------------------------
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public boolean getLife(){
    return life;
  }
  public void setLife(boolean l){
    life = l;
  }

  public void hit(){
    setVisible(false);
    life = false;
    //System.out.println("壁に命中");
  }

}
