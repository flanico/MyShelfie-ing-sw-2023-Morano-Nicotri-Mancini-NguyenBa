package it.polimi.ingsw.model;

public class CommonFourCorners extends CommonGoalCard {
    public boolean check() {
        if (!getCard(0,0).empty && !getCard(0,5).empty && !getCard(4,0).empty && !getCard(4,5).empty &&
        getCard(0,0).type==getCard(0,5).type && getCard(0,5).type==getCard(4,0).type && getCard(4,0).type==getCard(4,5).type)
            return true;
        else return false;
    }
}
