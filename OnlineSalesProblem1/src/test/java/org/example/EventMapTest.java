package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// This is a JUnit test class for testing the EventMap class, which models events and their probabilities.

class EventMapTest {

    // Test for a simple event with two outcomes (Head and Tail) and 100 tosses.
    @Test
    @DisplayName("Test 1 having head and tail with 35 and 65 with 100 tosses")
    public void testOne() {
        // Create an instance of EventMap to represent the events.
        EventMap emap = new EventMap();

        // Add events and their probabilities.
        emap.addEvent("Head", 35);
        emap.addEvent("Tail", 65);

        // Get the results of 100 tosses and validate them.
        Map<String, Integer> answer = emap.getResult(100);

        // Define the expected range for "Head" based on its probability.
        int min = (int)(35 * 0.8);
        int max = (int)(35 * 1.2);

        // Assert that the result falls within the expected range.
        assertTrue(answer.get("Head") >= min && answer.get("Head") <= max);

        // Define the expected range for "Tail" based on its probability.
        min = (int)(65 * 0.8);
        max = (int)(65 * 1.2);

        // Assert that the result falls within the expected range for "Tail".
        assertTrue(answer.get("Tail") >= min && answer.get("Tail") <= max);
    }



    // Test for a more complex event with a 6-sided dice and 1000 throws.
    @Test
    @DisplayName("Test 2 having 6 face dice with different probabilities throwing 1000 times")
    public void testTwo() {
        // Create an instance of EventMap to represent the dice outcomes.
        EventMap emap = new EventMap();

        // Add events (Dice1, Dice2, ...) and their probabilities.
        emap.addEvent("Dice1", 10);
        emap.addEvent("Dice2", 12);
        emap.addEvent("Dice3", 18);
        emap.addEvent("Dice4", 22);
        emap.addEvent("Dice5", 18);
        emap.addEvent("Dice6", 20);

        // Get the results of 1000 throws and validate them.
        Map<String, Integer> answer = emap.getResult(1000);

        // Calculate the expected range for each dice outcome based on probabilities.
        int[] min = new int[emap.getNumbersOfEvent()];
        int[] max = new int[emap.getNumbersOfEvent()];
        double[] percentageProbability = emap.getProbabilityArray();

        // Calculate the min and max values for each outcome based on probabilities.
        for (int i = 0; i < min.length; i++) {
            min[i] = (int)(percentageProbability[i] * 10 * 0.8);
            max[i] = (int)(percentageProbability[i] * 10 * 1.2);
        }

        int i = 0;

        // Validate each dice outcome falls within the expected range.
        for (String event : answer.keySet()) {
            assertTrue(answer.get(event) >= min[i] && answer.get(event) <= max[i], () -> "Dice is having a problem");
            i++;
        }
    }
}
