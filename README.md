# OpensalesAssignment
# Task1
# Probability Event Simulation

This repository contains a Java program for simulating events that follow given biasness. The program allows you to define events and their associated probabilities, and then it generates outcomes based on those probabilities. This simulation can be used for modeling various events, such as rolling a biased dice or flipping a biased coin.

## Problem Statement

The main objective of this program is to simulate events with predefined probabilities. The key requirements are as follows:

- Accept a map of possible outcomes and their associated probabilities.
- Generate outcomes based on the provided probabilities.
- Ensure that each occurrence of the event generates one outcome independently.
- Observe a large number of occurrences (e.g., 1000), and the probability distribution should roughly follow the given biasness.

## Examples

Here are a couple of examples of how this program can be used:

1. Rolling a six-faced biased dice:
   - Input: [{1, 10}, {2, 30}, {3, 15}, {4, 15}, {5, 30}, {6, 0}]

2. Flipping a biased coin:
   - Input: [{"Head": 35}, {"Tail": 65}]

## Usage

### EventMap Class

The `EventMap` class is the core component of this simulation. It provides methods for managing events and their probabilities. Here's an overview of its methods:

- `addEvent(String event, double probability)`: Adds a new event to the map with its probability.

- `isEventPresent(String event)`: Checks if an event is already present in the map.

- `getNumbersOfEvent()`: Returns the number of events in the map.

- `getEvents()`: Returns a list of event names.

- `getProbabilityArray()`: Converts probabilities to percentages and returns an array.

- `getResult(int times)`: Simulates event occurrences and returns the results.

### Running the Simulation

To use the program, follow these steps:

1. Compile and run the `GameOfLuck` class.

2. Enter the event names and their probabilities as prompted. You can add as many events as needed.

3. Specify the number of times you want to trigger the event.

4. The program will generate outcomes and display the results, showing how many times each event occurred and its approximate percentage.

## Example Output

Here is an example output based on the provided input:

- Input: [{1: 35}, {2: 65}]  (1=Head, 2=Tail)

  On triggering the event 1000 times:
  - Head appeared 332 times, which is roughly in line with the biasness.
  - Tail appeared 668 times, which is roughly in line with the biasness.

Please note that this is just one possible outcome, and the simulation may vary each time it is run.
# Task2

Bulk Mathematical Expression Evaluation using a Web API
This project focuses on solving the problem of evaluating multiple mathematical expressions in bulk using a public Web API. The goal is to accept a series of mathematical expressions, submit them to the API for evaluation, and display the results on the console.

Problem Statement
The problem can be summarized as follows:

Create a program that accepts multiple mathematical expressions for evaluation.
Utilize a public Web API to evaluate these expressions.
Display the evaluated results on the console.
Handle constraints where the API supports only 50 requests per second per client, but the application needs to evaluate at least 500 expressions per second.
Consider scenarios where the user may initiate more concurrent requests than the application can handle.
Approach and Reasoning
To solve this problem, we adopt the following approach:

API Selection: Choose a suitable public Web API that supports mathematical expression evaluation. In this example, we used the mathjs.org API.

Request Batching: Given the constraint of 50 requests per second, we batch multiple expressions into a single API request to maximize efficiency. The application collects expressions until either the user enters "end" or the batch size reaches 50.

Concurrency: To meet the requirement of evaluating at least 500 expressions per second, we use multithreading. We submit API request tasks to an executor service with a fixed number of threads (e.g., 10 threads). This allows us to process multiple batches concurrently.

Handling Large Concurrent Requests: The application handles situations where the user initiates more concurrent requests than the application can handle. If the user tries to submit more than 50 expressions in a single batch or more than 500 expressions overall, the program will throw an exception.

Implementation
The Java program MathExpressionCalculator demonstrates the implementation of this approach. Here's an overview of the key components:

API Endpoint: We define the API endpoint as a constant (API) and set the maximum requests per second allowed by the API (MAX_REQUEST_OF_API).

CallAPI Method: The callAPI method sends a batch of mathematical expressions to the API, collects the results, and returns them as a list of strings.

Main Method: In the main method, we collect expressions from the user until "end" is entered or a batch limit is reached. Then, we submit a task to the executor service to call the API and display the results.

Concurrency: The application uses an executor service with a fixed number of threads to handle concurrency. This allows efficient processing of multiple batches of expressions concurrently.

Usage
To use the program:

Compile and run the MathExpressionCalculator class.

Enter mathematical expressions one by one, pressing "Enter" after each. Enter "end" to initiate the evaluation.

The program will evaluate the expressions using the Web API and display the results on the console.

# Task3
# Problem Statement: Integer Computation Script

This README provides an overview of a Bash/Python script designed to perform computations on an integer input (n) based on certain conditions. The task is to identify and correct the bugs in the provided script, making it function correctly. You can choose either the Bash or Python script, as both will be corrected with only a few changes.

## Task Description

The script is designed to perform the following computations based on the value of the input integer (n):

1. If n is less than 10: Calculate the square of n.
   - Example: 4 => 16

2. If n is between 10 and 20: Calculate the factorial of (n-10).
   - Example: 15 => 120

3. If n is greater than 20: Calculate the sum of all integers between 1 and (n-20).
   - Example: 25 => 15

## Provided Code (with Bugs)

```python
def compute(n):
    if n < 10:
        out = n ** 2
    elif n < 20:
        out = 1
        for i in range(1, n-10):
            out *= i
    else:
        lim = n - 20
        out = lim * lim
        out = out - lim
        out = out / 2 
    print(out)

n = int(input("Enter an integer: "))
compute(n)
```

## Bug Fixes and Corrections

Here are the identified bugs in the provided code and the corrected script:

### Bug 1: Incorrect Factorial Calculation
- **Description:** When n is between 10 and 20, the script calculates the factorial of (n-10). However, it excludes the number n-10 from the product, leading to incorrect results.
- **Fix:** Changed the upper bound of the `range` function from `n-10` to `n-9` to include n-10 in the product.

### Bug 2: Incorrect Summation Calculation
- **Description:** When n is greater than 20, the script attempts to calculate the sum of all integers between 1 and (n-20). However, the original implementation subtracts n-20 instead of adding it to the result.
- **Fix:** Changed the subtraction operation to addition to correctly calculate the summation.

### Additional Note:
- The script now also includes a hint for debugging purposes, indicating the two identified bugs and the corresponding fixes.

### Corrected Script

```python
def compute(n):
    if n < 10:
        out = n ** 2
    elif n < 20:
        out = 1
        for i in range(1, n - 9):  # Fixed the range upper bound from n-10 to n-9
            out *= i
    else:
        lim = n - 20
        out = lim * lim
        out = out + lim  # Changed '-' to '+'
        out = out / 2 
    print(out)

n: int = int(input("Enter an integer: "))
compute(n)
```

Now, the script has been corrected, and it should perform the desired computations accurately based on the given conditions.

Feel free to choose either the Bash or Python script, apply the necessary fixes, and enjoy accurate integer computations based on the provided conditions.

---

Please note that the corrections were made in the Python script, but similar adjustments can be applied to the Bash script if needed.
