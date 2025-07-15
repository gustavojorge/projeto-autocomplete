package com.jus.autocomplete.service;

import com.jus.autocomplete.model.Suggestion;
import com.jus.autocomplete.model.Trie;
import com.jus.autocomplete.util.TextNormalizer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrieService {

    private final Trie trie = new Trie();

    @Autowired
    private DataLoadService dataLoadService;

    @PostConstruct
    public void initializeTrie() {
        List<Suggestion> suggestions = dataLoadService.loadSuggestions();
        for (Suggestion s : suggestions) {
            trie.insert(s.getTerm(), s.getDefinition());
        }
    }

    public List<Suggestion> searchSuggestions(String prefix, int limit) {
        if (prefix == null || prefix.length() < 4) {
            return List.of();
        }

        String normalizedPrefix = TextNormalizer.normalize(prefix);
        return trie.searchByPrefix(normalizedPrefix, limit);
    }
}
