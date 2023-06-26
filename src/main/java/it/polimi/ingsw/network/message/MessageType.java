package it.polimi.ingsw.network.message;

/**
 * enum that contains all the message type used to communicate between server and clients
 */
public enum MessageType {
    //Server side
    INFO_GAME,
    ERROR,
    CHAT_MESSAGE_REQ,
    CHAT_MESSAGE_REPLY,
    NUM_PLAYERS_REQ,
    SHOW_BOARD,
    SELECT_TILE_REQ,
    INSERT_TILE_REQ,
    COMMON_GOAL_COMPLETE,
    SHOW_BOOKSHELF,
    LOGIN_REPLY,
    WINNER,
    SCORES,
    DISCONNECTION,
    GENERIC,
    PING,
    SHOW_COMMON,
    SHOW_PERSONAL,
    ORDER_REQ,

    //Client side
    LOGIN_REQ,
    NUM_PLAYERS_REP,
    POSITION_REPLY,
    TILES_REPLY,
    ORDER_REPLY,
    COMMON_SCORES,
}
