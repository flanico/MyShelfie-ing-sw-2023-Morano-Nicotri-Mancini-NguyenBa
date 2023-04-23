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
    private static final String STR_INPUT_ERR = "\u001b[31;1m Invalid Input! \u001b[0m";
    public Cli() {
        out = System.out;
    }

    public Scanner readLine = new Scanner(System.in);

    public void init() {
        out.println("" +
               " ███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗\n" +
               " ████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝\n" +
               " ██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗\n" +
               " ██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝\n" +
               " ██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗\n" +
               " ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝ \n");
        out.println("Welcome to My Shelfie game!");

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
    public void showGameInfo(ArrayList<Player> players, int numberPlayers) {
        out.println("MATCH INFO: ");
        out.print("Players connected: ( ");
        for(int i=0; i < players.size(); i++) {
            out.print(players.get(i).getNickname());
            if(i < players.size() - 1) {
                out.println(", ");
            }
        }
        out.println(" ) " +players.size() + " / " + numberPlayers);
    }

    @Override
    public void showError(String errorMessage) {
        out.println("\u001b[31;1m" + errorMessage + "\u001b[0m \n" + "EXIT");
        System.exit(1);
    }

    @Override
    public void showWinner(Player winner) {
        out.println("Congratulations " +winner.getNickname() + " You have won the match!");
        out.println("Game finished.");
        System.exit(0);
    }

    @Override
    public void showGenericMessage(String genericMessage) {
        out.println(genericMessage);
    }

    @Override
    public void showCommonCards(List<CommonGoalCard> commonGoalCards) {
        out.println("COMMON GOAL CARDS OF THE MATCH ARE: ");
        for(CommonGoalCard c : commonGoalCards) {
            int index = commonGoalCards.indexOf(c) + 1;
            out.println(index + ": " + c);
        }
    }

    @Override
    public void showPersonalCard(Player player) {
        out.println("YOU'RE PERSONAL GOAL CARD IS: ");
        PersonalGoalCard personalGoalCard = player.getPersonalGoalCard();

        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print("  ");
            System.out.format("   |%4d", i);
        }
        System.out.print("     |");
        System.out.println();
        System.out.println( "________________________________________________________" );

        for( int i = 0; i < 6; i++ ) {
            System.out.format("  %-2d ", i);
            for (int j = 0; j < 5; j++) {
                if(personalGoalCard.getMatrix()[i][j].getType() == TileType.NULL){
                    System.out.format("| %-7s ", " ");
                }
                else {
                    System.out.format("| %-7s ", personalGoalCard.getMatrix()[i][j].getType());
                }
            }
            System.out.println( "|" );
            System.out.println( "_______________________________________________________ " );
        }
    }

    @Override
    public void showBoard (Board board){
        out.println("BOARD:");
        for (int i = 0; i < 9; i++) {
            System.out.print("  ");
            System.out.format("   |%4d", i);
        }
        System.out.print("     |");
        System.out.println();
        System.out.println( "_______________________________________________________________________________________________ " );

        for( int i = 0; i < 9; i++ ) {
            System.out.format("  %-2d ", i);
            for (int j = 0; j < 9; j++) {
                if(board.getMatrix()[i][j].isBlocked()){
                    System.out.format("| %-7s ", "X");
                }else {
                    if(board.getMatrix()[i][j].getType() == TileType.NULL){
                        System.out.format("| %-7s ", " ");
                    }
                    else {
                        System.out.format("| %-7s ", board.getMatrix()[i][j].getType());
                    }
                }

            }
            System.out.println( "|" );
            System.out.println( "_______________________________________________________________________________________________ " );
        }

    }
}
