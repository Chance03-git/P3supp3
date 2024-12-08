public class HelloWorldServer {
    public static void runTests() throws Exception {
        // Test the root endpoint
        System.out.println("Running tests...");

        // Create a connection to the root endpoint
        URL url = new URL("http://localhost:4567/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        Scanner scanner = new Scanner(connection.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        // Verify the response
        if ("hello world".equals(response)) {
            System.out.println("Test passed: Root endpoint responded with 'hello world'.");
        } else {
            throw new Exception("Root endpoint did not respond with 'hello world'.");
        }

        // Stop the server after the test
        stopServer();
    }
}