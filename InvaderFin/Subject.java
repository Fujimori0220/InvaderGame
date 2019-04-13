import java.util.ArrayList;
import javax.swing.*;

public abstract class Subject extends JLabel{
  
	private ArrayList<Observer> observers = new ArrayList<>();        // Observer������ێ�
    public void addObserver(Observer observer) {    // Observer��ǉ�
        observers.add(observer);
    }
    public void deleteObserver(Observer observer) { // Observer���폜
        observers.remove(observer);
    }
    public void notifyObservers() {               // Observer�֒ʒm
 
    	for(Observer o : observers){
    		o.update(this);
    	}
    }
}
