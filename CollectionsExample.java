
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class CollectionsExample {

  public static void main(String[] args) {
    // Create Scanner object for input
    Scanner scanner = new Scanner(System.in);

    // Create ArrayList, HashSet, and HashMap
    ArrayList<String> list = new ArrayList<>();
    HashSet<String> set = new HashSet<>();
    HashMap<String, Integer> map = new HashMap<>();

    // Prompt the user to input 10 strings
    System.out.println("Enter 10 strings:");

    // Taking 10 strings as input
    for (int i = 0; i < 10; i++) {
      String input = scanner.nextLine();

      // Add the string to the ArrayList and HashSet
      list.add(input);
      set.add(input);

      // Update frequency count in HashMap
      map.put(input, map.getOrDefault(input, 0) + 1);
    }

    // Close the scanner
    scanner.close();

    // Print the content of the ArrayList
    System.out.println("\nContents of ArrayList:");
    for (String item : list) {
      System.out.println(item);
    }

    // Print the content of the HashSet
    System.out.println("\nContents of HashSet:");
    for (String item : set) {
      System.out.println(item);
    }

    // Print the content of the HashMap (Word and Frequency)
    System.out.println("\nContents of HashMap (Word and Frequency):");
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
}
