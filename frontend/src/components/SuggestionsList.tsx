import React from "react";
import { highlightMatch } from "../utils/searchUtils";
import { Suggestion } from "../types/Suggestion";

interface SuggestionsListProps {
  suggestions: Suggestion[];
  searchTerm: string;
  highlightedIndex: number;
  onSuggestionClick: (suggestion: Suggestion) => void;
  onDefinitionClick: (suggestion: Suggestion) => void;
  onMouseEnter: (index: number) => void;
  suggestionsRef?: React.RefObject<HTMLDivElement | null>;
}

const SuggestionsList: React.FC<SuggestionsListProps> = ({
  suggestions,
  searchTerm,
  highlightedIndex,
  onSuggestionClick,
  onDefinitionClick,
  onMouseEnter,
  suggestionsRef,
}) => {
  const handleDefinitionClick = (
    e: React.MouseEvent,
    suggestion: Suggestion
  ) => {
    e.stopPropagation();
    onDefinitionClick(suggestion);
  };

  return (
    <div
      ref={suggestionsRef}
      className="absolute top-0 left-0 right-0 bg-white border border-gray-300 rounded-lg shadow-lg max-h-80 overflow-y-auto z-10"
      style={{ marginTop: "2px" }}
    >
      {suggestions.slice(0, 20).map((s, i) => (
        <div
          key={i}
          onClick={() => onSuggestionClick(s)}
          onMouseEnter={() => onMouseEnter(i)}
          className={`px-4 py-3 cursor-pointer text-sm border-b border-gray-100 last:border-b-0 flex items-center ${
            highlightedIndex === i ? "bg-blue-100" : "hover:bg-gray-50"
          }`}
        >
          <button
            onClick={(e) => handleDefinitionClick(e, s)}
            className="w-4 h-4 text-gray-400 hover:text-blue-500 mr-3 flex-shrink-0 transition-colors duration-200 cursor-pointer"
            title="Ver definição"
          >
            <svg
              className="w-full h-full"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
              />
            </svg>
          </button>
          <span className="flex-1 text-left">
            {highlightMatch(s.term, searchTerm)}
          </span>
        </div>
      ))}
    </div>
  );
};

export default SuggestionsList;
