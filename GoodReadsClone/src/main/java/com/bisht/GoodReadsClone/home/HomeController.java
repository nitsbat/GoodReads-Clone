package com.bisht.GoodReadsClone.home;

import com.bisht.GoodReadsClone.user.UserByBooks;
import com.bisht.GoodReadsClone.user.UserByBooksRepository;
import com.bisht.GoodReadsClone.userbooks.UserBookEntity;
import com.bisht.GoodReadsClone.userbooks.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    UserByBooksRepository userByBooksRepository;

    @GetMapping("/")
    public String getHome(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal == null || principal.getAttribute("login") == null) {
            return "index";
        }

        Slice<UserByBooks> userBookSlice = userByBooksRepository.
                findAllById(principal.getAttribute("login"), CassandraPageRequest.of(0, 100));
        List<UserByBooks> userBookList = userBookSlice.getContent();
        userBookList = userBookList.stream().distinct().collect(Collectors.toList());
        model.addAttribute("userBook", userBookList);
        return "home";
    }

}
