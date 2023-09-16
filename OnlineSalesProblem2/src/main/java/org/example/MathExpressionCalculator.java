package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MathExpressionCalculator {
    // Define constants for maximum API requests and API URL
    private static final int MAX_REQUEST_OF_API = 50;
    private static final int MAX_REQUEST_OF_APP = 500;
    private static final String API = "http://api.mathjs.org/v4/";

    // Method to call the API with a list of expressions and return results
    public List<String> callAPI(List<String> expressions) throws IOException, InterruptedException {
        // Create a JSON request body with the expressions
        JSONObject requestBody = new JSONObject();
        requestBody.put("expr", expressions);

        // Initialize a list to store the API response
        List<String> result = new ArrayList<>();

        // Create a URL and HTTP request
        URL url = new URL(API);
        HttpRequest request = HttpRequest.newBuilder(URI.create(API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        // Send the HTTP request and handle the response
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() <= 299) {
            // Parse the JSON response and extract the results
            JSONObject responseReceived = new JSONObject(response.body());
            JSONArray array = (JSONArray) responseReceived.get("result");
            for (Object o : array.toList()) {
                result.add((String) o);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        MathExpressionCalculator mec = new MathExpressionCalculator();

        // Initialize a scanner to read user input and a list to store expressions
        Scanner scanner = new Scanner(System.in);
        List<String> expressions = new ArrayList<>();

        // Create an executor service with a fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(MAX_REQUEST_OF_APP / MAX_REQUEST_OF_API);

        while (true) {
            String expression = scanner.nextLine();
            if (expression.equals("end")) {
                // When "end" is entered, submit a task to call the API with the expressions
                executor.submit(() -> {
                    try {
                        // Call the API with the collected expressions
                        List<String> answer = mec.callAPI(expressions);

                        // Print the results
                        for (int i = 0; i < answer.size(); i++) {
                            System.out.println(expressions.get(i) + " => " + answer.get(i));
                        }

                        // Clear the expressions list
                        expressions.clear();
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            }

            // Check if the maximum API request limit is reached
            if (expressions.size() == MAX_REQUEST_OF_API) {
                throw new Exception("Max request limit");
            } else {
                // Add the expression to the list
                expressions.add(expression);
            }
        }
    }
}
