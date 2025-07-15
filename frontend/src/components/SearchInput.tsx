import React from "react";

interface SearchInputProps {
  inputRef: React.RefObject<HTMLInputElement | null>;
  searchTerm: string;
  loading: boolean;
  onInputChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onKeyDown: (e: React.KeyboardEvent<HTMLInputElement>) => void;
  onSearchClick: () => void;
}

const SearchInput: React.FC<SearchInputProps> = ({
  inputRef,
  searchTerm,
  loading,
  onInputChange,
  onKeyDown,
  onSearchClick,
}) => {
  return (
    <div className="relative w-full max-w-2xl mx-auto">
      <div className="flex items-center border-2 border-gray-300 rounded-lg bg-white hover:shadow-md focus-within:shadow-md transition-shadow duration-200">
        <input
          ref={inputRef}
          type="text"
          value={searchTerm}
          onChange={onInputChange}
          onKeyDown={onKeyDown}
          placeholder="Digite sua busca aqui..."
          className="flex-1 px-4 py-3 text-lg outline-none rounded-l-lg"
        />

        {loading && (
          <div className="px-3">
            <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-gray-600"></div>
          </div>
        )}

        <button
          onClick={onSearchClick}
          disabled={!searchTerm.trim()}
          className="px-4 py-3 text-gray-500 hover:text-blue-500 disabled:text-gray-300 disabled:cursor-not-allowed transition-colors duration-200 cursor-pointer"
          title="Buscar"
        >
          <svg
            className="w-6 h-6"
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
      </div>
    </div>
  );
};

export default SearchInput;
