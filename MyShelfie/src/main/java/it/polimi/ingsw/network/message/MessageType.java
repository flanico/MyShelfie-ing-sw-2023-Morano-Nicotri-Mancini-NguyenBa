package it.polimi.ingsw.network.message;

/**
 * enum that contains all the message type used to communicate between server and clients
 */
public enum MessageType {
    //Server side
    INFO_GAME,
    ERROR,
    NUM_PLAYERS_REQ,
    SHOW_BOARD,
    SELECT_TILE_REQ,
    INSERT_TILE_REQ,
    END_TURN,
    COMMON_GOAL_COMPLETE1,
    COMMON_GOAL_COMPLETE2,
    SHOW_BOOKSHELF,
    BOOKSHELF_FULL1,
    BOOKSHELF_FULL2,
    LOGIN_REPLY,
    WINNER,
    SCORES,
    DISCONNECTION,
    GENERIC,
    PING,
    SHOW_COMMON,
    SHOW_PERSONAL,
    //Client side
    LOGIN_REQ,
    NUM_PLAYERS_REP,
    POSITION_REPLY,
    TILES_REPLY,
    ORDER_REPLY, ORDER_REQ,
}
