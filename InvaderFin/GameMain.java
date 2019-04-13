import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameMain extends JFrame{

  //フィールド----------------------------------------
  private int level = 1; //レベル
  private int score = 0; //スコア
  private int stock = 3; //残機
  private StartPanel sp;
  private GamePanel gp;
  private EndPanel ep;
  public PlayClip pc;


  //コンストラクタ------------------------------------
  public GameMain(){
		//タイトル
		super( "インベーダーゲーム" );
		//「X」で終了
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		//レイアウトは自由
		setLayout(null);
		//色の設定
		getContentPane().setBackground(Color.BLACK);
		//サイズ設定
		setBounds(0,0,Screen.WIDTH,Screen.HEIGHT);
    //初期画面作成
    sp = new StartPanel(this);
    add(sp);
		pc = new PlayClip("oto/メインBGM.wav");
    pc.loop();
		//可視
		setVisible(true);
  }


  //メソッド------------------------------------------
  //スタート画面表示
  void drawStart(JPanel jp){
    System.out.println("スタート画面表示");
    jp.setVisible(false);
    remove(jp);
    sp = new StartPanel(this);
    add(sp);
  }

  //ゲーム画面表示
  void drawGame(JPanel jp){
    System.out.println("ゲーム画面表示");
    jp.setVisible(false);
    remove(jp);
    gp = new GamePanel(this);
    add(gp);
  }

  //終了画面表示
  void drawEnd(JPanel jp){
    System.out.println("終了画面表示");
    jp.setVisible(false);
    remove(jp);
    ep = new EndPanel(this);
    add(ep);
  }

  //set&getメソッド
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
