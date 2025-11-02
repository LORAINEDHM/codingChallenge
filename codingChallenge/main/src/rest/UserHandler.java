package rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.User;
import service.Database;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserHandler implements HttpHandler {

    private Database database;
    private static final String BASE_PATH = "/api/users";

    public UserHandler(Database database) {
        this.database = database;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String query = uri.getQuery();
        String response;
        int statusCode = 200;

        try {
            if ("GET".equals(exchange.getRequestMethod())) {

                if (path.equals(BASE_PATH + "/countByCity") && query != null) {
                   response = handleCountByCity(query);

                } else if (path.startsWith(BASE_PATH + "/") && !path.equals(BASE_PATH + "/")) {
                    response = handleGetUserById(path);

                } else if (path.equals(BASE_PATH)) {
                    response = handleGetAllUsers();

                } else {
                    response = "Endpoint Not Found.";
                    statusCode = 404;
                }

            } else {
                response = "Method Not Allowed.";
                statusCode = 405;
            }
        } catch (Exception e) {
            response = "Internal Server Error: " + e.getMessage();
            statusCode = 500;
        }

        // Send Response
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    /**
     * Endpoint: GET /api/users/{id}
     * Retrieve a user by ID
     */
    private String handleGetUserById(String path) {

        String[] parts = path.split("/");
        if (parts.length < 4) {
            return "{\"error\":\"Invalid UUID format\"}";
        }

        try {
            UUID userId = UUID.fromString(parts[3]);
            User user = this.database.getUserById(userId);
            if (user != null) {
                return user.toJson();
            } else {
                // Not Found (404 handled in the main handler)
                return "{\"error\":\"User not found\"}";
            }
        } catch (IllegalArgumentException e) {
            return "{\"error\":\"ID must be a valid UUID\"}";
        }
    }

    /**
     * Endpoint: GET /api/users
     * Retrieve all users
     */
    private String handleGetAllUsers() {
        String jsonUsers = this.database.getAllUsers().stream()
                .map(User::toJson)
                .collect(Collectors.joining(","));
        return "[" + jsonUsers + "]";
    }

    /**
     * Endpoint: GET /api/users/countByCity?city={name}
     * Retrieve the number of users by city
     */
    private String handleCountByCity(String query) {
        if (query != null && query.toLowerCase().startsWith("city=")) {
            String cityValue = query.substring(5);
            // Simple URL decode (replace %20 with space)
            String city = cityValue.replace("%20", " ");
            if (city.isEmpty()) {
                return "{\"error\":\"City parameter cannot be empty\"}";
            }

            long count = this.database.getNumberOfUsersByCity(city);
            return String.format("{\"city\":\"%s\", \"count\":%d}", city, count);
        } else {
            // If query is null or doesn't start with "city="
            return "{\"error\":\"Missing required query parameter: city\"}";
        }
    }
}
