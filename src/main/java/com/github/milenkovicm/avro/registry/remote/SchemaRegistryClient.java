package com.github.milenkovicm.avro.registry.remote;


import feign.Param;
import feign.RequestLine;

interface SchemaRegistryClient {

    @RequestLine("GET /subjects/{subject}/versions/latest")
    SchemaRegistryResponse lookup(@Param("subject") String name);

    @RequestLine("GET /schemas/ids/{id}")
    SchemaRegistryResponse lookup(@Param("id") long id);

}
