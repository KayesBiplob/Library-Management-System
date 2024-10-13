package Final;

import java.time.LocalDate;

public class Book {

    private String bookName;
    private String author;
    private boolean borrowed;
    private LocalDate borrowedDate; // Updated to LocalDate type

    public Book(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Title: " + bookName + " | Author: " + author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowedDate(LocalDate borrowedDate, Book book) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }
}
