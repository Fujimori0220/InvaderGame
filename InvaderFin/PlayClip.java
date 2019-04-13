import java.io.*;
import javax.sound.sampled.*;

class PlayClip{

  //フィールド------------------------------
  Clip clip = null;
  int frameLength;


  //コンストラクタ----------------------------
  PlayClip(String filename){
    try{
      AudioInputStream ais =
        AudioSystem.getAudioInputStream(new File(filename));
      clip = AudioSystem.getClip();
      clip.open(ais);

      frameLength = clip.getFrameLength();
      //System.out.println("音声の長さ:" + frameLength);
    }catch(Exception e){
      System.out.println(e);
    }
  }


  //メソッド----------------------------------
  public void play(){
    clip.start();
    //System.out.println("Playing...");
  }
  public void loop(){
    clip.loop(-1);
  }
  public void stop(){
    clip.stop();
  }
  public void reset(){
    clip.setFramePosition(0);
  }
  
}
