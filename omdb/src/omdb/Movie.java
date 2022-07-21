//By Robin
package omdb;

import java.util.Comparator;

// By Robin Rai :)

public class Movie implements Comparable<Movie> {                               //implements a comparable method, that can only compare movies

    private final String allFilmItems;
    private final String filmName;
    private final String filmYear;
    private final int    intFilmYear;
    private final String filmAgeRating;
    private final String filmGenre;
    private final String filmDuration;
    private final int    intFilmDuration;
    private final String filmUserRating;                                        //These two aren't used but are here in case they'll be used in the future for things
    private final int    intFilmUserRating;                                     //like sorting by them and whatnot.
    private final int    intFilmNameLength;
    

    public Movie(String allFilmItems) {                                         //gets a big ol string from moviedatabase.java
        
        this.allFilmItems       = allFilmItems;
        String[] filmArray      = this.allFilmItems.split("\"");                //splits by a "
        filmName                = filmArray[1];
        filmGenre               = filmArray[5];

        filmYear                = filmArray[2].replace(",", "");                //gets rid of the comma.
        filmAgeRating           = filmArray[3];

        String filmDurationUser = filmArray[6];
        String[] filmArray2     = filmDurationUser.split(",");                  //splits the remaining combined strings by a ,
        filmDuration            = filmArray2[1];
        filmUserRating          = filmArray2[2];

        intFilmYear             = Integer.parseInt(filmYear);                   //makes int versions
        intFilmDuration         = Integer.parseInt(filmDuration);
        intFilmUserRating       = Integer.parseInt(filmUserRating);
        
        intFilmNameLength       = filmName.length();                            //makes an in based off of the name length
        
    }

    @Override
    public String toString() {
        return this.allFilmItems;                                               //toString for all filmdetails of a movie
    }
    public int getFilmYear() {                                                  //accessors
        return intFilmYear;
    }
    public String getFilmGenre() {
        return filmGenre;
    }
    public String getFilmRating() {
        return filmAgeRating;
    }
    public int getFilmNameLength() {
        return intFilmNameLength;
    }
    public int getFilmDuration() {
        return intFilmDuration;
    }
    
    
    @Override
    public int compareTo(Movie other) {
        return Integer.compare(other.getFilmYear(), this.getFilmYear());        //comparable, compares two movies at a time by year, returning 1,-1, or 0 based on weather not the first movies older, younger or the same.
    }

    
}
class SortByDuration implements Comparator<Movie> {                             //implements a comparator method that can only compare movies

        @Override
        public int compare(Movie a, Movie b) {
            return b.getFilmDuration() - a.getFilmDuration();                   //comparator that compares filmduration ints of two movies
        }
    }

class SortByNameLength implements Comparator<Movie> {

        @Override
        public int compare(Movie a, Movie b) {
            return b.getFilmNameLength() - a.getFilmNameLength();
        }
    }

class SortByYear implements Comparator<Movie> {                                 //made another year sorter so it can sort backwards.

        @Override
        public int compare(Movie a, Movie b) {
            return b.getFilmYear() - a.getFilmYear();
        }
    }
