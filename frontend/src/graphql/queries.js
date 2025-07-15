import { gql } from "@apollo/client";

export const GET_SUGGESTIONS = gql`
  query GetSuggestions($prefix: String!, $limit: Int = 20) {
    getSuggestions(prefix: $prefix, limit: $limit) {
      term
      definition
    }
  }
`;
