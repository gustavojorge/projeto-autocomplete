import React from "react";

interface SearchMessagesProps {
  searchTerm: string;
  loading: boolean;
  suggestionsCount: number;
}

const SearchMessages: React.FC<SearchMessagesProps> = ({
  searchTerm,
  loading,
  suggestionsCount,
}) => {
  if (searchTerm.length > 0 && searchTerm.length < 4) {
    return (
      <div className="text-center mt-4 text-gray-600 text-sm">
        Digite mais {4 - searchTerm.length} caracteres para ver sugestões
      </div>
    );
  }

  if (searchTerm.length >= 4 && !loading && suggestionsCount === 0) {
    return (
      <div className="text-center mt-4 text-gray-600 text-sm">
        Nenhuma sugestão encontrada para "{searchTerm}"
      </div>
    );
  }

  return null;
};

export default SearchMessages;
