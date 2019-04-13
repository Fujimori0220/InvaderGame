import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class WallPanel extends JPanel implements Observer{

  //フィールド-------------------------------------
  public int x = Screen.WP_X;
  public int y = Screen.WP_Y;
  public int w = Screen.WP_W;
  public int h = Screen.WP_H;
  public int n = Screen.WP_NUM;
  public int wn = Screen.WP_WNUM;
  public int hn = Screen.WP_HNUM;

  public Wall[][][] wall = new Wall[n][wn][hn];


  //コンストラクタ---------------------------------
  WallPanel(){
    setLayout(null);
    setBounds(x,y,w,h);
    setOpaque(false);
    for(int i = 0; i < n; i++){
      for(int j = 0; j < wn; j++){
        for(int k = 0; k < hn; k++){
          wall[i][j][k] = new Wall(i, j, k);
          add(wall[i][j][k]);
        }
      }
    }
  }


  //メソッド--------------------------------------
  public void update(Subject s){
    int mx;
    int my;
    if(s instanceof HoudaiMissile){
      mx = ((HoudaiMissile)s).getX();
      my = ((HoudaiMissile)s).getY();
    }
    else{ //if(s instanceof InvaderMissile){
      mx = ((InvaderMissile)s).getX();
      my = ((InvaderMissile)s).getY()+Screen.IM_H;
    }
    for(int i = 0; i < n; i++){
      for(int j = 0; j < wn; j++){
        for(int k = 0; k < hn; k++){
          int wx = wall[i][j][k].getX()+x;
          int wy = wall[i][j][k].getY()+y;
          if(wx <= mx+Screen.HM_W && (wx + Screen.WALL_W) >= mx && wy < my && (wy + Screen.WALL_H) >= my){
            if(wall[i][j][k].getLife() == true){
              wall[i][j][k].hit();
              if(s instanceof HoudaiMissile)
                ((HoudaiMissile)s).hit();
              else if(s instanceof InvaderMissile)
                ((InvaderMissile)s).hit();
            }
          }
        }
      }
    }
  }

  //リセット
  public void reset(){
    for(int i = 0; i < n; i++){
      for(int j = 0; j < wn; j++){
        for(int k = 0; k < hn; k++){
          wall[i][j][k].setVisible(true);
          wall[i][j][k].setLife(true);
        }
      }
    }
  }
}
