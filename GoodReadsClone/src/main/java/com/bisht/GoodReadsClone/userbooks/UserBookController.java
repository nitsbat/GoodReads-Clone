package com.bisht.GoodReadsClone.userbooks;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserBookController {

    @PostMapping("/addUserBook")
    public String addUserBook(@RequestBody MultiValueMap<String, String> map,
                              @AuthenticationPrincipal OAuth2User principal) {
        System.out.println(map);
        return "";
    }
}
