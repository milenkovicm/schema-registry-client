package com.github.milenkovicm.avro.registry;

import com.squareup.okhttp.mockwebserver.rule.MockWebServerRule;
import org.junit.Rule;
import org.junit.rules.Timeout;

public abstract class AbstractWebTest {

    @Rule
    public final MockWebServerRule server = new MockWebServerRule();

    @Rule
    public Timeout globalTimeout= new Timeout(3000);

}
