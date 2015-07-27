package com.github.milenkovicm.avro.registry;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DefaultAsyncSchemaRegistryTest extends AbstractWebTest {

    @Test
    public void getSchema_forName_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final DefaultAsyncSchemaRegistry registry = new DefaultAsyncSchemaRegistry("http://localhost:" + server.getPort());
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
    public void getSchema_forNameCount_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final DefaultAsyncSchemaRegistry registry = new DefaultAsyncSchemaRegistry("http://localhost:" + server.getPort());

        // first one should hit rest other one should hit cache
        registry.lookup("test-schema").toBlocking().single();
        registry.lookup("test-schema").toBlocking().single();

        assertThat(server.getRequestCount(), is(1));
    }

    @Test
    public void getSchema_forId_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_ID_RESPONSE));

        final DefaultAsyncSchemaRegistry repo = new DefaultAsyncSchemaRegistry("http://localhost:" + server.getPort());
        final RegistryItem lookup = repo.lookup(81).toBlocking().single();

        final RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath(), is("/schemas/ids/81"));
        assertThat(recordedRequest.getMethod(), is("GET"));
        assertThat(lookup.getId(), is(81L));
        assertThat(lookup.getSchema(), notNullValue());
    }

    @Test
    public void getSchema_forIdCount_success() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(Default.DEFAULT_NAME_RESPONSE));

        final DefaultAsyncSchemaRegistry registry = new DefaultAsyncSchemaRegistry("http://localhost:" + server.getPort());

        // first one should hit rest other one should hit cache
        registry.lookup(81).toBlocking().single();
        registry.lookup(81).toBlocking().single();

        assertThat(server.getRequestCount(), is(1));
    }
}
