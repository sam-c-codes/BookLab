import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PigLatinTranslator {
  // Method to translate the entire book

  public static Book translate(Book input) {
    Book translatedBook = new Book();
    translatedBook.setTitle(input.getTitle());


    // Loop through each line and translate it
    for (int i = 0; i < input.getLineCount(); i++) {
      String line = input.getLine(i);
      translatedBook.appendLine(translate(line));
    }
    return translatedBook;
  }

  // Method to translate a single sentence
  public static String translate(String input) {
    if (input.trim().isEmpty()) {
      return input;
    }
    StringBuilder result = new StringBuilder();
      for (String word : input.split(" ")) {
        if (!word.isEmpty()) {
          result.append(translateWord(word)).append(" ");
        }
      }
      return result.toString().trim();
  }

  // Method to handle translation of individual words
  private static String translateWord(String word) {
    // If the word contains hyphens, split and translate each part
    if (word.contains("-")) {
      StringBuilder result = new StringBuilder();
      String[] parts = word.split("-");
      for (int i = 0; i < parts.length; i++) {
        result.append(translateSingleWord(parts[i]));
        if (i < parts.length - 1) {
          result.append("-");
        }
      }
      return result.toString();
      } else {
        return translateSingleWord(word);
      }
  }
  
  private static String translateSingleWord(String word) {
    // Match the word and punctuation
    Pattern pattern = Pattern.compile("([a-zA-Z]+)([^a-zA-Z]*)");
    Matcher matcher = pattern.matcher(word);
    if (matcher.matches()) {
      String mainPart = matcher.group(1);  // Word without punctuation
      String punctuation = matcher.group(2);  // Punctuation
      // Translate the core of the word and apply capitalization rules
      String translated = translateCore(mainPart);
      // Apply original capitalization to the translated word
      return applyOriginalCapitalization(mainPart, translated) + punctuation;
    }
    return word; // Return the word as is if it doesn't match the pattern
  }

  private static String translateCore(String word) {
    if (word.isEmpty()) return word;
    // If the word starts with a vowel, just append "ay"
      boolean startsWithVowel = "aeiouAEIOU".indexOf(word.charAt(0)) != -1;
      if (startsWithVowel) {
        return word + "ay";
      } else {
        // Otherwise, move the first consonant cluster to the end and add "ay"
        int firstVowelIndex = -1;
        for (int i = 0; i < word.length(); i++) {
          if ("aeiouAEIOU".indexOf(word.charAt(i)) != -1) {
            firstVowelIndex = i;
            break;
          }
        }
        if (firstVowelIndex != -1) {
          String start = word.substring(firstVowelIndex);
          String end = word.substring(0, firstVowelIndex);
          return start + end + "ay";
        } else {
          return word + "ay";  // If no vowel, treat as all consonants
        }
      }
  }

  private static String applyOriginalCapitalization(String original, String translated) {
    // Check if the first letter of the original word is uppercase
    if (Character.isUpperCase(original.charAt(0))) {
      // Capitalize the translated word to match the first letter's case
      return capitalizeWord(translated);
    } else {
      // Otherwise, return the translated word in lowercase
      return translated.toLowerCase();
      }
  }
  
  private static String capitalizeWord(String word) {
    if (word.length() == 0) {
      return word;
    }
    // Capitalize the first letter, and make the rest lowercase
    return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
  }
}
