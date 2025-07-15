import React from "react";

const SearchInstructions: React.FC = () => {
  return (
    <div className="mt-12 bg-white rounded-lg shadow-sm border border-gray-200 p-6 max-w-2xl mx-auto">
      <h3 className="text-lg font-semibold text-gray-800 mb-4">Instruções</h3>

      <div className="space-y-3 text-sm text-gray-600">
        <p className="flex items-center">
          <span className="w-2 h-2 bg-blue-500 rounded-full mr-3"></span>
          Digite pelo menos 4 caracteres para ver sugestões
        </p>

        <p className="flex items-center">
          <span className="w-2 h-2 bg-blue-500 rounded-full mr-3"></span>
          Clique em uma sugestão para selecioná-la
        </p>

        <p className="flex items-center">
          <span className="w-2 h-2 bg-blue-500 rounded-full mr-3"></span>
          Clique no ícone de lupa para ver a definição da palavra selecionada
        </p>

        <p className="flex items-center">
          <span className="w-2 h-2 bg-blue-500 rounded-full mr-3"></span>
          Pressione 'enter' para selecionar a sugestão destacada
        </p>

        <p className="flex items-center">
          <span className="w-2 h-2 bg-blue-500 rounded-full mr-3"></span>
          Pressione 'esc' para fechar as sugestões/definição
        </p>

        <p className="flex items-center">
          <span className="w-2 h-2 bg-green-500 rounded-full mr-3"></span>
          Para testar, tente "ação" ou "direito" (ambos com mais de 30 termos)
        </p>
      </div>
    </div>
  );
};

export default SearchInstructions;
