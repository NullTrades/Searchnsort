/**
 * Title: Main.java
 * Author: Osy Okocha
 * Date: 5/17/2024
 */

import java.util.Scanner;
import java.util.ArrayList;


/**
 * The main class to run the board game management program.
 */
public class Main {
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    ProgramTimer wait = new ProgramTimer();

    /**
     * Displays the start screen of the program.
     */
    private static void startScreen() {
        System.out.println(YELLOW + "Welcome to the Board Game Collection Manager!");
        ProgramTimer.wait(1);
        System.out.println("This program allows you to manage a collection of board games.");
        ProgramTimer.wait(1);
        System.out.println("You can add new games, search for games by title, display all games sorted by a criteria, and remove games.");
        ProgramTimer.wait(1);
        System.out.println("Let's get started!");
        System.out.println("\n---------------------------------------------------------------------------------------------" + RESET);
        ProgramTimer.wait(1);
    }

    /**
     * Prints the menu options for the user.
     */
    public static void printMenu() {
        System.out.println("\n1. Add a new game");
        System.out.println("2. Search for a game by title");
        System.out.println("3. Display all games sorted by a criteria");
        System.out.println("4. Remove a game");
        System.out.println("5. Save and Exit");
        System.out.println("6. Reset to original CSV file");
    }

    /**
     * The main function to run the board game management program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BoardGameCollection collection = new BoardGameCollection();

        collection.setFileNames("C:\\Users\\jmook\\IdeaProjects\\SearchnSort\\src\\GameCollection.csv");

        if (collection.isAdjustedCSVPresent()) {
            System.out.print("Load adjusted CSV file? (yes/no): ");
            String loadAdjusted = scanner.nextLine();
            if (loadAdjusted.equalsIgnoreCase("yes")) {
                collection.readGamesFromCSV("C:\\Users\\jmook\\IdeaProjects\\SearchnSort\\src\\GameCollection - adjusted.csv");
            } else {
                collection.readGamesFromCSV("C:\\Users\\jmook\\IdeaProjects\\SearchnSort\\src\\GameCollection.csv");
            }
        } else {
            collection.readGamesFromCSV("C:\\Users\\jmook\\IdeaProjects\\SearchnSort\\src\\GameCollection.csv");
        }

        startScreen();
        while (true) {
            printMenu();
            System.out.print(GREEN + "Enter your choice: " + RESET);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the rating: ");
                    float rating = Float.parseFloat(scanner.nextLine());
                    System.out.print("Enter the difficulty: ");
                    float difficulty = Float.parseFloat(scanner.nextLine());
                    System.out.print("Enter the number of players: ");
                    int players = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter the time: ");
                    int time = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter the year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter the genre: ");
                    String genre = scanner.nextLine();
                    BoardGame newGame = new BoardGame(name, rating, difficulty, players, time, year, genre);
                    collection.addGame(newGame);
                    break;

                case "2":
                    System.out.print("Enter the name to search for: ");
                    String searchName = scanner.nextLine();
                    ArrayList<BoardGame> matchedGames = collection.searchByTitle(searchName);
                    if (!matchedGames.isEmpty()) {
                        System.out.println("Found games:");
                        collection.printGames(matchedGames);
                    } else {
                        System.out.println("No games found");
                    }
                    break;

                case "3":
                    System.out.print("Enter the criteria to sort by (name, rating, difficulty, players, time, year, genre): ");
                    String criteria = scanner.nextLine();
                    collection.sortGames(criteria);
                    collection.printGames(collection.getGames());
                    break;

                case "4":
                    System.out.print("Enter the name of the game to remove: ");
                    String removeName = scanner.nextLine();
                    boolean removed = collection.removeGame(removeName);
                    if (removed) {
                        System.out.println("Game removed successfully.");
                    } else {
                        System.out.println("Game not found.");
                    }
                    break;

                case "5":
                    collection.saveAdjustedGamesToCSV();
                    scanner.close();
                    return;

                case "6":
                    collection.resetToOriginal();
                    System.out.println("Reset to original CSV data.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}