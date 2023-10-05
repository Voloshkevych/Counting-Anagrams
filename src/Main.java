import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a sentence: ");

    //getting user input and converting words to be the same register
    String input = scanner.nextLine().toLowerCase();
    scanner.close();

    //creating array of words splitting them with anything but letters
    String[] words = input.split("[^a-zA-Z]+");

    // filter out duplicate words
    String[] distinctWords = Stream.of(words).distinct().toArray(String[]::new);

    //create a map to hold the frequency array of each distinct word and its occurrence count
    Map<String, Integer> countMap = Stream.of(distinctWords)
        .map(Main::generateFrequencyArray)
        .collect(Collectors.toMap(
            Arrays::toString,
            v -> 1,
            Integer::sum));

    //count the number of anagrams, adjusting for the initial count of 1 per set
    int anagrams = countMap.values().stream()
        .filter(count -> count > 1)
        .mapToInt(count -> count - 1)
        .sum();

    System.out.println("Output: " + anagrams);
  }


  //generate a frequency array for each distinct word, counting letter occurrences
  public static int[] generateFrequencyArray(String word) {
    int[] letterCount = new int[26];
    for (char c : word.toCharArray()) {
      int index = c - 'a';
      if (index >= 0 && index < 26) {
        letterCount[index]++;
      }
    }
    return letterCount;
  }
}