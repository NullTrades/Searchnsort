/**
*  Title: BoardGameCollection.java
*  Author: Osy Okocha
*  Date: 5/17/2024
*/



import java.io.*;
import java.util.ArrayList;



/**
 * Represents a collection of board games.
 */
public class BoardGameCollection {
    private ArrayList<BoardGame> games;
    private String originalFileName;
    private String adjustedFileName;
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    /**
     * Constructs an empty BoardGameCollection.
     */
    public BoardGameCollection() {
        games = new ArrayList<>();
    }

    /**
     * Sets the original file name and initializes the adjusted file name.
     *
     * @param fileName The name of the original CSV file.
     */
    public void setFileNames(String fileName) {
        this.originalFileName = fileName;
        this.adjustedFileName = fileName.replace(".csv", " - adjusted.csv");
    }

    /**
     * Reads board games from a CSV file and adds them to the collection.
     *
     * @param fileName The name of the CSV file to read from.
     */
    public void readGamesFromCSV(String fileName) {
        setFileNames(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                BoardGame game = new BoardGame(
                        values[0],
                        Float.parseFloat(values[1]),
                        Float.parseFloat(values[2]),
                        Integer.parseInt(values[3]),
                        Integer.parseInt(values[4]),
                        Integer.parseInt(values[5]),
                        values[6]
                );
                games.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current collection of board games to a CSV file.
     *
     * @param fileName The name of the CSV file to write to.
     */
    public void saveGamesToCSV(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("Name,Rating,Difficulty,Players,Time,Year,Genre\n");
            for (BoardGame game : games) {
                bw.write(game.getName() + "," + game.getRating() + "," + game.getDifficulty() + ","
                        + game.getPlayers() + "," + game.getTime() + "," + game.getYear() + "," + game.getGenre() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current collection of board games to the adjusted CSV file.
     */
    public void saveAdjustedGamesToCSV() {
        saveGamesToCSV(this.adjustedFileName);
    }

    /**
     * Resets the collection to the original CSV file.
     */
    public void resetToOriginal() {
        games.clear();
        readGamesFromCSV(this.originalFileName);
    }

    /**
     * Checks if the adjusted CSV file exists.
     *
     * @return true if the adjusted CSV file exists, false otherwise.
     */
    public boolean isAdjustedCSVPresent() {
        File adjustedFile = new File(this.adjustedFileName);
        return adjustedFile.exists();
    }

    /**
     * Adds a new board game to the collection.
     *
     * @param game The board game to add.
     */
    public void addGame(BoardGame game) {
        games.add(game);
    }

    /**
     * Removes a board game from the collection by its title.
     *
     * @param title The title of the board game to remove.
     * @return true if the game was removed, false otherwise.
     */
    public boolean removeGame(String title) {
        return games.removeIf(game -> game.getName().equalsIgnoreCase(title));
    }

    /**
     * Sorts the board games in the collection based on the given criteria using insertion sort.
     *
     * @param criteria The criteria to sort by ('name', 'rating', 'difficulty', 'players', 'time', 'year', 'genre').
     */
    public void sortGames(String criteria) {
        for (int i = 1; i < games.size(); i++) {
            BoardGame key = games.get(i);
            int j = i - 1;

            switch (criteria) {
                case "name":
                    while (j >= 0 && games.get(j).getName().compareTo(key.getName()) > 0) {
                        games.set(j + 1, games.get(j));
                        j = j - 1;
                    }
                    break;
                case "rating":
                    while (j >= 0 && games.get(j).getRating() > key.getRating()) {
                        games.set(j + 1, games.get(j));
                        j = j - 1;
                    }
                    break;
                case "difficulty":
                    while (j >= 0 && games.get(j).getDifficulty() > key.getDifficulty()) {
                        games.set(j + 1, games.get(j));
                        j = j - 1;
                    }
                    break;
                case "players":
                    while (j >= 0 && games.get(j).getPlayers() > key.getPlayers()) {
                        games.set(j + 1, games.get(j));
                        j = j - 1;
                    }
                    break;
                case "time":
                    while (j >= 0 && games.get(j).getTime() > key.getTime()) {
                        games.set(j + 1, games.get(j));
                        j = j - 1;
                    }
                    break;
                case "year":
                    while (j >= 0 && games.get(j).getYear() > key.getYear()) {
                        games.set(j + 1, games.get(j));
                        j = j - 1;
                    }
                    break;
                case "genre":
                    while (j >= 0 && games.get(j).getGenre().compareTo(key.getGenre()) > 0) {
                        games.set(j + 1, games.get(j));
                        j = j - 1;
                    }
                    break;
                // Add other sorting criteria as needed
            }
            games.set(j + 1, key);
        }
    }

    /**
     * Searches for a board game by its title using binary search with partial match.
     *
     * @param title The title or partial title of the board game to search for.
     * @return A list of board games that contain the given title as a substring.
     */
    public ArrayList<BoardGame> searchByTitle(String title) {
        ArrayList<BoardGame> matchedGames = new ArrayList<>();
        title = title.toLowerCase();
        for (BoardGame game : games) {
            if (game.getName().toLowerCase().contains(title)) {
                matchedGames.add(game);
            }
        }
        return matchedGames;
    }

    /**
     * Gets the list of board games in the collection.
     *
     * @return The list of board games.
     */
    public ArrayList<BoardGame> getGames() {
        return games;
    }

    /**
     * Prints the list of board games with a header row.
     *
     * @param games The list of board games to print.
     */
    public void printGames(ArrayList<BoardGame> games) {
        System.out.printf("%-55s %-8s %-10s %-7s %-5s %-5s %-15s\n",YELLOW + "Name", "Rating", "Difficulty", "Players", "Time", "Year", "Genre");
        System.out.println("---------------------------------------------------------------------------------------------" + RESET);
        for (BoardGame game : games) {
            System.out.printf("%-55s %-8.2f %-10.2f %-7d %-5d %-5d %-15s\n",
                    GREEN + game.getName() + RESET, game.getRating(), game.getDifficulty(), game.getPlayers(), game.getTime(), game.getYear(), game.getGenre());
        }
    }
}
