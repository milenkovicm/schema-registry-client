package com.github.milenkovicm.avro.registry.remote;


import feign.Headers;
import feign.Param;
import feign.RequestLine;

interface SchemaRegistryClient {

    @RequestLine("GET /subjects/{subject}/versions/latest")
    SchemaRegistryItem lookup(@Param("subject") String name);

    @RequestLine("GET /schemas/ids/{id}")
    SchemaRegistryItem lookup(@Param("id") long id);

    @RequestLine("POST /subjects/{subject}/versions")
    @Headers("Content-Type: application/vnd.schemaregistry.v1+json")
    SchemaRegistryId put(@Param("subject") String subject, SchemaRegistryItem schemaRegistryItem);

}
