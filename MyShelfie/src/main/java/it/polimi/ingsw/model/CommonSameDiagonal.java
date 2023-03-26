package it.polimi.ingsw.model;

public class CommonSameDiagonal extends CommonGoalCard {
    public boolean check() {
        TileType ref;
        boolean flag = true;                                        //creo un flag per la verifica e una ref per il tipo unico presente nella diagonale

        if (getCard(0,0).empty){                                    //controllo che la cella 0,0 non sia vuota e impongo che la ref sia il tipo della carta presente
            return false
        } else ref = getCard.type(0,0);

        for (int x=0; x<5; x++){                                    //controllo che le tessere nella prima diagonale abbiano lo stesso tipo della ref
            if (getCard(x,x).type != ref || getCard(x,x).empty)
                flag = false;
        }

        if (flag)                                                   //se il check va a buon fine, ritorna true
            return true;

        ref = true;
        if (getCard(1,1).empty){                                    //secondo check, controllo che la cella 1,1 non sia vuota e modifico la ref
            return false
        } else ref = getCard.type(1,1);

        for (int x=1; x<6; x++){                                        //controllo se la seconda diagonale abbia lo stesso tipo del nuovo ref
            if (getCard(x,x-1).type != ref || getCard(x,x-1).empty)
                flag = false;
        }

        if (flag)
            return true;
        else return false;
    }
}
