FROM node AS builder

WORKDIR app
RUN pwd

COPY ./app .
RUN npm i && npm run build --base="/"

FROM nginx:alpine

COPY landing-page /usr/share/nginx/html
COPY --from=builder /app/dist/beeyond /usr/share/nginx/html/app
COPY nginx/nginx-startup.sh /docker-entrypoint.d/
COPY nginx/set-base-href.sh /docker-entrypoint.d/
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

ENV DEVICEMANAGER_BACKEND_API_URL="http://localhost:8080"
