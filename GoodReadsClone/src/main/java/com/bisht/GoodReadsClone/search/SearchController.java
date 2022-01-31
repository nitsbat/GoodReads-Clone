package com.bisht.GoodReadsClone.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class SearchController {

    private static final String BASE_URL = "http://openlibrary.org/search.json?q=OL10002236W";

    private WebClient webClient;

    public SearchController(WebClient.Builder webclientBuilder) {
        this.webClient = webclientBuilder.baseUrl(BASE_URL).build();
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam String query, Model model) {
        final Mono<SearchResult> mono = this.webClient.get().uri("?q={query}", query)
                .retrieve().bodyToMono(SearchResult.class);
        SearchResult result = mono.block();
        if (result == null)
            return "search-no-book";
        model.addAttribute("searchResult", result);
        return "search";
    }
}
