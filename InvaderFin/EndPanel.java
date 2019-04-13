import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class EndPanel extends JPanel implements KeyListener{

  //�t�B�[���h----------------------------
	int choice;	//1:RESTERT 2:END

	GameMain gm;
	JLabel tl;	//�^�C�g��
	JLabel lvl;	//���B���x��
	JLabel scl;	//�X�R�A
	JLabel rstl;	//���X�^�[�g
	JLabel endl;	//�I��
  PlayClip pc = null;
  PlayClip pc2 = null;
  PlayClip pc3 = null;

	//�摜�ǂݍ���
  Image bg = Toolkit.
    getDefaultToolkit().
    getImage("gazou/back4.png");


  //�R���X�g���N�^------------------------
  EndPanel(GameMain gm){
		//�l��}��
		choice = 1;
		this.gm = gm;
    pc = new PlayClip("oto/�J�[�\����������.wav");
    pc2 = new PlayClip("oto/����.wav");
    pc3 = new PlayClip("oto/�N���A�t�@���t�@�[��.wav");
		gm.pc.stop();
		gm.pc.reset();
		pc3.play();

    //���x����ݒu
    tl = new JLabel("GAMEOVER");
		tl.setBounds(Screen.WIDTH/2-250 ,100 ,500 ,200);
		tl.setFont(new Font("Palatino Linotype", Font.BOLD, 80));
	  tl.setHorizontalAlignment(JLabel.CENTER);
		tl.setForeground(Color.red);
    add(tl);
    lvl = new JLabel("LEVEL  :    " + String.valueOf(gm.getLevel()));
		lvl.setBounds(Screen.WIDTH/2-150 ,200 ,500 ,300);
		lvl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
		lvl.setForeground(Color.white);
    add(lvl);
    scl = new JLabel("SCORE :    " + String.valueOf(gm.getScore()));
		scl.setBounds(Screen.WIDTH/2-150 ,250 ,500 ,300);
		scl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
		scl.setForeground(Color.white);
    add(scl);
    rstl = new JLabel("RESTART");
		rstl.setBounds(Screen.WIDTH/2-350 ,500 ,300 ,100);
		rstl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
	  rstl.setHorizontalAlignment(JLabel.CENTER);
		rstl.setForeground(Color.white);
    add(rstl);
    endl = new JLabel("END");
		endl.setBounds(Screen.WIDTH/2+50 ,500 ,300 ,100);
		endl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
	  endl.setHorizontalAlignment(JLabel.CENTER);
		endl.setForeground(Color.gray);
    add(endl);

    //�ݒ�
    setLayout(null);
    setBounds(0, 0, Screen.WIDTH, Screen.HEIGHT);
    gm.addKeyListener(this);
    setVisible(true);
  }


  //���\�b�h----------------------------
	//�ĕ`�悳�ꂽ�Ƃ��ɌĂ΂�郁�\�b�h
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, Screen.WIDTH, Screen.HEIGHT, this);
		repaint();
	}

  //�L�[�������ꂽ
  public void keyPressed(KeyEvent e){
    int kc = e.getKeyCode();
    //�E�L�[
    if(kc == e.VK_RIGHT){
      pc.stop();
      pc.reset();
      pc.play();
      if(choice == 1){
        rstl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
    		rstl.setForeground(Color.gray);
     		endl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
    		endl.setForeground(Color.white);
        choice = 2;
      }
      else if(choice == 2){
        endl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
    		endl.setForeground(Color.gray);
     		rstl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
    		rstl.setForeground(Color.white);
        choice = 1;
      }
    }
    //���L�[
    if(kc == e.VK_LEFT){
      pc.stop();
      pc.reset();
      pc.play();
      if(choice == 1){
        rstl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
        rstl.setForeground(Color.gray);
        endl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
        endl.setForeground(Color.white);
        choice = 2;
      }
      else if(choice == 2){
        endl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
        endl.setForeground(Color.gray);
        rstl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
        rstl.setForeground(Color.white);
        choice = 1;
      }
    }
    //�G���^�[�L�[
    if(kc == e.VK_ENTER){
      pc2.stop();
      pc2.reset();
      pc2.play();
      if(choice == 1){
				gm.setLevel(1);
				gm.setScore(0);
				gm.setStock(3);
        gm.removeKeyListener(this);
	      pc3.stop();
	      pc3.reset();
	      gm.pc.play();
        gm.drawGame(this);
      }
      else if(choice == 2){
        System.exit(0);
      }
    }
    //�X�y�[�X�L�[
    if(kc == e.VK_SPACE){
      pc2.stop();
      pc2.reset();
      pc2.play();
      if(choice == 1){
				gm.setLevel(1);
				gm.setScore(0);
				gm.setStock(3);
        gm.removeKeyListener(this);
        gm.drawGame(this);
      }
      else if(choice == 2){
        System.exit(0);
      }
		}
  }
  //�L�[�������ꂽ
  public void keyReleased(KeyEvent e){
    //�����Ȃ�
  }
  public void keyTyped(KeyEvent e){
    //�����Ȃ�
  }

}
