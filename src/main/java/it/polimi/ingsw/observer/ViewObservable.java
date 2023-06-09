package it.polimi.ingsw.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * abstract class extended by the CLI and GUI classes which must be observed by the controller
 * @see ViewObserver
 */
public class ViewObservable {
    protected final List<ViewObserver> observers = new ArrayList<>();

    public void addObserver(ViewObserver obs) {
        observers.add(obs);
    }

    public void addAllObservers(List<ViewObserver> viewObserverList) {
        observers.addAll(viewObserverList);
    }

    public void removeObserver(ViewObserver obs) {
        observers.remove(obs);
    }

    public void removeAllObservers(List<ViewObserver> viewObserverList) {
        observers.removeAll(viewObserverList);
    }

    protected void notifyObserver(Consumer<ViewObserver> lambda) {
        for (ViewObserver obs : observers) {
            lambda.accept(obs);
        }
    }
}
