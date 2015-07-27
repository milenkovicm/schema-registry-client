package com.github.milenkovicm.avro.registry;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DefaultSyncSchemaRegistryTest extends AbstractWebTest{
    @Test
    public void getSchema_forName_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final DefaultSyncSchemaRegistry registry = new DefaultSyncSchemaRegistry("http://localhost:" + server.getPort());
        final RegistryItem schemaItem = registry.lookup("test-schema");

        final RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath(), is("/subjects/test-schema/versions/latest"));
        assertThat(recordedRequest.getMethod(), is("GET"));
        assertThat(schemaItem.getSubject(), is("test-schema"));
        assertThat(schemaItem.getVersion(), is(1));
        assertThat(schemaItem.getId(), is(81L));
        assertThat(schemaItem.getSchema(), notNullValue());
    }

    @Test
    public void getSchema_forNameCount_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final DefaultSyncSchemaRegistry registry = new DefaultSyncSchemaRegistry("http://localhost:" + server.getPort());

        registry.lookup("test-schema");
        registry.lookup("test-schema");

        // first one should hit rest other one should hit cache
        assertThat(server.getRequestCount(), is(1));
    }

    @Test
    public void getSchema_forId_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_ID_RESPONSE));

        final DefaultSyncSchemaRegistry registry = new DefaultSyncSchemaRegistry("http://localhost:" + server.getPort());
        final RegistryItem schemaItem = registry.lookup(81);

        final RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath(), is("/schemas/ids/81"));
        assertThat(recordedRequest.getMethod(), is("GET"));
        assertThat(schemaItem.getId(), is(81L));
        assertThat(schemaItem.getSchema(), notNullValue());
    }

    @Test
    public void getSchema_forIdCount_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final DefaultSyncSchemaRegistry registry = new DefaultSyncSchemaRegistry("http://localhost:" + server.getPort());

        registry.lookup(81);
        registry.lookup(81);
        // first one should hit rest other one should hit cache
        assertThat(server.getRequestCount(), is(1));
    }

}
