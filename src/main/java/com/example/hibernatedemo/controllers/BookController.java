package com.example.hibernatedemo.controllers;

import com.example.hibernatedemo.dao.BookDAO;
import com.example.hibernatedemo.entities.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookDAO bookDAO = new BookDAO();

    @GetMapping("/books")
    public String listBooks(@RequestParam(name = "title", required = false) String title,
                            @RequestParam(name = "author", required = false) String author,
                            @RequestParam(name = "publishYear", required = false) Integer publishYear,
                            @RequestParam(name = "genre", required = false) String genre,
                            @RequestParam(name = "pageCount", required = false) Integer pageCount,
                            @RequestParam(name = "description", required = false) String description,
                            Model model) {
        List<Book> books = bookDAO.searchBooks(title, author, publishYear, genre, pageCount, description);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookDAO.getBookById(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book book) {
        bookDAO.update(book);
        return "redirect:/books";
    }
}
