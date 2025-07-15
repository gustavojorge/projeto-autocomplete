import React from "react";
import { ApolloProvider } from "@apollo/client";
import client from "./graphql/client";
import SearchAutocomplete from "./components/SearchAutocomplete";

function App() {
  return (
    <ApolloProvider client={client}>
      <SearchAutocomplete />
    </ApolloProvider>
  );
}

export default App;
