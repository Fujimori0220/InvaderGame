import java.util.ArrayList;
import javax.swing.*;

public abstract class Subject extends JLabel{
  
	private ArrayList<Observer> observers = new ArrayList<>();        // Observerたちを保持
    public void addObserver(Observer observer) {    // Observerを追加
        observers.add(observer);
    }
    public void deleteObserver(Observer observer) { // Observerを削除
        observers.remove(observer);
    }
    public void notifyObservers() {               // Observerへ通知
 
    	for(Observer o : observers){
    		o.update(this);
    	}
    }
}
