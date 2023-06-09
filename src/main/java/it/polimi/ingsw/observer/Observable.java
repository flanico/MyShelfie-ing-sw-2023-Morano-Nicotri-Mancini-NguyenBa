package it.polimi.ingsw.observer;

import it.polimi.ingsw.network.message.Message;
import java.util.ArrayList;
import java.util.List;

/**
 * class that defines observable
 */
public class Observable {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * add an observer in the list of observers
     * @param obs to add
     */
    public void addObserver(Observer obs){
        observers.add(obs);
    }

    /**
     * remove an observer from the list of observers
     * @param obs to remove
     */
    public void removeObserver(Observer obs){
        observers.remove(obs);
    }

    /**
     * remove all observers from the list of observers
     */
    public void removeAllObservers() {
        observers.clear();
    }

    /**
     * notify all observer in the list of observers
     * @param message of updating
     */
    public void notifyObserver(Message message){
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}