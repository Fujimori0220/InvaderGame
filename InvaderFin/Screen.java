class Screen{
  //画面サイズ--------------------------------------------------------------
  public static final int WIDTH = 1365;//幅
  public static final int HEIGHT = 730; //高さ
  public static final int RIGHT = WIDTH-15;   //右
  public static final int LEFT = 0;           //左
  public static final int TOP = 0;            //天井
  public static final int BOTTOM = HEIGHT-45; //地面

  //砲台----------------------------------------------------------------------
  //Houdai
  public static final int HOUDAI_W = 30;  //砲台幅
  public static final int HOUDAI_H = 33;  //砲台高さ
  public static final int HOUDAI_Y = BOTTOM-HOUDAI_H-10; //砲台Y座標

  //HoudaiMissile
  public static final int HM_W = 20;  //砲台ミサイル幅
  public static final int HM_H = 30;  //砲台ミサイル高さ
  public static final int HM_Y = BOTTOM-HOUDAI_H-HM_H;  //砲台ミサイルY座標発射時

  //UFO----------------------------------------------------------------------
  //Ufo
  public static final int UFO_Y = TOP+40; //UFOY座標
  public static final int UFO_W = 50; //UFO幅
  public static final int UFO_H = 29; //UFO高さ

  //インベーダー-------------------------------------------------------------
  //Invader
  public static final int IV_W = 40;  //インベーダー幅
  public static final int IV_H = 40;  //インベーダー高さ

  //InvaderMissile
  public static final int IM_W = 20;  //インベーダーミサイル幅
  public static final int IM_H = 30;  //インベーダーミサイル高さ

  //InvaderPanel
  public static final int IP_WNUM = 11;//11;   //インベーダー列数
  public static final int IP_HNUM = 5;//5;  //インベーダー段数
  public static final int IP_WNEXT = 50;  //隣との幅
  public static final int IP_HNEXT = 20;  //下との幅
  public static final int IP_W = IV_W*IP_WNUM+IP_WNEXT*(IP_WNUM-1); //インベーダーパネル幅
  public static final int IP_H = IV_H*IP_HNUM+IP_HNEXT*(IP_HNUM-1);  //インベーダーパネル高さ
  public static final int IP_X = LEFT;    //インベーダーパネルX座標初期位置
  public static final int IP_Y = TOP+UFO_Y+UFO_H+20;  //iインベーダーパネルX座標初期位置

  //壁------------------------------------------------------------------------
  public static final int WALL_W = 30;
  public static final int WALL_H = 30
  ;
  public static final int WP_NUM = 4;
  public static final int WP_WNUM = 5;
  public static final int WP_HNUM = 3;
  public static final int WP_W = RIGHT;
  public static final int WP_H = WALL_H*WP_HNUM;
  public static final int WP_X = LEFT;
  public static final int WP_Y = 500;

  //移動方向-------------------------------------------------------------------
  public static final int DIR_R = 0;  //移動方向右
  public static final int DIR_L = 1;  //移動方向左
}
