package it.polimi.ingsw.network.message;

/**
 * enum that contains all the message type used to communicate between server and clients
 */
public enum MessageType {
    //Server side
    INFO_GAME,
    ERROR,
    NUM_PLAYERS_REQ,
    LOGIN_REPLY,
    WINNER,
    //Client side
    LOGIN_REQ,
    NUM_PLAYERS_REP,
    POSITION_REPLY,
    TILES_REPLY,
}
