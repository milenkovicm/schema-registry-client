# Reponse Examples  
GET http://localhost:8090/subjects

```json
["model-simple","simple-model","Kafka-key"]
```

GET http://localhost:8090/subjects/simple-model/versions

```json
[1]
```

GET http://localhost:8090/subjects/simple-model/versions/1

```json
{"subject":"simple-model","version":1,"id":41,"schema":"\"string\""}
```

GET http://localhost:8090/subjects/simple-model/versions/latest

```json
{"subject":"simple-model","version":1,"id":41,"schema":"\"string\""}
```

GET http://localhost:8090/subjects/test-schema/versions/latest

```json
{"subject":"test-schema","version":1,"id":81,"schema":"{\"type\":\"record\",\"subject\":\"E_2\",\"namespace\":\"com.github.milenkovicm.avro\",\"fields\":[{\"subject\":\"P1\",\"type\":\"string\"},{\"subject\":\"P2\",\"type\":\"string\"},{\"subject\":\"P3\",\"type\":\"string\"}]}"}
```

GET http://localhost:8090/schemas/ids/41

```json
{"schema":"\"string\""}
```

```json
{"schema":"{   \"type\": \"record\",   \"subject\": \"E_2\",   \"namespace\": \"com.github.milenkovicm.avro\",   \"fields\": [     {       \"subject\": \"P1\",       \"type\": \"string\"     },     {       \"subject\": \"P2\",       \"type\": \"string\"     },     {       \"subject\": \"P3\",       \"type\": \"string\"     }   ] }"}
```

POST http://localhost:8090/subjects/test-schema/versions

