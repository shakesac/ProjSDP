# Projecto de SDP
## Task List
1. **Database**
    * [ ] ER Diagram
    * [x] Postgres Docker container
    * [x] Database creation
    * [ ] Tables stuctured
2. **API REST**
    * [ ] Nothing yet
3. **Web App Interface**
    * [ ] Nothing yet

## 1. Database
### 1.1 ER Diagram
### 1.2 Postgres Docker container
#### docker-compose file
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
