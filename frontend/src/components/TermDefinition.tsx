import React from "react";

interface TermDefinitionProps {
  term: string;
  definition: string;
  onClose: () => void;
}

const TermDefinition: React.FC<TermDefinitionProps> = ({
  term,
  definition,
  onClose,
}) => {
  const handleOverlayClick = (e: React.MouseEvent) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  return (
    <div
      className="fixed inset-0 bg-black/50 flex items-center justify-center z-50"
      onClick={handleOverlayClick}
    >
      <div className="bg-white rounded-xl shadow-lg max-w-md w-full mx-4 p-6 relative">
        <button
          onClick={onClose}
          className="absolute top-4 right-4 text-gray-500 hover:text-gray-700 text-3xl font-bold leading-none cursor-pointer"
        >
          ×
        </button>

        <h2 className="text-xl font-bold text-gray-800 mb-4 pr-8">{term}</h2>

        <p className="text-gray-600 leading-relaxed text-justify">
          {definition || "Definição não encontrada"}
        </p>
      </div>
    </div>
  );
};

export default TermDefinition;
