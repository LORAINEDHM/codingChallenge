package service;

import com.sun.net.httpserver.HttpServer;
import rest.UserHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ApiServer {

    private static final int PORT = 8080;
    private static final String BASE_PATH = "/api/users";

    private Database database;

    public ApiServer(Database database) {
        this.database = database;
    }


    public HttpServer setUp() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext(BASE_PATH, new UserHandler(this.database));
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();

        System.out.println("Server started on port " + PORT);
        System.out.println("Endpoints:\n"
                + "  - All users: GET http://localhost:" + PORT + BASE_PATH + "\n"
                + "  - User by ID: GET http://localhost:" + PORT + BASE_PATH + "/{uuid}\n"
                + "  - Count by city: GET http://localhost:" + PORT + BASE_PATH + "/countByCity?city={name}");

        return server;
    }
}
