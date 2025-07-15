package com.jus.autocomplete.model;

import com.jus.autocomplete.util.TextNormalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class Trie {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode('\0');
    }

    public void insert(String word, String definition) {
        String normalizedWord = TextNormalizer.normalize(word);
        TrieNode current = root;

        for (char c : normalizedWord.toCharArray()) {
            Map<Character, TrieNode> children = current.getChildren();
            if (!children.containsKey(c)) {
                children.put(c, new TrieNode(c));
            }
            current = children.get(c);
        }

        current.setEndOfWord(true);
        current.setFullWord(word);
        current.setDefinition(definition);
    }

    public List<Suggestion> searchByPrefix(String prefix, int limit) {
        List<Suggestion> results = new ArrayList<>();

        if (prefix == null || prefix.trim().isEmpty()) {
            return results;
        }

        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            current = current.getChildren().get(c);
            if (current == null) {
                return results;
            }
        }

        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty() && results.size() < limit) {
            TrieNode node = queue.poll();

            if (node.isEndOfWord()) {
                String term = node.getFullWord();
                String definition = node.getDefinition();
                results.add(new Suggestion(term, definition));
            }

            for (TrieNode child : node.getChildren().values()) {
                queue.add(child);
            }
        }

        return results;
    }

}
