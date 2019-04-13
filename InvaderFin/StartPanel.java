import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class StartPanel extends JPanel implements KeyListener{

  //�t�B�[���h----------------------------
  int choice; //1:START 2:END

  GameMain gm;
  JLabel tl;
  JLabel stl;
  JLabel endl;
  PlayClip pc = null;
  PlayClip pc2 = null;

	//�摜�ǂݍ���
  Image bg = Toolkit.
    getDefaultToolkit().
    getImage("gazou/backSp.jpg");


  //�R���X�g���N�^------------------------
  StartPanel(GameMain gm){
    //�l��}��
    choice = 1;
    this.gm = gm;
    pc = new PlayClip("oto/�J�[�\����������.wav");
    pc2 = new PlayClip("oto/����.wav");

    //���x����ݒu
    tl = new JLabel();
    tl.setLayout(null);
		tl.setBounds(Screen.WIDTH/2-500 ,100 ,998 ,174);
    tl.setIcon(new ImageIcon("gazou/title.png"));
    add(tl);
    stl = new JLabel("START");
		stl.setBounds(Screen.WIDTH/2-350 ,500 ,300 ,100);
		stl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
	  stl.setHorizontalAlignment(JLabel.CENTER);
		stl.setForeground(Color.white);
    add(stl);
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
        stl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
    		stl.setForeground(Color.gray);
     		endl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
    		endl.setForeground(Color.white);
        choice = 2;
      }
      else if(choice == 2){
        endl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
    		endl.setForeground(Color.gray);
     		stl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
    		stl.setForeground(Color.white);
        choice = 1;
      }
    }
    //���L�[
    if(kc == e.VK_LEFT){
      pc.stop();
      pc.reset();
      pc.play();
      if(choice == 1){
        stl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
        stl.setForeground(Color.gray);
        endl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
        endl.setForeground(Color.white);
        choice = 2;
      }
      else if(choice == 2){
        endl.setFont(new Font("Palatino Linotype", Font.BOLD, 50));
        endl.setForeground(Color.gray);
        stl.setFont(new Font("Palatino Linotype", Font.BOLD, 60));
        stl.setForeground(Color.white);
        choice = 1;
      }
    }
    //�G���^�[�L�[
    if(kc == e.VK_ENTER){
      pc2.stop();
      pc2.reset();
      pc2.play();
      if(choice == 1){
        gm.removeKeyListener(this);
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
