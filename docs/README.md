# Projecto de SDP
## Task List
1. **Database**
   * [x] Rel Model Diagram
   * [x] Postgres Docker container
   * [x] Database creation
   * [x] Tables structured
2. **API REST**
   * [ ] CRUD
     * [ ] Items
       * GET /items/     -> Items collection
       * GET /items/1    -> Item
       * PUT /items/1    -> Change item
       * POST /items/    -> Create item
       * DELETE /items/1 -> Delete item
     * [ ] Deposits
     * [ ] Deliveries
   * [ ] JDBC
   * [ ] Docker Wildfly
   * [ ] Replicate
3. **Web App Interface**
   * [ ] Check Info
   * [ ] Replicate

## 1. Database
### 1.1 Database Diagram
![Relational Model Diagram](/docs/database/Stock_Management.png)
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
