import static spark.Spark.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * HelloWorldApp demonstrates a simple web server with a single GET endpoint
 * at the root ("/") that responds with "hello world".
 * 
 * It also includes tests for the endpoint.
 */
public class HelloWorldApp {

    /**
     * Starts the Spark server and defines the root GET endpoint.
     */
    public static void main(String[] args) {
        port(4567); // Set the port to 4567
        get("/", (req, res) -> "hello world"); // Define the root GET endpoint
    }

    /**
     * Stops the Spark server.
     */
    public static void stopServer() {
        stop();
    }

    /**
     * Unit tests for HelloWorldApp.
     */
    public static class HelloWorldTest {

        @BeforeAll
        public static void setup() {
            Thread serverThread = new Thread(() -> HelloWorldApp.main(null));
            serverThread.start();
            try {
                // Allow some time for the server to start
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @AfterAll
        public static void teardown() {
            HelloWorldApp.stopServer();
        }

        @Test
        public void testHelloWorldEndpoint() {
            // Send a GET request to the server
            HttpResponse<String> response = Unirest.get("http://localhost:4567/").asString();

            // Verify the response status code and body
            assertEquals(200, response.getStatus());
            assertEquals("hello world", response.getBody());
        }
    }
}
