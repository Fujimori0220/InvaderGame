import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class InvaderPanel extends JPanel implements Runnable, Observer{

  //フィールド----------------------------------
  public int x = Screen.IP_X+1;
  public int y = Screen.IP_Y;
  public int w = Screen.IP_W;
  public int h = Screen.IP_H;
  public int wn = Screen.IP_WNUM; //列数
  public int hn = Screen.IP_HNUM; //行数
  public int dir = Screen.DIR_R;  //移動方向
  public int dis;
  public int[] ivNum = new int[wn]; //列ごとのインベーダー残数
  public boolean life;

  public GamePanel gp;
  public Invader[][] iv = new Invader[wn][hn];
  public InvaderMissile[][] im = new InvaderMissile[wn][hn];


  //コンストラクタ-------------------------------
  InvaderPanel(GamePanel gp, InvaderMissile[][] im){
    //値を挿入
    this.gp = gp;
    this.im = im;
    life = true;
    //レベルに応じた速度を挿入、上限速度を設ける
    if(60-gp.gm.getLevel()*10 > 10){
      dis = 50-gp.gm.getLevel()*10;
    }
    else{
      dis = 10;
    }
    for(int i = 0; i < wn; i++){
      ivNum[i] = hn;
    }

    //インベーダー作成、IVとIMのスレッドスタート
		for(int i = 0; i < wn; i++){
			for(int j = 0; j < hn; j++){
        iv[i][j] = new Invader(gp, i, j);
        add(iv[i][j]);
      }
    }

    //設定
    setLayout(null);
    setBounds(x,y,w,h);
    setOpaque(false);

  }


  //メソッド--------------------------------------
  public void run(){
    while(gp.getLife() == true && life == true){
      //各列の最前列のミサイル発射
      for(int i = 0; i < wn; i++){
        for(int j = hn-1; j >= ivNum[i]-1; j--){
          if(j < 0){
            break;
          }
          if(iv[i][j].getLife() == true && im[i][j].getLife() == false){
            im[i][j].setPos(x+iv[i][j].getX(), y+iv[i][j].getY());
            im[i][j].setLife(true);
            break;
          }
          //System.out.println("j : " + j);
        }
        //System.out.println("i : " + i);
      }
      //左右に移動、壁に当たった場合下へ移動
      if(dir == Screen.DIR_R){
        if(hitWall()){
          y += 20;
          dir = Screen.DIR_L;
          //速度UP
          if(dis > 5)
            dis -= 2;
          x -= 1;
        }else{
          x += 1;
        }
      }else if(dir == Screen.DIR_L){
        if(hitWall()){
          y += 20;
          dir = Screen.DIR_R;
          //速度UP
          if(dis > 5)
            dis -= 2;
          x += 1;
        }else{
          x -= 1;
        }
      }
      setLocation(x,y);
      //防壁到達時、残機を0にする
      if(y+h >= Screen.WP_Y){
        gp.gm.setStock(0);
      }
      try{Thread.sleep(dis);}catch(Exception e){}
    }
  }

  public void update(Subject s){
    int mx = ((HoudaiMissile)s).getX();
    int my = ((HoudaiMissile)s).getY();
    //砲台ミサイルパネル内侵入
    if(x <= mx && (x + w) >= mx && y < my && (y + h) >= my){
      for(int i = 0; i < wn; i++){
        for(int j = 0; j < hn; j++){
          int ix = iv[i][j].getX()+x;
          int iy = iv[i][j].getY()+y;
          //ミサイルの位置に該当するIVがあるか
          if(ix <= mx+Screen.HM_W && (ix + Screen.IV_W) >= mx && iy < my && (iy + Screen.IV_H) >= my){
            //IVが生きているか
            if(iv[i][j].getLife()){
              //ミサイル命中時の処理
              iv[i][j].hit(s);
              ivNum[i]--;
            }
          }
        }
      }
    }
  }

  //左右の壁に当たったか
  public boolean hitWall(){
    for(int i = 0; i < wn; i++){
      int ix = iv[i][0].getX();
      if(x+ix+Screen.IV_W >= Screen.RIGHT || x+ix <= Screen.LEFT){
        if(ivNum[i] > 0){
          return true;
        }
      }
    }
    return false;
  }

  //get,setメソッド
  public void setLife(boolean l){
    life = l;
  }

}
