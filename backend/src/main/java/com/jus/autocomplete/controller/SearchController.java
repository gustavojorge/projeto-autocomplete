package com.jus.autocomplete.controller;

import com.jus.autocomplete.model.Suggestion;
import com.jus.autocomplete.service.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private TrieService trieService;

    @QueryMapping
    public List<Suggestion> getSuggestions(@Argument String prefix, @Argument int limit) {
        return trieService.searchSuggestions(prefix, limit);
    }
}
