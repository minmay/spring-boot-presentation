# spring-boot-presentation

## Consoles

###  Run mongodb console
```
docker-compose mongodb exec mongo 27017
```

###  Run cassandra cqlsh
```
docker-compose exec cassandra cqlsh
```

## Configure Cassandra

###  Run create cassandra keyspace
```
docker-compose exec cassandra cqlsh -e "CREATE KEYSPACE lajug WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };"

docker-compose exec cassandra cqlsh -e "CREATE TABLE lajug.cassandra_messages(id bigint PRIMARY KEY, message VARCHAR);"
```

## Configure Rabbit MQ
1. Point browser to http://localhost:15672/
2. Log in with credentials guest/guest
3. Click on the "Queue"" tab
4. Input a name and then click on "Add Queue"

## Resource Cheat Sheet

### Insert Mongo DB message
```
curl -i -X POST -H "Content-Type:application/json" -d "{  \"id\" : \"1\",  \"message\" : \"Lorem ipsum dolor sit amet\" }" http://localhost:8080/system/mongoDbMessages
```
### View Mongo DB Messages
```
http://localhost:8080/system/mongoDbMessages
```

### Insert Redis Message
```
curl -i -X POST -H "Content-Type:application/json" -d "{  \"id\" : \"1\",  \"message\" : \"Lorem ipsum dolor sit amet\" }" http://localhost:8080/system/redisMessages
```
### View Redis Messages
```
http://localhost:8080/system/redisMessages
```

### Insert Cassandra Message
```
curl -i -X POST -H "Content-Type:application/json" -d "{  \"id\" : \"1\",  \"message\" : \"Lorem ipsum dolor sit amet\" }" http://localhost:8080/system/cassandraMessages
```
### View Cassandra Messages
```
http://localhost:8080/system/cassandraMessages
```

### Insert Postgres Message
```
curl -i -X POST -H "Content-Type:application/json" -d "{  \"message\" : \"Lorem ipsum dolor sit amet\" }" http://localhost:8080/system/postgresMessages
```
### View Postgres Messages
```
http://localhost:8080/system/postgresMessages
```

```
don't delete this quoted text
```