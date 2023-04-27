package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.View;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class that represents the interface view via command line interface
 */
public class Cli extends ViewObservable implements View {
    private final PrintStream out;
    private static final String STR_INPUT_ERR = ColorCli.RED + "Invalid Input! Please retry." + ColorCli.RESET;
    public Scanner readLine = new Scanner(System.in);

    /**
     * constructor of the Cli
     */
    public Cli() {
        out = System.out;
    }

    /**
     * prints the welcome message of the game
     */
    public void init() {
        out.println(ColorCli.YELLOW_BOLD  +
               " ███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗\n" +
               " ████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝\n" +
               " ██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗\n" +
               " ██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝\n" +
               " ██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗\n" +
               " ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝ \n" + ColorCli.RESET);
        out.println(ColorCli.YELLOW_BOLD + "Welcome to My Shelfie game!" + ColorCli.RESET);

        selectConnection();
    }

    /**
     * used to clear the terminal
     */
    public void clearCli(){
        System.out.print("\033[H\033[2J");
        out.flush();
    }

    /**
     * asks the type of connection the client wants to use to connect to the server
     */
    public void selectConnection() {
        int connectingType = 0;
        out.println("Do you want to use Socket or RMI?");
        out.println("Digit 's' for Socket or 'r' for RMI");

        while(connectingType == 0) {
            connectingType = chooseConnectingType();
        }

        if(connectingType == 1) {
            out.println("Socket connection...");
            connectingSocket();
        }
        else if (connectingType == 2) {
            out.println("RMI connection to do...");
            //Chiamo connessione RMI
        }
    }

    /**
     * used to return the type of connection selected by the client
     * @return the int represents the type of connection selected by the client
     */
    private int chooseConnectingType() {
        String input = readLine.nextLine();
        if(input.equalsIgnoreCase("s")) return 1;
        else if(input.equalsIgnoreCase("r")) return 2;
        else {
            out.println("Please input Socket ('s') or RMI ('r')");
            return 0;
        }
    }

    /**
     * socket connection: asks the server address and port to the client
     */
    public void connectingSocket(){
        final String correctIp;
        final String correctPort;
        String inputIp;
        String inputPort;
        String defaultIp = "localhost";
        String defaultPort = "12345";
        boolean isValid = false;

        out.println("The value between the brackets is the default value.");

        do {
            out.println("Enter the server address (Default address: '" + defaultIp + "') : ");
            inputIp = readLine.nextLine();

            if (inputIp.isEmpty()) {
                isValid = true;
            } else if (ClientController.isValidAddress(inputIp)) {
                isValid = true;
            }
            else {
                out.println(STR_INPUT_ERR);
                clearCli();
            }
        } while ((!isValid));

        if(inputIp.isEmpty()) {
            correctIp = defaultIp;
        }
        else {
            correctIp = inputIp;
        }

        isValid = false;
        do {
            out.println("Enter the server port (Default port: '" + defaultPort + "') : ");
            inputPort = readLine.nextLine();

            if (inputPort.isEmpty()) {
                isValid = true;
            } else if (ClientController.isValidPort(inputPort)) {
                isValid = true;
            }
            else {
                out.println(STR_INPUT_ERR);
                clearCli();
            }
        } while ((!isValid));

        if(inputPort.isEmpty()) {
            correctPort = defaultPort;
        }
        else {
            correctPort = inputPort;
        }
        notifyObserver(obs -> obs.createConnection(correctIp, correctPort));
    }

    @Override
    public void askNickname() {
        boolean isValid;
        String nickname;
        do {
            out.println("Enter your nickname: ");
            nickname = readLine.nextLine();

            if(nickname.isEmpty()) {
                out.println(STR_INPUT_ERR);
                isValid = false;
            }
            else {
                isValid = true;
                clearCli();
            }
        } while (!isValid);

        String finalNickname = nickname;
        notifyObserver(obs -> obs.sendNickname(finalNickname));
    }

    @Override
    public void askPlayersNumber() throws NumberFormatException{
        boolean isValid = false;
        int num = 0;
        do {
            out.println("Select the desired number of players (Max. 4): ");
            try {
                num = Integer.parseInt(readLine.nextLine());
            } catch (NumberFormatException e) {
                clearCli();
            }

            if(num >= 2 && num <= 4) {
                isValid = true;
                int finalNum = num;
                notifyObserver(obs -> obs.sendNumPlayers(finalNum));
            }
            else {
                out.println(STR_INPUT_ERR);
                clearCli();
            }
        } while (!isValid);
    }

    @Override
    public void showLoginResult(boolean isNicknameAccepted, String nickname) {
        if(isNicknameAccepted) {
            out.println("Welcome "+ nickname +", you are connected to the game!");
        }
        else {
            out.println("Sorry, this nickname is already taken!");
            askNickname();
        }
    }

    @Override
    public void showGameInfo(List<Player> players, int numberPlayers) {
        out.println(ColorCli.GREEN + "MATCH INFO: " + ColorCli.RESET);
        out.print("Players connected: ( ");
        for(int i=0; i < players.size(); i++) {
            out.print(players.get(i).getNickname());
            if(i < players.size() - 1) {
                out.print(", ");
            }
        }
        out.println(" ) " +players.size() + " / " + numberPlayers);
    }

    @Override
    public void showError(String errorMessage) {
        out.println(ColorCli.RED + errorMessage + "EXIT" + ColorCli.RESET);
        System.exit(1);
    }

    @Override
    public void showWinner(Player winner) {
        out.println("Congratulations " + winner.getNickname() + " You have won the match!");
        out.println("Game finished.");
        System.exit(0);
    }

    @Override
    public void showGenericMessage(String genericMessage) {
        out.println(genericMessage);
    }

    @Override
    public void showCommonCards(List<CommonGoalCard> commonGoalCards) {
        out.println();
        out.println(ColorCli.GREEN + "COMMON GOAL CARDS OF THE MATCH ARE: " + ColorCli.RESET);
        for(CommonGoalCard c : commonGoalCards) {
            int index = commonGoalCards.indexOf(c) + 1;
            out.println("*" + index + ": " + c);
        }
    }

    @Override
    public void showPersonalCard(Player player) {
        out.println();
        out.println(ColorCli.GREEN + "YOU'RE PERSONAL GOAL CARD IS: " + ColorCli.RESET);
        PersonalGoalCard personalGoalCard = player.getPersonalGoalCard();

        out.println();
        for (int i = 0; i < 5; i++) {
            out.print("  ");
            out.format("   |%4d", i);
        }
        out.print("     |");
        out.println();
        out.println( "________________________________________________________" );

        for( int i = 0; i < 6; i++ ) {
            out.format("  %-2d ", i);
            for (int j = 0; j < 5; j++) {
                if(personalGoalCard.getMatrix()[i][j].getType() == TileType.NULL){
                    out.format("| %-7s ", " ");
                }
                else {
                    out.format("| %-7s ", personalGoalCard.getMatrix()[i][j].getType());
                }
            }
            out.println( "|" );
            out.println( "________________________________________________________" );
        }
    }

    @Override
    public void showBoard (Board board){
        out.println();
        out.println(ColorCli.GREEN + "BOARD:" + ColorCli.RESET);
        out.println();
        for (int i = 0; i < 9; i++) {
            out.print("  ");
            out.format("   |%4d", i);
        }
        out.print("     |");
        out.println();
        out.println( "_______________________________________________________________________________________________ " );

        for( int i = 0; i < 9; i++ ) {
            out.format("  %-2d ", i);
            for (int j = 0; j < 9; j++) {
                if(board.getMatrix()[i][j].isBlocked()){
                    out.format("| %-7s ", "X");
                }else {
                    if(board.getMatrix()[i][j].getType() == TileType.NULL){
                        out.format("| %-7s ", " ");
                    }
                    else {
                        out.format("| %-7s ", board.getMatrix()[i][j].getType());
                    }
                }

            }
            out.println( "|" );
            out.println( "________________________________________________________________________________________________" );
        }
    }

    @Override
    public void showBookshelf(Player player) {
        out.println();
        out.println(ColorCli.GREEN + "BOOKSHELF " + player.getNickname() + ":" +ColorCli.RESET);
        Bookshelf bookshelf = player.getBookshelf();

        out.println();
        for (int i = 0; i < 5; i++) {
            out.format("| %-7s ", i);
        }
        out.print("|");
        out.println();
        out.println( "___________________________________________________" );

        for( int i = 0; i < 6; i++ ) {
            for (int j = 0; j < 5; j++) {
                if(bookshelf.getMatrix()[i][j].getType() == TileType.NULL){
                    out.format("| %-7s ", " ");
                }
                else {
                    out.format("| %-7s ", bookshelf.getMatrix()[i][j].getType());
                }
            }
            out.println( "|" );
            out.println( "___________________________________________________" );
        }
    }

    @Override
    public void askSelectTiles(Board board) {
        boolean isValid = false;
        int num = -1;
        List<Tile> tiles = new ArrayList<>();

        out.println("Hey you have to select the tiles from the board!");
        do {
            out.println("How many tiles do you want to select (1,2 o 3 tiles)?");
            try {
                num = Integer.parseInt(readLine.nextLine());
                if(num >= 1 && num <= 3) isValid = true;
                else out.println(STR_INPUT_ERR);
            } catch (NumberFormatException e) {
                out.println(STR_INPUT_ERR);
                num = -1;
                clearCli();
            }
        } while (!isValid);

        for (int i = 0; i < num; i++) {
            isValid = false;
            int row = -1;
            int col = -1;
            int index = i + 1;
            do {
                out.println("Digit the corresponding ROW of the tile number " + index);
                try {
                    row = Integer.parseInt(readLine.nextLine());
                    if (row >= 0 && row <= 8) isValid = true;
                    else out.println(STR_INPUT_ERR);
                } catch (NumberFormatException e) {
                    row = -1;
                    isValid = false;
                    clearCli();
                }
                if(isValid) {
                    out.println("Digit the corresponding COLUMN of the tile number " + index);
                    try {
                        col = Integer.parseInt(readLine.nextLine());
                        if (col >= 0 && col <= 8) isValid = true;
                        else {
                            isValid = false;
                            out.println(STR_INPUT_ERR);
                        }
                    } catch (NumberFormatException e) {
                        col = -1;
                        isValid = false;
                        clearCli();
                    }
                }
            } while (!isValid);

            Tile tile = new Tile(board.getMatrix()[row][col].getType(), row, col);
            tiles.add(tile);
        }
        notifyObserver(obs -> obs.sendSelectTiles(tiles));
    }

    @Override
    public void askInsertTiles(Bookshelf bookshelf, List<Tile> tiles) {
        boolean isValid = false;
        int col = -1;

        out.println("Hey you have to insert the tiles in the bookshelf!");
        out.println("Please select the column where to insert the tiles: ");
        do {
            try {
                col = Integer.parseInt(readLine.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                col = -1;
                clearCli();
            }
        } while (!isValid);

        int finalCol = col;
        notifyObserver(obs -> obs.sendInsertTiles(finalCol, tiles));
    }

    @Override
    public void askOrderTiles(List<Tile> tiles) {
        boolean isValid = false;
        List<Tile> finalTiles = new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++) {
            finalTiles.add(new Tile(TileType.NULL));
        }

        out.println("Hey you have to select the order of the tiles!");
        out.println("Attention: the first tile sorted is the one placed at the bottom of the bookshelf. The count order starts from 0.");
        for (int i = 0; i < tiles.size(); i++) {
            isValid = false;
            int position = -1;
            do {
                try {
                    out.println("Digit the order number for the tile " + tiles.get(i).getType() + " :");
                    position = Integer.parseInt(readLine.nextLine());
                    if(position >= 0 && position <= tiles.size()-1 && finalTiles.get(position).getType() == TileType.NULL) {
                        finalTiles.set(position, tiles.get(i));
                        isValid = true;
                    }
                    else {
                        out.println(STR_INPUT_ERR);
                    }
                } catch (NumberFormatException e) {
                    position = -1;
                    clearCli();
                }
            } while (!isValid);
        }
        notifyObserver(obs -> obs.sendOrderTiles(finalTiles));
    }

    @Override
    public void showEndTurn() {

    }

    @Override
    public void showCommonScores(List<CommonGoalCardScore> commonGoalCardScores) throws NullPointerException {
        try {
            for (int i = 0; i<2; i++)
            {
                out.println("Stack of the Common Goal Card n." + i + ":");
                commonGoalCardScores.toString();
            }
        } catch (NullPointerException e){
            out.println("Empty Stack: there isn't any score availble");
        }
    }
    @Override
    public void showCommonGoalComplete1(CommonGoalCard commonGoalCard, int score){
        out.println("You complete the Common Goal card:"+ commonGoalCard.toString() +"score: " + score);
    }


    @Override
    public void showScores(List<Player> players) {

    }
}
