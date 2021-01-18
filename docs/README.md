# Projecto de SDP
## Task List
1. **Database**
   * [x] Rel Model Diagram
   * [x] Postgres Docker container
   * [x] Database creation
   * [x] Tables structured
2. **API REST**
   * [x] JDBC Driver
   * [ ] CONTROLLER
     * [ ] Data Access Object
   * [ ] CRUD
     * [ ] Items
       * GET /items/     -> Items collection
       * GET /items/1    -> Item
       * PUT /items/1    -> Change item
       * POST /items/    -> Create item
       * DELETE /items/1 -> Delete item
     * [ ] Deposits
     * [ ] Deliveries
   * [ ] Docker Wildfly
   * [ ] Replicate
3. **Web App Interface**
   * [ ] Check Info
   * And more... 

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
### Referencies
* **Database related**:
  * Docker PostgreSQL: https://hub.docker.com/_/postgres
  * pgAdmin 4: https://www.pgadmin.org/download/pgadmin-4-windows/
  * JDBC PostgreSQL Driver: https://mvnrepository.com/artifact/org.postgresql/postgresql/42.2.18
  * PostgreSQL Tutorial: https://www.postgresqltutorial.com
  * getGeneratedKeys(): https://www.javaguides.net/2019/07/how-to-retrieve-auto-generated-keys-in-jdbc.html  
* **API REST related**:
  * Aulas SDP:
    * 19
    * 21  
  * Wildfly 21:
    * https://registry.hub.docker.com/r/jboss/wildfly#!
    * https://github.com/JBoss-Dockerfiles/wildfly
    * Documentation: https://docs.wildfly.org/21/JavaEE_Tutorial.html  
  * Dependencies:
    * JDBC PostgreSQL Driver: https://mvnrepository.com/artifact/org.postgresql/postgresql/42.2.18
    * Jackson Core (_ObjectMapper()_) lib:  https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
  * File Structure and CRUD:
    * RESTeasy: https://zetcode.com/jaxrs/resteasycrud/
    * Jax-Rs: http://javainsimpleway.com/crud-operations-with-jax-rs-and-json/
    * API Design: https://courses.bekwam.net/public_tutorials/bkcourse_wildfly_api_design.html
    * Wildfly JAX-RS: http://www.thejavageek.com/2015/12/16/jax-rs-hello-world-example-with-wildfly/
  * Jackson JSON Processing Documentation:
    * https://mkyong.com/java/jackson-how-to-parse-json/
    * Jackson Databind: https://fasterxml.github.io/jackson-databind/javadoc/2.7/com/fasterxml/jackson/databind/ObjectMapper.html
    * Jackson JSON Java Parser: https://www.journaldev.com/2324/jackson-json-java-parser-api-example-tutorial#jackson-json-8211-read-specific-json-key
    * Processing JSON: https://dzone.com/articles/processing-json-with-jackson
    * https://www.youtube.com/watch?v=vi1lU57U2p8
  * Java HashMap: https://www.javatpoint.com/java-map
* **Angular Front-end**:
  * Documentation: https://angular.io/guide/setup-local
  * Firefox CORS: https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS/Errors/CORSMissingAllowOrigin


## Realizado por:
#### Miguel Lima - 30003444
#### Tiago Alves - 30003460
