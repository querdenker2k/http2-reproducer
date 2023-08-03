package io.quarkus.http2_reproducer;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/bigdata")
public class MyService {
    @POST
    public String receiveBigData(byte[] data) {
        return "received: " + data.length;
    }
}
