package com.github.milenkovicm.avro.registry.remote;

import com.github.milenkovicm.avro.registry.AbstractWebTest;
import com.github.milenkovicm.avro.registry.Default;
import com.github.milenkovicm.avro.registry.RegistryItem;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class RemoteSchemaRegistryTest extends AbstractWebTest {

    @Test
    public void getSchema_forName_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final SchemaRegistryClient registry = RestAsyncSchemaRegistry.getInstance("http://localhost:" + server.getPort());
        final SchemaRegistryResponse response = registry.lookup("test-schema");

        final RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath(), is("/subjects/test-schema/versions/latest"));
        assertThat(recordedRequest.getMethod(), is("GET"));
        assertThat(response.getSubject(), is("test-schema"));
        assertThat(response.getVersion(), is(1));
        assertThat(response.getId(), is(81L));
        assertThat(response.getSchema(), is(Default.DEFAULT_NAME_SCHEMA));
    }

    @Test
    public void getSchema_forId_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_ID_RESPONSE));

        final SchemaRegistryClient registry = RestAsyncSchemaRegistry.getInstance("http://localhost:" + server.getPort());
        final SchemaRegistryResponse response = registry.lookup(81);

        final RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath(), is("/schemas/ids/81"));
        assertThat(recordedRequest.getMethod(), is("GET"));
        assertThat(response.getSchema(), is(Default.DEFAULT_ID_SCHEMA));

    }

    @Test
    public void getSchemaAsync_forName_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final RestAsyncSchemaRegistry registry = new RestAsyncSchemaRegistry("http://localhost:" + server.getPort());
        final RegistryItem schemaItem = registry.lookup("test-schema").toBlocking().single();

        final RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath(), is("/subjects/test-schema/versions/latest"));
        assertThat(recordedRequest.getMethod(), is("GET"));
        assertThat(schemaItem.getSubject(), is("test-schema"));
        assertThat(schemaItem.getVersion(), is(1));
        assertThat(schemaItem.getId(), is(81L));
        assertThat(schemaItem.getSchema(), notNullValue());
    }

    @Test
    public void getSchemaAsync_forId_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_ID_RESPONSE));

        final RestAsyncSchemaRegistry registry = new RestAsyncSchemaRegistry("http://localhost:" + server.getPort());
        final RegistryItem schemaItem = registry.lookup(81).toBlocking().single();

        final RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath(), is("/schemas/ids/81"));
        assertThat(recordedRequest.getMethod(), is("GET"));
        assertThat(schemaItem.getId(), is(81L));
        assertThat(schemaItem.getSchema(), notNullValue());

    }

}
