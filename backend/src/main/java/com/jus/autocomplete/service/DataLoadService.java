package com.jus.autocomplete.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jus.autocomplete.model.Suggestion;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class DataLoadService {

    public List<Suggestion> loadSuggestions() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/data/glossary.json");
            List<Suggestion> suggestions = mapper.readValue(is, new TypeReference<List<Suggestion>>() {});
            return suggestions;
        } catch (Exception e) {
            System.err.println("Error to load the glossary: " + e.getMessage());
            return List.of();
        }
    }
}
