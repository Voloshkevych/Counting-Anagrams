import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    System.out.print("Enter a sentence: ");
//
//    //getting user input and converting words to be the same register
//    String input = scanner.nextLine().toLowerCase();
//    scanner.close();
//
//    System.out.println("Output: " + anagrams);

    String test1 = "aa aa odg dog gdo";
    String test2 = "a c b c run urn urn";

    System.out.println("Anagrams in test1: " + countAnagrams(test1));
    System.out.println("Anagrams in test2: " + countAnagrams(test2));
  }

  //method to count anagrams in string
  public static int countAnagrams(String string) {
    String[] words = string.split("[^a-z]+");

    //filter out duplicate words and create a map to hold the frequency array
    //of each distinct word and its occurrence count
    Map<String, Integer> countMap = Stream.of(words)
        .distinct()
        .map(Main::generateFrequencyArray)
        .collect(Collectors.toMap(
            Arrays::toString,
            v -> 1,
            Integer::sum));

    //count the number of anagrams, adjusting for the initial count of 1 per set
    return countMap.values().stream()
        .filter(count -> count > 1)
        .mapToInt(count -> count - 1)
        .sum();
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