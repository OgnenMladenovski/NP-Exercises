package Collections.LaboratoryExercises;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Movie {
    private String title;
    private String genre;
    private int year;
    private double avgRating;

    public Movie(String title, String genre, int year, double avgRating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.avgRating = avgRating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getAvgRating() {
        return avgRating;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", title, genre, year, avgRating);
    }
}

class MovieTheater {
    private ArrayList<Movie> movies;

    public MovieTheater() {
        this.movies = new ArrayList<>();
    }

    public void readMovies(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int n = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            String title = br.readLine();
            String genre = br.readLine();
            int year = Integer.parseInt(br.readLine());
            String[] rating = br.readLine().trim().split("\\s+");
            double counter = 0;
            double sum = 0;
            for (int j = 0; j < rating.length; j++) {
                counter++;
                sum += Double.parseDouble(rating[j]);
            }
            double average =  sum / counter;
            Movie new_movie = new Movie(title, genre, year, average);
            movies.add(new_movie);
        }
    }

    public void printByGenreAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getGenre).thenComparing(Movie::getTitle))
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    public void printByYearAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getYear).thenComparing(Movie::getTitle))
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    public void printByRatingAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getAvgRating).reversed().thenComparing(Movie::getTitle))
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    @Override
    public String toString() {
        return movies.toString();
    }
}

public class MovieTheaterTester {
    public static void main(String[] args) {
        MovieTheater mt = new MovieTheater();
        try {
            mt.readMovies(System.in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("SORTING BY RATING");
        mt.printByRatingAndTitle();
        System.out.println("\nSORTING BY GENRE");
        mt.printByGenreAndTitle();
        System.out.println("\nSORTING BY YEAR");
        mt.printByYearAndTitle();
    }
}
