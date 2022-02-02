package com.bisht.GoodReadsClone.books;

import com.bisht.GoodReadsClone.userbooks.UserBookEntity;
import com.bisht.GoodReadsClone.userbooks.UserBookKey;
import com.bisht.GoodReadsClone.userbooks.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @Autowired
    private UserBookRepository userBookRepository;

    @GetMapping(value = "/books/{bookId}")
    public String getBook(@PathVariable String bookId, Model model,
                          @AuthenticationPrincipal OAuth2User principal) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute("book", book);
            String coverImage = "static/images/no_image.jpg";
            if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
                coverImage = new StringBuilder().append(COVER_ID)
                        .append(book.getCoverIds().get(0))
                        .append("-L.jpg").toString();
                model.addAttribute("coverImage", coverImage);
            }
            if (principal != null) {
                String loginId = principal.getAttribute("login");
                if (loginId != null) {
                    model.addAttribute("loginId", loginId);
                    UserBookKey key = new UserBookKey();
                    key.setUserID(loginId);
                    key.setBookId(book.getId());
                    Optional<UserBookEntity> userBook = userBookRepository.findById(key);
                    if (userBook.isPresent()) {
                        model.addAttribute("userBook", userBook.get());
                    } else {
                        model.addAttribute("userBook", new UserBookEntity());
                    }
                }
            }
            return "book-found";
        }
        return "book-not-found";
    }
}
