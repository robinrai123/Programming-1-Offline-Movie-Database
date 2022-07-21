package omdb;

// By Robin Rai :)

public class Omdb {

    public static void main(String[] args) {
        MovieDatabase filmItem = new MovieDatabase();
        filmItem.fileScanner();
        filmItem.sortByYear();
        filmItem.sortByDuration();
        filmItem.filterGenre("Film-Noir");
        filmItem.filterAgeRating("UNRATED");
        filmItem.sortByNameLength();
    }
    
}
