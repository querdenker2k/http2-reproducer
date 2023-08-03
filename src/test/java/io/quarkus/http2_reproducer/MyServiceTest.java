package io.quarkus.http2_reproducer;

import io.quarkus.test.junit.QuarkusTest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MyServiceTest {
    @Test
    public void testHttp() throws IOException, InterruptedException {
        callWithProtocol(HttpClient.Version.HTTP_1_1, 1_000_000);
    }

    @Test
    public void testHttp2Small() throws IOException, InterruptedException {
        callWithProtocol(HttpClient.Version.HTTP_2, 1_000);
    }

    @Test
    public void testHttp2Big() throws IOException, InterruptedException {
        callWithProtocol(HttpClient.Version.HTTP_2, 1_000_000);
    }

    private static void callWithProtocol(HttpClient.Version version, int size) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
            .version(version)
            .build();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8081/rs/bigdata"))
            .POST(HttpRequest.BodyPublishers.ofByteArray(new byte[size]))
            .build();
        HttpResponse<String> response =
            client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(2, response.statusCode() / 100);
    }
}
