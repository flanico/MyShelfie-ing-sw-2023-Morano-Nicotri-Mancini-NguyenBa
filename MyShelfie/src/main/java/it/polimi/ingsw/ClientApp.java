package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.view.Cli;

import java.util.Scanner;

/**
 * main of the client app
 */
public class ClientApp {
    public static void main(String[] args) {

        System.out.println("Choose the modality: 'c' for CLI or 'g' for GUI");
        Scanner scanner = new Scanner(System.in);
        String response;
        response = scanner.nextLine();

        if(response.equalsIgnoreCase("c")) {
            Cli view = new Cli();
            ClientController clientController = new ClientController(view);
        }
        else if (response.equalsIgnoreCase("g")) {
            //Lancio la GUI
        }
        else {
            System.err.println("Invalid argument! Please run the executable again");
        }
    }
}