package com.bisht.GoodReadsClone.search;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    private static final String BASE_URL = "http://openlibrary.org/search.json?q=OL10002236W";

    private WebClient webClient;

    private final String COVER_ID = "https://covers.openlibrary.org/b/id/";

    public SearchController(WebClient.Builder webclientBuilder) {
        this.webClient = webclientBuilder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configure -> configure.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build())
                .baseUrl(BASE_URL).build();
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam String query, Model model) {
        Mono<SearchResult> mono = this.webClient.get().uri("?q={query}", query)
                .retrieve().bodyToMono(SearchResult.class);
        SearchResult result = mono.block();
        if (result == null)
            return "search-no-book";
        List<SearchBook> resultData = result.getDocs().stream()
                .map(searchData -> {
                    searchData.setKey(searchData.getKey().replace("/works/", ""));
                    if (StringUtils.isNotBlank(searchData.getCover_i())) {
                        searchData.setCover_i(new StringBuilder().append(COVER_ID)
                                .append(searchData.getCover_i())
                                .append("-L.jpg").toString());
                    } else {
                        searchData.setCover_i("/images/no_image.jpg");
                    }
                    return searchData;
                })
                .limit(10).collect(Collectors.toList());
        model.addAttribute("searchResults", resultData);
        return "search";
    }
}
