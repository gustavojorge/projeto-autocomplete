import { useState, useEffect, useRef } from "react";
import { useLazyQuery } from "@apollo/client";
import { GET_SUGGESTIONS } from "../graphql/queries";
import { Suggestion } from "../types/Suggestion";

export const useSearchAutocomplete = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [suggestions, setSuggestions] = useState<Suggestion[]>([]);
  const [showSuggestions, setShowSuggestions] = useState(false);
  const [highlightedIndex, setHighlightedIndex] = useState(-1);
  const [selectedSuggestion, setSelectedSuggestion] =
    useState<Suggestion | null>(null);
  const inputRef = useRef<HTMLInputElement>(null);
  const suggestionsRef = useRef<HTMLDivElement>(null);

  const [fetchSuggestions, { data, loading }] = useLazyQuery(GET_SUGGESTIONS);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setSearchTerm(value);

    if (value.length >= 4) {
      fetchSuggestions({
        variables: { prefix: value, limit: 20 },
        fetchPolicy: "network-only",
      });
    } else {
      setSuggestions([]);
      setShowSuggestions(false);
      setHighlightedIndex(-1);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (selectedSuggestion && e.key === "Escape") {
      clearSelectedSuggestion();
      return;
    }

    if (!showSuggestions) return;

    switch (e.key) {
      case "ArrowDown":
        e.preventDefault();
        setHighlightedIndex((prev) =>
          prev < Math.min(suggestions.length - 1, 9) ? prev + 1 : prev
        );
        break;
      case "ArrowUp":
        e.preventDefault();
        setHighlightedIndex((prev) => (prev > 0 ? prev - 1 : -1));
        break;
      case "Enter":
        e.preventDefault();
        if (highlightedIndex >= 0 && highlightedIndex < suggestions.length) {
          const selected = suggestions[highlightedIndex];
          setSearchTerm(selected.term);
          updateSearchTerm(selected);
          setShowSuggestions(false);
          setHighlightedIndex(-1);
        }
        break;
      case "Escape":
        setShowSuggestions(false);
        setHighlightedIndex(-1);
        break;
      default:
        break;
    }
  };

  const handleSuggestionClick = (suggestion: Suggestion) => {
    setSearchTerm(suggestion.term);
    setShowSuggestions(false);
    setHighlightedIndex(-1);
    setSelectedSuggestion(suggestion);
  };

  const updateSearchTerm = (suggestion: Suggestion) => {
    setSearchTerm(suggestion.term);
    setShowSuggestions(false);
    setHighlightedIndex(-1);
  };

  const clearSelectedSuggestion = () => {
    setSelectedSuggestion(null);
  };

  useEffect(() => {
    if (data && data.getSuggestions) {
      setSuggestions(data.getSuggestions);
      setShowSuggestions(data.getSuggestions.length > 0);
      setHighlightedIndex(-1);
    } else if (
      data &&
      data.getSuggestions &&
      data.getSuggestions.length === 0
    ) {
      setSuggestions([]);
      setShowSuggestions(false);
      setHighlightedIndex(-1);
    }
  }, [data, searchTerm]);

  useEffect(() => {
    if (searchTerm.length < 4) {
      setSuggestions([]);
      setShowSuggestions(false);
      setHighlightedIndex(-1);
    }
  }, [searchTerm]);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        inputRef.current &&
        !inputRef.current.contains(event.target as Node) &&
        suggestionsRef.current &&
        !suggestionsRef.current.contains(event.target as Node)
      ) {
        setShowSuggestions(false);
        setHighlightedIndex(-1);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  useEffect(() => {
    const handleGlobalEscape = (e: KeyboardEvent) => {
      if (e.key === "Escape" && selectedSuggestion) {
        clearSelectedSuggestion();
      }
    };

    window.addEventListener("keydown", handleGlobalEscape);
    return () => window.removeEventListener("keydown", handleGlobalEscape);
  }, [selectedSuggestion]);

  return {
    searchTerm,
    suggestions,
    showSuggestions,
    highlightedIndex,
    selectedSuggestion,
    clearSelectedSuggestion,
    loading,
    inputRef,
    suggestionsRef,
    handleInputChange,
    handleKeyDown,
    handleSuggestionClick,
    updateSearchTerm,
    setHighlightedIndex,
  };
};
