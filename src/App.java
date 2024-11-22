import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        // URL for "Romeo and Juliet" from Project Gutenberg
        String bookUrl1 = "https://www.gutenberg.org/cache/epub/11/pg11.txt";
        // URL for "Alice's Adventures in Wonderland" from Project Gutenberg
        String bookUrl2 = "https://www.gutenberg.org/cache/epub/11/pg11.txt";
        // Read and translate "Romeo and Juliet"
        Book input1 = new Book();
        input1.readFromUrl("Romeo and Juliet", bookUrl1);

        // Count the number of words in the original book
        int originalWordCount1 = countWords(input1);
        System.out.println("Book: Romeo and Juliet");
        System.out.println("Original word count: " + originalWordCount1);

        // Translate the book into Pig Latin
        Book translatedBook1 = PigLatinTranslator.translate(input1);

        // Count the number of words in the translated book
        int translatedWordCount1 = countWords(translatedBook1);
        System.out.println("Translated word count: " + translatedWordCount1);

        // Save the translated book to a text file
        String fileName1 = "translated_romeo_and_juliet.txt";
        saveToFile(translatedBook1, fileName1);
        System.out.println("Translated book saved to: " + fileName1);

        // --- Now process the second book: "Alice's Adventures in Wonderland" ---

        // Read and translate "Alice's Adventures in Wonderland"
        Book input2 = new Book();
        input2.readFromUrl("Alice's Adventures in Wonderland", bookUrl2);

        // Count the number of words in the original book
        int originalWordCount2 = countWords(input2);
        System.out.println("Book: Alice's Adventures in Wonderland");
        System.out.println("Original word count: " + originalWordCount2);

        // Translate the book into Pig Latin
        Book translatedBook2 = PigLatinTranslator.translate(input2);

        // Count the number of words in the translated book
        int translatedWordCount2 = countWords(translatedBook2);
        System.out.println("Translated word count: " + translatedWordCount2);

        // Save the translated book to a text file
        String fileName2 = "translated_alices_adventures_in_wonderland.txt";
        saveToFile(translatedBook2, fileName2);
        System.out.println("Translated book saved to: " + fileName2);

        // Run the test suite to verify the translation logic
        System.out.println("\nRunning Test Suite...");
        TestSuite.run();  // This will print out whether the tests pass or fail.
    }

    // Method to count words in a book
    private static int countWords(Book book) {
        int wordCount = 0;
        for (int i = 0; i < book.getLineCount(); i++) {
            String line = book.getLine(i);
            wordCount += line.split("\\s+").length;  // Split by whitespace to count words
        }
        return wordCount;
    }

    // Method to save a book to a text file
    private static void saveToFile(Book book, String fileName) throws IOException {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileName))) {
            writer.write(book.getTitle() + "\n\n");  // Write the title at the top of the file
            for (int i = 0; i < book.getLineCount(); i++) {
                writer.write(book.getLine(i) + "\n");
            }
            System.out.println("Book saved to: " + fileName);
        }
    }
}
