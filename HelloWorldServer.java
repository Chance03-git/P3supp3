import static spark.Spark.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class HelloWorldServer {
    public void testHelloWorldEndpoint() {
        // Send a GET request to the server
        HttpResponse<String> response = Unirest.get("http://localhost:4567/").asString();

        // Verify the response status code and body
        assertEquals(200, response.getStatus());
        assertEquals("hello world", response.getBody());
    }
}