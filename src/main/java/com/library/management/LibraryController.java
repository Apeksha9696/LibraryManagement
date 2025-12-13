package com.library.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LibraryController {

    @Autowired
    private LibraryManager libraryManager;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", libraryManager.getAllBooks());
        model.addAttribute("members", libraryManager.getAllMembers());
        return "index";
    }

    @GetMapping("/add-book")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        libraryManager.addBook(book);
        redirectAttributes.addFlashAttribute("message", "Book added successfully!");
        return "redirect:/";
    }

    @GetMapping("/add-member")
    public String addMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "add-member";
    }

    @PostMapping("/add-member")
    public String addMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        libraryManager.addMember(member);
        redirectAttributes.addFlashAttribute("message", "Member added successfully!");
        return "redirect:/";
    }

    @PostMapping("/issue-book")
    public String issueBook(@RequestParam String bookId, RedirectAttributes redirectAttributes) {
        if (libraryManager.issueBook(bookId)) {
            redirectAttributes.addFlashAttribute("message", "Book issued successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found or already issued!");
        }
        return "redirect:/";
    }

    @PostMapping("/return-book")
    public String returnBook(@RequestParam String bookId, RedirectAttributes redirectAttributes) {
        if (libraryManager.returnBook(bookId)) {
            redirectAttributes.addFlashAttribute("message", "Book returned successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found!");
        }
        return "redirect:/";
    }

    @PostMapping("/delete-book")
    public String deleteBook(@RequestParam String bookId, RedirectAttributes redirectAttributes) {
        if (libraryManager.deleteBook(bookId)) {
            redirectAttributes.addFlashAttribute("message", "Book deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found!");
        }
        return "redirect:/";
    }

    @PostMapping("/delete-member")
    public String deleteMember(@RequestParam String memberId, RedirectAttributes redirectAttributes) {
        if (libraryManager.deleteMember(memberId)) {
            redirectAttributes.addFlashAttribute("message", "Member deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Member not found!");
        }
        return "redirect:/";
    }

    @GetMapping("/csv")
    public String csvPage() {
        return "csv";
    }

    @GetMapping("/export-books")
    public void exportBooks(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"library_books.csv\"");
        
        PrintWriter writer = response.getWriter();
        writer.println("Id,Title,Author,Status");
        
        for (Book book : libraryManager.getAllBooks()) {
            writer.printf("%s,%s,%s,%s%n", 
                book.getId(), 
                book.getTitle(), 
                book.getAuthor(), 
                book.isIssued() ? "Issued" : "Available");
        }
        
        writer.flush();
    }

    @GetMapping("/export-members")
    public void exportMembers(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"library_members.csv\"");
        
        PrintWriter writer = response.getWriter();
        writer.println("Id,Name");
        
        for (Member member : libraryManager.getAllMembers()) {
            writer.printf("%s,%s%n", member.getId(), member.getName());
        }
        
        writer.flush();
    }
}