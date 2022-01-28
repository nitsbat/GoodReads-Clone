package com.bisht.GoodReadsClone.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BookController {

    private final String COVER_ID = "https://covers.openlibrary.org/b/id/";

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/book/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute("book", book);
            String coverImage = "static/image/no_image.jpg";
            if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
                coverImage = new StringBuilder().append(COVER_ID)
                        .append(book.getCoverIds().get(0))
                        .append("-L.jpg").toString();
                model.addAttribute("coverImage", coverImage);
            }
            return "book-found";
        }
        return "book-not-found";
    }
}
