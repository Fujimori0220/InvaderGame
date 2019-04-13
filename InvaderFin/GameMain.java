import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameMain extends JFrame{

  //�t�B�[���h----------------------------------------
  private int level = 1; //���x��
  private int score = 0; //�X�R�A
  private int stock = 3; //�c�@
  private StartPanel sp;
  private GamePanel gp;
  private EndPanel ep;
  public PlayClip pc;


  //�R���X�g���N�^------------------------------------
  public GameMain(){
		//�^�C�g��
		super( "�C���x�[�_�[�Q�[��" );
		//�uX�v�ŏI��
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		//���C�A�E�g�͎��R
		setLayout(null);
		//�F�̐ݒ�
		getContentPane().setBackground(Color.BLACK);
		//�T�C�Y�ݒ�
		setBounds(0,0,Screen.WIDTH,Screen.HEIGHT);
    //������ʍ쐬
    sp = new StartPanel(this);
    add(sp);
		pc = new PlayClip("oto/���C��BGM.wav");
    pc.loop();
		//��
		setVisible(true);
  }


  //���\�b�h------------------------------------------
  //�X�^�[�g��ʕ\��
  void drawStart(JPanel jp){
    System.out.println("�X�^�[�g��ʕ\��");
    jp.setVisible(false);
    remove(jp);
    sp = new StartPanel(this);
    add(sp);
  }

  //�Q�[����ʕ\��
  void drawGame(JPanel jp){
    System.out.println("�Q�[����ʕ\��");
    jp.setVisible(false);
    remove(jp);
    gp = new GamePanel(this);
    add(gp);
  }

  //�I����ʕ\��
  void drawEnd(JPanel jp){
    System.out.println("�I����ʕ\��");
    jp.setVisible(false);
    remove(jp);
    ep = new EndPanel(this);
    add(ep);
  }

  //set&get���\�b�h
	int getLevel(){
		return level;
	}
	void setLevel(int l){
		level = l;
	}
	int getScore(){
		return score;
	}
	void setScore(int s){
		score = s;
	}
	int getStock(){
		return stock;
	}
	void setStock(int s){
		stock = s;
	}

}
