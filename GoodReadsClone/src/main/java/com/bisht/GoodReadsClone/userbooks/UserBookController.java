package com.bisht.GoodReadsClone.userbooks;

import com.bisht.GoodReadsClone.books.Book;
import com.bisht.GoodReadsClone.books.BookRepository;
import com.bisht.GoodReadsClone.user.UserByBooks;
import com.bisht.GoodReadsClone.user.UserByBooksRepository;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class UserBookController {

    private final String COVER_ID = "https://covers.openlibrary.org/b/id/";

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private UserByBooksRepository userByBooksRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addUserBook")
    public ModelAndView addUserBook(@RequestBody MultiValueMap<String, String> map,
                                    @AuthenticationPrincipal OAuth2User principal) {
        String loginId = principal.getAttribute("login");
        if (principal == null || loginId == null) {
            return null;
        }
        UserBookEntity userBookEntity = new UserBookEntity();

        UserBookKey key = new UserBookKey();
        key.setUserID(loginId);
        String bookId = map.getFirst("bookId");
        key.setBookId(bookId);

        userBookEntity.setKey(key);
        userBookEntity.setStartedDate(LocalDate.parse(map.getFirst("startDate")));
        userBookEntity.setCompletedDate(LocalDate.parse(map.getFirst("completedDate")));
        userBookEntity.setRating(Integer.parseInt(map.getFirst("rating")));
        userBookEntity.setStatus(map.getFirst("status"));

        userBookRepository.save(userBookEntity);

        //save the user by book for the home api
        persistUserByBooksForHomePage(map, loginId);

        return new ModelAndView("redirect:/books/" + bookId);
    }

    private void persistUserByBooksForHomePage(MultiValueMap<String, String> map, String loginId) {
        UserByBooks userByBooks = new UserByBooks();
        userByBooks.setId(loginId);
        userByBooks.setRating(Integer.parseInt(map.getFirst("rating")));
        userByBooks.setReadingStatus(map.getFirst("status"));
        userByBooks.setTimeUuid(Uuids.timeBased());
        Optional<Book> optionalBook = bookRepository.findById(map.getFirst("bookId"));
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            userByBooks.setBookName(book.getName());
            userByBooks.setBookId(book.getId());
            userByBooks.setAuthorNames(book.getAuthorNames());

            String coverImage = "/images/no_image.jpg";
            if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
                coverImage = new StringBuilder().append(COVER_ID)
                        .append(book.getCoverIds().get(0))
                        .append("-L.jpg").toString();
            }
            userByBooks.setCoverId(coverImage);
        }
        userByBooksRepository.save(userByBooks);
    }
}
