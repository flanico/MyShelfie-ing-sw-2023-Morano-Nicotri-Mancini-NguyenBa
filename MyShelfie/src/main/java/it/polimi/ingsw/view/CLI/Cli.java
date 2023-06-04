package it.polimi.ingsw.view.CLI;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class that represents the interface view via command line interface
 */
public class Cli extends ViewObservable implements View {
    private final PrintStream out;
    private static final String STR_INPUT_ERR = ColorCli.RED + "Invalid Input! Please retry." + ColorCli.RESET;
    public Scanner readLine = new Scanner(System.in);
    private boolean gameRunning;
    private final Object lock;
    private boolean myTurn;
    private BufferedReader br;
    private final ArrayList<String> buffer;
    private String finalNickname;
    private final SimpleDateFormat sdf;
    private String formattedTime;

    /**
     * constructor of the Cli
     */
    public Cli() {
        out = System.out;
        this.lock = new Object();
        this.buffer= new ArrayList<>();
        this.sdf = new SimpleDateFormat("HH:mm:ss");
    }


    /**
     * prints the welcome message of the game
     */
    public void init() {
        out.println();
        out.println(ColorCli.YELLOW_BOLD  +
                " ███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗\n" +
                " ████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝\n" +
                " ██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗\n" +
                " ██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝\n" +
                " ██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗\n" +
                " ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝ \n" + ColorCli.RESET);
        out.println(ColorCli.YELLOW_BOLD + "Welcome to My Shelfie game!" + ColorCli.RESET);

        selectConnection();
        gameRunning = true;
        myTurn = false;

        //out.println("init");
        br = new BufferedReader(new InputStreamReader(System.in));
        new Thread(() -> {
            try {
                Listener();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    /**
     * method create a new thread that listen the input of every player
     */

    public void Listener() throws IOException, InterruptedException {
        while(gameRunning){
            synchronized (lock){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //if it's my turn I have to give the lock to the other thread
                //out.println("lock myturn:"+ myTurn);
                if(myTurn){
                    //out.println("aaaaaa");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    //if it's not my turn I have to listen the client
                    while (!br.ready()) {
                        Thread.sleep(200);
                    }
                    String input = br.readLine();
                    String[] input3 = input.split(" ", 3);
                    typeInput(input3);
                }
                lock.notify();
            }
        }
    }

    /**
     * method that select the type of input requested
     */
    public void typeInput(String[] input) {
        switch (input[0]) {
            case "-chat" -> {
                String destination;
                String message;
                if (input.length != 3) {
                    out.println(ColorCli.RED+ "Destination or message is empty, please retry."+ ColorCli.RESET);

                } else {
                    destination = input[1];
                    Date now = new Date();
                    formattedTime = sdf.format(now);
                    buffer.add("[" + formattedTime + "] Message sent from [you] to [" + destination + "]: " + input[2]);

                    message = input[2];
                    notifyObserver(obs -> obs.sendChatMessage(destination, message));
                    out.println(ColorCli.YELLOW_BOLD+ "Message sent correctly." + ColorCli.RESET);
                }
                clearCli();

            }
            case "-show_chat" -> {
                if (buffer.size() == 0) {
                    out.println(ColorCli.PINK + "No messages in the chat" + ColorCli.RESET);
                } else {
                    for (String s : buffer) {
                        out.println(ColorCli.PINK + s + ColorCli.RESET);
                    }
                }
            }
            default -> {
                //default case
                out.println(STR_INPUT_ERR);
            }
        }
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
        out.print("Digit 's' for Socket or 'r' for RMI: ");

        while(connectingType == 0) {
            connectingType = chooseConnectingType();
        }

        out.println("Connection...");
        connecting(connectingType);
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
            out.print("Please input Socket ('s') or RMI ('r'): ");
            return 0;
        }
    }

    /**
     * socket connection: asks the server address and port to the client
     */
    public void connecting(int type){
        final String correctIp;
        final String correctPort;
        String inputIp;
        String inputPort;
        String defaultIp = "localhost";
        String defaultPort = "12345";   // Socket default port value
        if (type == 2)
            defaultPort = "1099";   //RMI default port value
        boolean isValid = false;

        out.println("The value between the brackets is the default value");

        do {
            out.print("Enter the server address (default: '" + defaultIp + "'): ");
            inputIp = readLine.nextLine();

            if (inputIp.isEmpty()) {
                isValid = true;
            } else if (ClientController.isValidAddress(inputIp)) {
                isValid = true;
            } else {
                out.println(STR_INPUT_ERR);
                clearCli();
            }
        } while ((!isValid));

        if (inputIp.isEmpty()) {
            correctIp = defaultIp;
        } else {
            correctIp = inputIp;
        }

        isValid = false;
        do {
            out.print("Enter the server port (default: '" + defaultPort + "'): ");
            inputPort = readLine.nextLine();

            if (inputPort.isEmpty()) {
                isValid = true;
            } else if (ClientController.isValidPort(inputPort)) {
                isValid = true;
            } else {
                out.println(STR_INPUT_ERR);
                clearCli();
            }
        } while ((!isValid));

        if (inputPort.isEmpty()) {
            correctPort = defaultPort;
        } else {
            correctPort = inputPort;
        }

        notifyObserver(obs -> obs.createConnection(correctIp, correctPort, type));
    }

    @Override
    public void askNickname() {
        boolean isValid;
        String nickname;
        do {
            out.print("Enter your nickname: ");
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

        finalNickname = nickname;
        notifyObserver(obs -> obs.sendNickname(finalNickname));
    }

    @Override
    public void askPlayersNumber() throws NumberFormatException {
        boolean isValid = false;
        int num = 0;
        do {
            out.print("Select the number of players (Max. 4): ");
            try {
                num = Integer.parseInt(readLine.nextLine());
            } catch (NumberFormatException e) {
                clearCli();
            }

            if (num >= 2 && num <= 4) {
                isValid = true;
                int finalNum = num;
                notifyObserver(obs -> obs.sendNumPlayers(finalNum));
            } else {
                out.println(STR_INPUT_ERR);
                clearCli();
            }
        } while (!isValid);
    }

    @Override
    public void showLoginResult(boolean isNicknameAccepted, boolean isConnectionSuccessful, String nickname) {
        if (isNicknameAccepted && isConnectionSuccessful) {
            out.println(ColorCli.YELLOW_BOLD + "\nWelcome " + nickname + ", you are connected to the game!" + ColorCli.RESET);
        } else if (!isNicknameAccepted && isConnectionSuccessful) {
            out.println(ColorCli.RED + "Sorry, this nickname is not valid!" + ColorCli.RESET);
            askNickname();
        } else if (isNicknameAccepted) {
            out.println(ColorCli.RED + "Sorry, the game lobby is full! \nEXIT" + ColorCli.RESET);
            System.exit(0);
        } else {
            out.println(ColorCli.RED + "Server impossible to reach! \nEXIT" + ColorCli.RESET);
            System.exit(1);
        }
    }

    @Override
    public void showGameInfo(List<Player> players, int numberPlayers) {
        out.println(ColorCli.BLUE_INFO + "\nMATCH INFO: " + ColorCli.RESET);
        out.print("Players connected: ( ");
        for (int i = 0; i < players.size(); i++) {
            out.print(players.get(i).getNickname());
            if (i < players.size() - 1) {
                out.print(", ");
            }
        }
        out.println(" ) " + players.size() + " / " + numberPlayers);
    }

    @Override
    public void showError(String errorMessage) {
        out.println(ColorCli.RED + errorMessage + " EXIT" + ColorCli.RESET);
        System.exit(1);
    }

    @Override
    public void showWinner(String winner) {
        out.println(ColorCli.YELLOW_BOLD + "Congratulations to " + winner + "! You have won the match!" + ColorCli.RESET);
        out.println(ColorCli.YELLOW_BOLD + "Game finished..." + ColorCli.RESET);
        System.exit(0);
    }

    @Override
    public void showGenericMessage(String genericMessage) {
        if (genericMessage.contains("It's your turn")) {
            myTurn = true;
            //out.println("true:showgeneric");
        } else {
            myTurn = false;
            //out.println("false:showgeneric");
        }

        out.println(genericMessage);
    }

    @Override
    public void showCommonCards(List<CommonGoalCard> commonGoalCards) {
        myTurn = false;
        //out.println("false:showcommon");
        out.println();
        out.println(ColorCli.BLUE_INFO + "COMMON GOAL CARDS OF THE MATCH: " + ColorCli.RESET);
        for (CommonGoalCard c : commonGoalCards) {
            int index = commonGoalCards.indexOf(c) + 1;
            out.println(ColorCli.BLUE_INFO + "*" + index + " " + ColorCli.RESET + c);
        }
    }

    @Override
    public void showPersonalCard(Player player) {
        out.println(ColorCli.BLUE_INFO + "\nPERSONAL GOAL CARD: " + ColorCli.RESET);
        PersonalGoalCard personalGoalCard = player.getPersonalGoalCard();

        out.print("   ");
        for (int i = 0; i < 5; i++) {
            out.print(ColorCli.BROWN_BOOK + "   " + i + ColorCli.RESET);
        }
        out.println();
        out.println(ColorCli.BROWN_BOOK + "    ┌───┬───┬───┬───┬───┐" + ColorCli.RESET);
        for (int i = 0; i < 6; i++) {
            out.print(ColorCli.BROWN_BOOK + " " + i + "  " + ColorCli.RESET);
            for (int j = 0; j < 5; j++) {
                if (personalGoalCard.getMatrix()[i][j].getType() == TileType.NULL) {
                    out.print(ColorCli.BROWN_BOOK + "│   " + ColorCli.RESET);
                } else {
                    out.print(ColorCli.BROWN_BOOK + "│" + ColorCli.RESET + personalGoalCard.getMatrix()[i][j].toString());
                }
            }
            out.println(ColorCli.BROWN_BOOK + "│" + ColorCli.RESET);
            if (i != 5) out.println(ColorCli.BROWN_BOOK + "    ├───┼───┼───┼───┼───┤" + ColorCli.RESET);
        }
        out.println(ColorCli.BROWN_BOOK + "    └───┴───┴───┴───┴───┘" + ColorCli.RESET);
    }

    @Override
    public void showBoard(Board board) {
        out.println();
        out.println(ColorCli.BLUE_INFO + "BOARD:" + ColorCli.RESET);
        out.print("     ");
        for (int i = 0; i < 9; i++) {
            out.print(ColorCli.GREEN_BOARD + i + "   " + ColorCli.RESET);
        }
        out.println();
        out.print(ColorCli.GREEN_BOARD + "  ╔═════════════════════════════════════╗\n" + ColorCli.RESET);

        for (int i = 0; i < 9; i++) {
            out.print(ColorCli.GREEN_BOARD + i + " ║ " + ColorCli.RESET);
            for (int j = 0; j < 9; j++) {
                if (board.getMatrix()[i][j].isBlocked()) {
                    out.print(ColorCli.BLACK + " X " + ColorCli.RESET + " ");
                } else {
                    if (board.getMatrix()[i][j].getType() == TileType.NULL) out.print("    ");
                    else out.print(board.getMatrix()[i][j].toString() + " ");
                }

            }
            out.print(ColorCli.GREEN_BOARD + "║\n" + ColorCli.RESET);
        }
        out.print(ColorCli.GREEN_BOARD + "  ╚═════════════════════════════════════╝\n" + ColorCli.RESET);
    }

    @Override
    public void showBookshelf(Player player) {
        out.println();
        out.println(ColorCli.BLUE_INFO + "BOOKSHELF " + player.getNickname() + ":" + ColorCli.RESET);
        Bookshelf bookshelf = player.getBookshelf();

        out.print("  ");
        for (int i = 0; i < 5; i++) {
            out.print(ColorCli.BROWN_BOOK + i + "   " + ColorCli.RESET);
        }
        out.println();
        out.println(ColorCli.BROWN_BOOK + "╔═══╦═══╦═══╦═══╦═══╗" + ColorCli.RESET);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (bookshelf.getMatrix()[i][j].getType() == TileType.NULL) {
                    out.print(ColorCli.BROWN_BOOK + "║   " + ColorCli.RESET);
                } else {
                    out.print(ColorCli.BROWN_BOOK + "║" + ColorCli.RESET + bookshelf.getMatrix()[i][j].toString());
                }
            }
            out.println(ColorCli.BROWN_BOOK + "║" + ColorCli.RESET);
            if (i != 5) out.println(ColorCli.BROWN_BOOK + "╠═══╬═══╬═══╬═══╬═══╣" + ColorCli.RESET);
        }
        out.println(ColorCli.BROWN_BOOK + "╚═══╩═══╩═══╩═══╩═══╝" + ColorCli.RESET);
    }

    @Override
    public void askSelectTiles(Board board, Bookshelf bookshelf) throws NumberFormatException {
        boolean isValid = false;
        String input;
        String[] inputSplit;
        int num = -1;
        List<Tile> tiles = new ArrayList<>();
        int maxTiles = 0;
        myTurn = true;
        //out.println("askeSelect:" + myTurn);

        out.println(ColorCli.YELLOW_BOLD + "Hey you have to select the tiles from the board! Digit in this order: \n-number of tiles \n-first tile's row \n-first tile's column \n-second tile's row \n-second tile's column \n-third tile's row \n-third tile's column3" + ColorCli.RESET);
        do {
            input = readLine.nextLine();
            inputSplit = input.split(" ");
            try {
                num = Integer.parseInt(inputSplit[0]);
                if (num >= 1 && num <= 3) {
                    if (num <= board.maxTilesBoard()) {
                        maxTiles = bookshelf.maxTilesBookshelf();
                        if (num > maxTiles) {
                            out.println(ColorCli.RED + "You don't have enough space in your bookshelf. You can select MAX " + maxTiles + " tiles. Please retry." + ColorCli.RESET);
                        } else {
                            if (num == (inputSplit.length / 2)) {
                                //out.println(inputSplit.length / 2);
                                    for (int i = 1; i < inputSplit.length; i++) {
                                        try {
                                            int cell = Integer.parseInt(inputSplit[i]);
                                            if (cell >= 0 && cell < 9) {
                                                isValid = true;
                                            } else {
                                                out.println(ColorCli.RED + "You have to select a cell between 0 and 8. Please retry." + ColorCli.RESET);
                                                isValid = false;
                                                break;
                                            }
                                        }
                                        catch (NumberFormatException e){
                                            out.println(STR_INPUT_ERR);
                                            isValid = false;
                                            clearCli();
                                            break;
                                        }
                                    }
                            }
                            else{
                                out.println(STR_INPUT_ERR);
                            }
                        }
                    } else {
                        out.println(ColorCli.RED + "There isn't enough removable tiles on the board. You can select MAX " + board.maxTilesBoard() + " tiles. Please retry." + ColorCli.RESET);
                    }
                } else out.println(STR_INPUT_ERR);
            } catch (NumberFormatException e) {
                out.println(STR_INPUT_ERR);
                clearCli();
            }
        } while (!isValid);

        //valid input, select tiles
        int row = -1;
        int col = -1;
        for (int i = 1; i < inputSplit.length; i += 2) {
            //out.println("sono nel ciclo");
            row = Integer.parseInt(inputSplit[i]);
            col = Integer.parseInt(inputSplit[i + 1]);
            //out.println("row: " + row + " col: " + col);

            Tile tile = new Tile(board.getMatrix()[row][col].getType(), board.getMatrix()[row][col].getColortype(), row, col);
            tiles.add(tile);
        }
        notifyObserver(obs -> obs.sendSelectTiles(tiles));
    }

    @Override
    public void askInsertTiles(Bookshelf bookshelf, List<Tile> tiles) throws NumberFormatException {
        boolean isValid = false;
        int col = -1;

        out.println(ColorCli.YELLOW_BOLD + "Hey you have to insert the tiles in the bookshelf!" + ColorCli.RESET);
        out.print("Please select the column where to insert the tiles: ");
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
    public void askOrderTiles(List<Tile> tiles) throws NumberFormatException {
        boolean isValid = false;
        List<Tile> finalTiles = new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++) {
            finalTiles.add(new Tile(TileType.NULL, 1));
        }
        if (tiles.size() != 1) {
            out.println(ColorCli.YELLOW_BOLD + "Hey you have to select the order of the tiles!" + ColorCli.RESET);
            out.println("Attention: the first tile sorted is the one placed at the bottom of the bookshelf. The count order starts from 0.");
            for (int i = 0; i < tiles.size(); i++) {
                isValid = false;
                int position = -1;
                do {
                    try {
                        out.print("Digit the order number for the tile " + tiles.get(i).toString() + " : ");
                        position = Integer.parseInt(readLine.nextLine());
                        if (position >= 0 && position <= tiles.size() - 1 && finalTiles.get(position).getType() == TileType.NULL) {
                            finalTiles.set(position, tiles.get(i));
                            isValid = true;
                        } else {
                            out.println(STR_INPUT_ERR);
                        }
                    } catch (NumberFormatException e) {
                        position = -1;
                        clearCli();
                    }
                } while (!isValid);
            }
        } else {
            finalTiles.set(0, tiles.get(0));
        }
        notifyObserver(obs -> obs.sendOrderTiles(finalTiles));
    }

    @Override
    public void showCommonScores(List<CommonGoalCardScore> commonGoalCardScores) {
        int index = 1;
        for (int i = 0; i < 2; i++) {
            out.println(ColorCli.BLUE_INFO + "\nSCORES OF THE COMMON GOAL CARD " + index + ": " + ColorCli.RESET);
            for (Integer score : commonGoalCardScores.get(i).getStack()) {
                out.print(ColorCli.BLUE_INFO + score + " " + ColorCli.RESET);
            }
            index++;
        }
        out.println();
    }

    @Override
    public void showCommonGoalComplete(CommonGoalCard commonGoalCard, int score) {
        out.println(ColorCli.YELLOW_BOLD + "You have completed " + commonGoalCard + "\nScore: " + score + ColorCli.RESET);
    }

    @Override
    public void disconnection(String nickname, boolean isStarted) {
        out.print(ColorCli.CYAN_BOLD + "\n" + nickname + " has been disconnecting from the game!" + ColorCli.RESET);
        if (!isStarted) {
            out.println("\nGame ended...");
            System.exit(1);
            gameRunning = false;
        }
    }

    @Override
    public void showScores(Map<String, Integer> playerScore) {
        out.println(ColorCli.BLUE_INFO + "\nRANK SCORES OF THE GAME: " + ColorCli.RESET);
        int position = 1;
        for (String player : playerScore.keySet()) {
            out.println(position + "- " + player + ": " + playerScore.get(player));
            position++;
        }
    }

    /**
     * add a message to the buffer of the player's chat
     *
     * @param sender      sender of the message
     * @param destination destination of the message
     * @param message     message
     */
    @Override
    public void addChatMessage(String sender, String destination, String message) {
        //the message is for me
        if (!sender.equals(finalNickname) && (destination.equals("all") || destination.equals(finalNickname))) {
            //out.println("nuovo mex");
            Date now = new Date();
            formattedTime = sdf.format(now);
            //out.println("before buffer message: " + message);
            message = "[" + formattedTime + "] Message sent from ["+ sender +"] to [" + destination + "]: " + message;
            //out.println("buffer message: " + message);
            buffer.add(message);
        }
    }
}
