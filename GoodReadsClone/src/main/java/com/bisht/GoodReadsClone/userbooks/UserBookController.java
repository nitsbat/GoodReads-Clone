package com.bisht.GoodReadsClone.userbooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class UserBookController {

    @Autowired
    private UserBookRepository userBookRepository;

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

        System.out.println(map);
        return new ModelAndView("redirect:/books/" + bookId);
    }
}
