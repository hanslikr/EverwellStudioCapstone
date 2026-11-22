// Sources/refrenced
//https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpExchange.html
//https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/package-summary.html
//https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpExchange.html

package com.everwell.database;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class WebsiteServer {

    public static void main(String[] args) throws IOException {

        // web server on port 8080.
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        // show website the website when someone visits homepage.
        server.createContext("/", exchange -> {

            // read existing HTML file.
            Path file = Path.of("..", "index.html");
            byte[] page = Files.readAllBytes(file);

            // tell the browser the response is a HTML page.
            exchange.getResponseHeaders().set(
                    "Content-Type", "text/html; charset=UTF-8");

            exchange.sendResponseHeaders(200, page.length);

            exchange.getResponseBody().write(page);

            exchange.close();
        });

        // handle customer registration.
        server.createContext("/register", exchange -> {

            String response;
            int responseCode = 200;

            try {
                // get customer information from the registration form.
                InputStream input = exchange.getRequestBody();
                String formData = new String(input.readAllBytes(), StandardCharsets.UTF_8);

                // separate the customer information.
                String[] fields = formData.split("&");

                String firstName = URLDecoder.decode(fields[0].split("=")[1], StandardCharsets.UTF_8);
                String lastName = URLDecoder.decode(fields[1].split("=")[1], StandardCharsets.UTF_8);
                String email = URLDecoder.decode(fields[2].split("=")[1], StandardCharsets.UTF_8);
                String dateOfBirth = URLDecoder.decode(fields[3].split("=")[1], StandardCharsets.UTF_8);
                String password = URLDecoder.decode(fields[4].split("=")[1], StandardCharsets.UTF_8);

                // save customer information in the database.
                CustomerRegistration registration = new CustomerRegistration();

                boolean registered = registration.registerCustomer(
                        firstName,
                        lastName,
                        email,
                        LocalDate.parse(dateOfBirth),
                        password);

                // show if the customer was registered successfully.
                if (registered) {
                    response = "Registration successful!";
                } else {
                    response = "Registration was not completed.";
                }

            } catch (Exception e) {
                e.printStackTrace();
                response = "Registration error: " + e.getMessage();
                responseCode = 500;
            }

            // tell browser request was successful =200- responsebody=registration message
            // sent back to website. close connection after sending.
            // https://www.youtube.com/watch?v=LJjIaCKuzoc&utm
            // https://www.youtube.com/watch?v=9HQkw89Ulqk&utm
            exchange.sendResponseHeaders(responseCode, response.getBytes(StandardCharsets.UTF_8).length);
            exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
            exchange.close();
        });

        // start web server.
        server.start();

        System.out.println("Website is running at http://localhost:8080");
    }
}