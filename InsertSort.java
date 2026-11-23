import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads lines of numbers from input.txt, sorts each line using
 * insertion sort and writes the results to output.txt.
 */
final class InsertSort {

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws IllegalStateException if called
     */
    private InsertSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Sorts a list of integers using insertion sort.
     *
     * @param numbers the list to sort
     */
    static void insertionSort(final ArrayList<Integer> numbers) {
        for (int index = 1; index < numbers.size(); index++) {
            final int key = numbers.get(index);
            int position = index - 1;

            while (position >= 0 && numbers.get(position) > key) {
                numbers.set(position + 1, numbers.get(position));
                position--;
            }
            numbers.set(position + 1, key);
        }
    }

    /**
     * The starting method for the program.
     *
     * @param args nothing passed in
     */
    public static void main(final String[] args) {
        try (Scanner reader = new Scanner(new File("input.txt"));
             FileWriter writer = new FileWriter("output.txt")) {

            while (reader.hasNextLine()) {
                final String line = reader.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                final ArrayList<Integer> numbers = new ArrayList<>();
                for (String token : line.split("\\s+")) {
                    numbers.add(Integer.parseInt(token));
                }

                insertionSort(numbers);

                final StringBuilder result = new StringBuilder();
                for (int number : numbers) {
                    result.append(number).append(' ');
                }
                writer.write(result.toString().trim() + "\n");
            }
            System.out.println("Done! See output.txt");
        } catch (IOException error) {
            System.out.println("File error: " + error.getMessage());
        }
    }
}