package it.polimi.ingsw;

public enum PersonalGoalCardType {
    GOAL1,
    GOAL2,
    GOAL3,
    GOAL4,
    GOAL5,
    GOAL6,
    GOAL7,
    GOAL8,
    GOAL9,
    GOAL10,
    GOAL11,
    GOAL12;

    private boolean isTaken;

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}