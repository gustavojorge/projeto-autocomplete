package com.jus.autocomplete.model;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    final private char character;
    private Map<Character, TrieNode> children;
    private boolean isEndOfWord;
    private String fullWord;
    private String definition;

    public TrieNode(char character) {
        this.character = character;
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.fullWord = null;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public String getFullWord() {
        return fullWord;
    }

    public void setFullWord(String fullWord) {
        this.fullWord = fullWord;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
