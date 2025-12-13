package com.library.management;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LibraryManager {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void addMember(Member member) {
        members.put(member.getId(), member);
    }

    public boolean issueBook(String bookId) {
        Book book = books.get(bookId);
        if (book == null || book.isIssued()) {
            return false;
        }
        book.setIssued(true);
        return true;
    }

    public boolean returnBook(String bookId) {
        Book book = books.get(bookId);
        if (book == null) {
            return false;
        }
        book.setIssued(false);
        return true;
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public Collection<Member> getAllMembers() {
        return members.values();
    }

    public Book getBook(String id) {
        return books.get(id);
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public boolean deleteBook(String bookId) {
        Book removedBook = books.remove(bookId);
        return removedBook != null;
    }

    public boolean deleteMember(String memberId) {
        Member removedMember = members.remove(memberId);
        return removedMember != null;
    }
}