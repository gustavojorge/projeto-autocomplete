# Build stage
FROM node:18-alpine AS builder

WORKDIR /app

# Declaração do ARG
ARG VITE_API_URL

# Copia arquivos e instala dependências
COPY package*.json ./
RUN npm ci

COPY . .

ENV VITE_API_URL=$VITE_API_URL

RUN VITE_API_URL=$VITE_API_URL npm run build

FROM nginx:alpine

COPY --from=builder /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
