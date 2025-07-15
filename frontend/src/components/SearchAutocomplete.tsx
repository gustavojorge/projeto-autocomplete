import React from "react";
import { useSearchAutocomplete } from "../hooks/useSearchAutocomplete";
import SearchInput from "./SearchInput";
import SuggestionsList from "./SuggestionsList";
import SearchMessages from "./SearchMessages";
import SearchInstructions from "./SearchInstructions";
import TermDefinition from "./TermDefinition";
import { Suggestion } from "../types/Suggestion";

const SearchAutocomplete: React.FC = () => {
  const {
    searchTerm,
    suggestions,
    showSuggestions,
    highlightedIndex,
    loading,
    inputRef,
    suggestionsRef,
    handleInputChange,
    handleKeyDown,
    handleSuggestionClick,
    updateSearchTerm,
    setHighlightedIndex,
    selectedSuggestion,
    clearSelectedSuggestion,
  } = useSearchAutocomplete();

  const handleSearchClick = () => {
    if (searchTerm.trim()) {
      const exactMatch = suggestions.find(
        (suggestion) =>
          suggestion.term.toLowerCase() === searchTerm.toLowerCase()
      );

      if (exactMatch) {
        handleSuggestionClick(exactMatch);
      } else if (suggestions.length > 0 && highlightedIndex >= 0) {
        handleSuggestionClick(suggestions[highlightedIndex]);
      } else {
        handleSuggestionClick(suggestions[0]);
      }
    }
  };

  const handleDefinitionClick = (suggestion: Suggestion) => {
    handleSuggestionClick(suggestion);
  };

  const handleSuggestionSelect = (suggestion: Suggestion) => {
    updateSearchTerm(suggestion);
  };

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-start pt-20">
      <div className="w-full max-w-2xl px-4">
        <div className="text-center mb-8">
          <h1 className="text-4xl font-bold text-gray-800 mb-2">
            Glossário Jurídico
          </h1>
          <p className="text-gray-600">
            Digite pelo menos 4 caracteres para ver as sugestões
          </p>
        </div>

        <div className="relative mb-4">
          <SearchInput
            inputRef={inputRef}
            searchTerm={searchTerm}
            loading={loading}
            onInputChange={handleInputChange}
            onKeyDown={handleKeyDown}
            onSearchClick={handleSearchClick}
          />
        </div>

        {showSuggestions && suggestions.length > 0 && (
          <div className="relative">
            <SuggestionsList
              suggestions={suggestions}
              searchTerm={searchTerm}
              highlightedIndex={highlightedIndex}
              onSuggestionClick={handleSuggestionSelect}
              onDefinitionClick={handleDefinitionClick}
              onMouseEnter={setHighlightedIndex}
              suggestionsRef={suggestionsRef}
            />
          </div>
        )}

        <SearchMessages
          searchTerm={searchTerm}
          loading={loading}
          suggestionsCount={suggestions.length}
        />

        <SearchInstructions />
      </div>

      {selectedSuggestion && (
        <TermDefinition
          term={selectedSuggestion.term}
          definition={selectedSuggestion.definition}
          onClose={clearSelectedSuggestion}
        />
      )}
    </div>
  );
};

export default SearchAutocomplete;
