//By Robin
package omdb;

//By Robin Rai :)

import java.io.*;
import java.util.*;

public class MovieDatabase {

    private final List<Movie> filmArrayList;                                    //array list of movies
    private List<Movie> genreArrayList;
    private List<Movie> AgeRatingArrayList;
    String genreToFilter;
    String ratingToFilter;

    public MovieDatabase() {
        this.filmArrayList = new ArrayList<>();
    }

    public void fileScanner() {
        try {                                                                   //try reading the file located at filepath, if it can't catch it and complain
            String filePath = "src/omdb/films.txt";
            File filmFile = new File(filePath);
            Scanner filmScanner = new Scanner(filmFile);
            do {                                                                
                String newLine = filmScanner.nextLine();                        //scans a line from filmFile
                Movie temp = new Movie(newLine);                                //makes a new temp movie object with it
                filmArrayList.add(temp);                                        //appends the movie object to the filmArrayLsit

            } while (filmScanner.hasNext());                                    //keep doing the do part as long as the scanner still has stuff to scan

        } catch (FileNotFoundException e) {                                     
            System.out.println("Yo something went wrong");
        }                                                                       
    }

    public void sortByYear() {
        Collections.sort(filmArrayList);                                        //uses comparable in movie.java which compares two movies at a time. what value it gets determins where it'll put those two things back in the list
        System.out.println("Movies sorted chronologically:\n");
        filmArrayList.forEach(System.out::println);                             //for each item in the array print it out on a new line. Same thing used later
    }

    public void sortByDuration() {
        Collections.sort(filmArrayList, new SortByDuration());                  //uses comparator specified to sort by duration instead of comparable and by year used above
        //System.out.println("Movies sorted by duration: ");
        //filmArrayList.forEach(System.out::println);
    }
    
    public void sortByNameLength(){
        Collections.sort(filmArrayList, new SortByNameLength());                //same as above
        //System.out.println("Movies sorted by name length: ");
        //filmArrayList.forEach(System.out::println);
        
        System.out.println("\nThe film with the longest name is: "
                + filmArrayList.get(0));
    }
    
    public void filterGenre(String genre) {
        genreToFilter = genre;
        this.genreArrayList = new ArrayList<>();
        //filmArrayList.forEach(if(this.toString().contains(genre));
        for (Movie film : filmArrayList) {                                      //for every movie in the filmArrayList, 
            if (film.getFilmGenre().contains(genre)) {                          //if it contains a specified genre for the genre, 
                genreArrayList.add(film);                                       //add it to a new arraylist.
            }
        }
        
        Collections.sort(genreArrayList, new SortByDuration());                 //sorts it by duration
        //System.out.println("Movies that are "+ genreToFilter);
        //genreArrayList.forEach(System.out::println);
        
        System.out.println("\nThe third longest "+ genreToFilter+" is: "
                + genreArrayList.get(2));
    }
    
    public void filterAgeRating(String rating) {
        ratingToFilter = rating;
        this.AgeRatingArrayList = new ArrayList<>();
        for (Movie film : filmArrayList) {                                      //same as above
            if (film.getFilmRating().contains(rating)) {
                AgeRatingArrayList.add(film);
            }
        }
        
        Collections.sort(AgeRatingArrayList, new SortByYear());                 //uses comparator version of the year sorter, since its in the opposite order
        //System.out.println("Movies that are "+ ratingToFilter);
        //AgeRatingArrayList.forEach(System.out::println);
        
        System.out.println("\nThe eighth most recent "+ ratingToFilter+" is: "
                + AgeRatingArrayList.get(7));
    }
}
