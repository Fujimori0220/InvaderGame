class Screen{
  //��ʃT�C�Y--------------------------------------------------------------
  public static final int WIDTH = 1365;//��
  public static final int HEIGHT = 730; //����
  public static final int RIGHT = WIDTH-15;   //�E
  public static final int LEFT = 0;           //��
  public static final int TOP = 0;            //�V��
  public static final int BOTTOM = HEIGHT-45; //�n��

  //�C��----------------------------------------------------------------------
  //Houdai
  public static final int HOUDAI_W = 30;  //�C�䕝
  public static final int HOUDAI_H = 33;  //�C�䍂��
  public static final int HOUDAI_Y = BOTTOM-HOUDAI_H-10; //�C��Y���W

  //HoudaiMissile
  public static final int HM_W = 20;  //�C��~�T�C����
  public static final int HM_H = 30;  //�C��~�T�C������
  public static final int HM_Y = BOTTOM-HOUDAI_H-HM_H;  //�C��~�T�C��Y���W���ˎ�

  //UFO----------------------------------------------------------------------
  //Ufo
  public static final int UFO_Y = TOP+40; //UFOY���W
  public static final int UFO_W = 50; //UFO��
  public static final int UFO_H = 29; //UFO����

  //�C���x�[�_�[-------------------------------------------------------------
  //Invader
  public static final int IV_W = 40;  //�C���x�[�_�[��
  public static final int IV_H = 40;  //�C���x�[�_�[����

  //InvaderMissile
  public static final int IM_W = 20;  //�C���x�[�_�[�~�T�C����
  public static final int IM_H = 30;  //�C���x�[�_�[�~�T�C������

  //InvaderPanel
  public static final int IP_WNUM = 11;//11;   //�C���x�[�_�[��
  public static final int IP_HNUM = 5;//5;  //�C���x�[�_�[�i��
  public static final int IP_WNEXT = 50;  //�ׂƂ̕�
  public static final int IP_HNEXT = 20;  //���Ƃ̕�
  public static final int IP_W = IV_W*IP_WNUM+IP_WNEXT*(IP_WNUM-1); //�C���x�[�_�[�p�l����
  public static final int IP_H = IV_H*IP_HNUM+IP_HNEXT*(IP_HNUM-1);  //�C���x�[�_�[�p�l������
  public static final int IP_X = LEFT;    //�C���x�[�_�[�p�l��X���W�����ʒu
  public static final int IP_Y = TOP+UFO_Y+UFO_H+20;  //i�C���x�[�_�[�p�l��X���W�����ʒu

  //��------------------------------------------------------------------------
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

  //�ړ�����-------------------------------------------------------------------
  public static final int DIR_R = 0;  //�ړ������E
  public static final int DIR_L = 1;  //�ړ�������
}
