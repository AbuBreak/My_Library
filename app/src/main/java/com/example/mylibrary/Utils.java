package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Books> allBooks;
    private static ArrayList<Books> alreadyReadBooks;
    private static ArrayList<Books> currentlyReadingBooks;
    private static ArrayList<Books> wantToReadBooks;
    private static ArrayList<Books> favoriteBooks;

    public Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }
        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }
        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }
        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: add initial data

        allBooks.add(new Books(1,"The Subtle Art Of Not Giving A FUCK ","Mark Manson",150,"https://anylang.net/sites/default/files/covers/i654912.jpg",
                "a book that will help you!","This Book was release in 2019 by M.M"));
        allBooks.add(new Books(2,"IQ84 ","Haruki Murakami",1350,"https://bayrockbayrock.files.wordpress.com/2015/06/1q84-cover.jpg",
                "A work of maddening brilliance","Long Description"));
    }

    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static ArrayList<Books> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Books> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Books> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Books> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Books> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Books getBookById(int id){
        for(Books b:allBooks){
            if(b.getId()==id){
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadRead(Books books){
     return alreadyReadBooks.add(books);
    }

    public boolean addToFavorite(Books books){
        return favoriteBooks.add(books);
    }

    public boolean addToCurrentlyReading(Books books){
        return currentlyReadingBooks.add(books);
    }

    public boolean addToWishlist(Books books){
        return wantToReadBooks.add(books);
    }

    public boolean removeFromAlreadyRead(Books books){
        return alreadyReadBooks.remove(books);
    }

    public boolean removeFromWantToRead(Books books){
        return wantToReadBooks.remove(books);
    }

    public boolean removeFromCurrentlyReading(Books books){
        return currentlyReadingBooks.remove(books);
    }

    public boolean removeFromFavorite(Books books){
        return favoriteBooks.remove(books);
    }
}
