package com.example.demo8;

public class Film { private String no;
    private String title;
    private String year;
    private String genre;
    private String origin;
    private String director;
    private String star;
    private String imdbLink;

    public Film(String no, String title, String year, String genre, String origin, String director, String star, String imdbLink) {
        this.no = no;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.origin = origin;
        this.director = director;
        this.star = star;
        this.imdbLink = imdbLink;
    }

    public String getNo() {
        return no;
    }


    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDirector() {
        return director;
    }

    public String getStar() {
        return star;
    }

    public String getImdbLink() {
        return imdbLink;
    }
    public String getTitle()
    {
        return getTitle();
    }
}

