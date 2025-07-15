import React from "react";

export const highlightMatch = (text: string, search: string) => {
  if (!search) return text;

  const normalize = (str: string) =>
    str
      .normalize("NFD")
      .replace(/[\u0300-\u036f]/g, "")
      .toLowerCase();

  const normalizedText = normalize(text);
  const normalizedSearch = normalize(search);

  const matchIndex = normalizedText.indexOf(normalizedSearch);

  if (matchIndex === -1) return text;

  const start = text.slice(0, matchIndex);
  const match = text.slice(matchIndex, matchIndex + search.length);
  const end = text.slice(matchIndex + search.length);

  return (
    <>
      {start}
      <span className="font-bold text-green-600">{match}</span>
      {end}
    </>
  );
};
