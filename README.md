# Projecto de SDP
## Dockers
## Docker-compose file
````
version: '3.5'
services:
  db:
    image: postgres
    container_name: postgres
    restart: always
    environment:
      POSTGRES_DB: stock_db
      POSTGRES_USER: sdp
      POSTGRES_PASSWORD: t1t1m1m1
    ports:
      - 5432:5432
    volumes:
      - /opt/postgres/data:/var/lib/postgresql/data
    networks:
      - stock_net
      
networks:
  stock_net:
    driver: bridge
````

## Realizado por:
#### Miguel Lima - 30003444
#### Tiago Alves - 30003460
