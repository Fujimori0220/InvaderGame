import java.util.ArrayList;
import javax.swing.*;

public abstract class Subject extends JLabel{
  
	private ArrayList<Observer> observers = new ArrayList<>();        // Observer‚½‚¿‚ğ•Û
    public void addObserver(Observer observer) {    // Observer‚ğ’Ç‰Á
        observers.add(observer);
    }
    public void deleteObserver(Observer observer) { // Observer‚ğíœ
        observers.remove(observer);
    }
    public void notifyObservers() {               // Observer‚Ö’Ê’m
 
    	for(Observer o : observers){
    		o.update(this);
    	}
    }
}
