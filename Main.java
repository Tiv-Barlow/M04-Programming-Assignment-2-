//M04 Programming Assignment (2)
//Ivy Tech Community College
//SDEV 200 - Java
//Professor Bumgardner
//Nativida Muhammad
//14 April 2024

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashSet;
import java.io.IOException;
import java.util.Set;

public class Main {

  private static final Set<String> keywords = new HashSet<>();
  static {
    // Populate the set of Java keywords
    keywords.add("abstract");
    keywords.add("assert");
    // Add all Java keywords...
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: Java keyword counter filename.");
      return;
    }

    String filename = args[0];
    int keywordCount = countKeywords(filename);
    System.out.println("Number of keywords founed: " + keywordCount);
  }

  private static int countKeywords(String filename) {
    int count = 0;
    boolean inComment = false;
    boolean inString = false;

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        // Check for comments
        if (line.contains("//")) {
          line = line.substring(0, line.indexOf("//"));
        }
        if (line.contains("/*")) {
          inComment = true;
        }
        if (line.contains("*/")) {
          inComment = false;
          continue;
        }
        if (inComment)
          continue;

        // Check for strings
        if (line.contains("\"")) {
          inString = !inString;
        }
        if (inString)
          continue;

        // Extract and count keywords
        for (String word : line.split("\\W+")) {
          if (keywords.contains(word)) {
            count++;
          }
        }
      }
    } catch (IOException e) {
      System.err.println("There was an error reading file: " + e.getMessage());
    }
    return count;
  }
}
