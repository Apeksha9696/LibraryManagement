package com.library.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LibraryManager libraryManager;

    @Override
    public void run(String... args) throws Exception {
        // Add sample books
        libraryManager.addBook(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", false));
        libraryManager.addBook(new Book("B002", "To Kill a Mockingbird", "Harper Lee", false));
        libraryManager.addBook(new Book("B003", "1984", "George Orwell", true));
        libraryManager.addBook(new Book("B004", "Pride and Prejudice", "Jane Austen", false));
        libraryManager.addBook(new Book("B005", "The Catcher in the Rye", "J.D. Salinger", false));

        // Add sample members
        libraryManager.addMember(new Member("M001", "John Doe"));
        libraryManager.addMember(new Member("M002", "Jane Smith"));
        libraryManager.addMember(new Member("M003", "Bob Johnson"));

        System.out.println("Sample data initialized!");
    }
}