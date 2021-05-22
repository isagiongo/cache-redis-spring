### POC - Spring Boot + Redis
---------

#### Building and Running this application

#### Build Java jar
```mvn clean install -DskipTests```

#### Build and run docker compose
```
docker-compose build --no-cache 
docker-compose up --force-recreate
```

#### Testing requests

* Insert new customer 
```
curl --location --request POST 'http://localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "Pupy",
        "cpf": "88866677700098",
        "city": "Porto Alegre",
        "postalCode": "90460120",
        "country": "Brasil",
        "address": "Perpetua Teles 180"
}'
```
* Get all customers
```
curl --location --request GET 'http://localhost:8080/customers/'
```
* Get one customer by id
```
curl --location --request GET 'http://localhost:8080/customers/1'
```
* Update customer
```
curl --location --request PUT 'http://localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
        "id":1,
        "name": "Pupy",
        "cpf": "88866677700098",
        "city": "Porto Alegre",
        "postalCode": "90460120",
        "country": "Brasil",
        "address": "Perpetua Teles 180"
}'
```
* Delete customer
```
curl --location --request DELETE 'http://localhost:8080/customers/1'
```
