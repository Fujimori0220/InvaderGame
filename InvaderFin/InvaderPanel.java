import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class InvaderPanel extends JPanel implements Runnable, Observer{

  //�t�B�[���h----------------------------------
  public int x = Screen.IP_X+1;
  public int y = Screen.IP_Y;
  public int w = Screen.IP_W;
  public int h = Screen.IP_H;
  public int wn = Screen.IP_WNUM; //��
  public int hn = Screen.IP_HNUM; //�s��
  public int dir = Screen.DIR_R;  //�ړ�����
  public int dis;
  public int[] ivNum = new int[wn]; //�񂲂Ƃ̃C���x�[�_�[�c��
  public boolean life;

  public GamePanel gp;
  public Invader[][] iv = new Invader[wn][hn];
  public InvaderMissile[][] im = new InvaderMissile[wn][hn];


  //�R���X�g���N�^-------------------------------
  InvaderPanel(GamePanel gp, InvaderMissile[][] im){
    //�l��}��
    this.gp = gp;
    this.im = im;
    life = true;
    //���x���ɉ��������x��}���A������x��݂���
    if(60-gp.gm.getLevel()*10 > 10){
      dis = 50-gp.gm.getLevel()*10;
    }
    else{
      dis = 10;
    }
    for(int i = 0; i < wn; i++){
      ivNum[i] = hn;
    }

    //�C���x�[�_�[�쐬�AIV��IM�̃X���b�h�X�^�[�g
		for(int i = 0; i < wn; i++){
			for(int j = 0; j < hn; j++){
        iv[i][j] = new Invader(gp, i, j);
        add(iv[i][j]);
      }
    }

    //�ݒ�
    setLayout(null);
    setBounds(x,y,w,h);
    setOpaque(false);

  }


  //���\�b�h--------------------------------------
  public void run(){
    while(gp.getLife() == true && life == true){
      //�e��̍őO��̃~�T�C������
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
      //���E�Ɉړ��A�ǂɓ��������ꍇ���ֈړ�
      if(dir == Screen.DIR_R){
        if(hitWall()){
          y += 20;
          dir = Screen.DIR_L;
          //���xUP
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
          //���xUP
          if(dis > 5)
            dis -= 2;
          x += 1;
        }else{
          x -= 1;
        }
      }
      setLocation(x,y);
      //�h�Ǔ��B���A�c�@��0�ɂ���
      if(y+h >= Screen.WP_Y){
        gp.gm.setStock(0);
      }
      try{Thread.sleep(dis);}catch(Exception e){}
    }
  }

  public void update(Subject s){
    int mx = ((HoudaiMissile)s).getX();
    int my = ((HoudaiMissile)s).getY();
    //�C��~�T�C���p�l�����N��
    if(x <= mx && (x + w) >= mx && y < my && (y + h) >= my){
      for(int i = 0; i < wn; i++){
        for(int j = 0; j < hn; j++){
          int ix = iv[i][j].getX()+x;
          int iy = iv[i][j].getY()+y;
          //�~�T�C���̈ʒu�ɊY������IV�����邩
          if(ix <= mx+Screen.HM_W && (ix + Screen.IV_W) >= mx && iy < my && (iy + Screen.IV_H) >= my){
            //IV�������Ă��邩
            if(iv[i][j].getLife()){
              //�~�T�C���������̏���
              iv[i][j].hit(s);
              ivNum[i]--;
            }
          }
        }
      }
    }
  }

  //���E�̕ǂɓ���������
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

  //get,set���\�b�h
  public void setLife(boolean l){
    life = l;
  }

}
