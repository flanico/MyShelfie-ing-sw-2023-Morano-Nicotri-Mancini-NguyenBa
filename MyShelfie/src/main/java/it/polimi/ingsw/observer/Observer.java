package it.polimi.ingsw.observer;

import it.polimi.ingsw.network.message.Message;

/**
 * observer interface that support the method update
 */
public interface Observer {

    /**
     * called by the observable, update the observer
     * @param message for updating
     */
    void update(Message message);
}