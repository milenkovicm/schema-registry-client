# Schema registry client [![Build Status](https://travis-ci.org/milenkovicm/schema-registry-client.svg)](https://travis-ci.org/milenkovicm/schema-registry-client)
Schema registry ( https://github.com/confluentinc/schema-registry ) is a handy tool to share Apache Avro ( https://avro.apache.org/ ) schemas across multiple applications. 
This library is a tool to query the registry for registered schemas. 
It queries remote schema registry for schemas and keeps it in a local cache.

Library is a bit over engineered as I wanted to get my hands dirty with `RxJava`.

## Examples

It comes in two flavors: synchronous and asynchronous
 
```java
// lookup schema by subject
AsyncSchemaRegistry registry = new DefaultAsyncSchemaRegistry("http://localhost:8090");
final Observable<RegistryItem> registryItem = registry.lookup("test-schema");
```

```java
// lookup schema by id
AsyncSchemaRegistry registry = new DefaultAsyncSchemaRegistry("http://localhost:8090");
final Observable<RegistryItem> registryItem = registry.lookup(81);
```

```java
// lookup schema by subject but block 
SyncSchemaRegistry registry = new DefaultSyncSchemaRegistry("http://localhost:8090");
final RegistryItem registryItem = registry.lookup("test-schema");
```

```java
// lookup schema by id but block
SyncSchemaRegistry registry = new DefaultSyncSchemaRegistry("http://localhost:8090");
final RegistryItem registryItem = registry.lookup(81);
```

Once the `RegistryItem` is available getting avro schema from it is as simple as `registryItem.getSchema()`
