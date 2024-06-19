/**

    Title: BoardGame.java
    Author: Osy Okocha
    Date: 5/17/2024

*/

/**
 * Represents a board game with various attributes.
 */
public class BoardGame {
    private String name;
    private float rating;
    private float difficulty;
    private int players;
    private int time;
    private int year;
    private String genre;

    /**
     * Constructs a BoardGame object.
     *
     * @param name       The name of the board game.
     * @param rating     The rating of the board game.
     * @param difficulty The difficulty level of the board game.
     * @param players    The number of players for the board game.
     * @param time       The time duration to play the board game.
     * @param year       The year the board game was released.
     * @param genre      The genre of the board game.
     */
    public BoardGame(String name, float rating, float difficulty, int players, int time, int year, String genre) {
        this.name = name;
        this.rating = rating;
        this.difficulty = difficulty;
        this.players = players;
        this.time = time;
        this.year = year;
        this.genre = genre;
    }

    // Getters and Setters for each attribute
    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public int getPlayers() {
        return players;
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return String.format("%-30s %-8.2f %-10.2f %-7d %-5d %-5d %-15s", name, rating, difficulty, players, time, year, genre);
    }
}
