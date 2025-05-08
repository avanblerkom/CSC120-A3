import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Implements a chatbot that mirrors user input or provides canned responses.
 */
class Conversation implements Chatbot {

  // Attributes
  private int rounds;
  private ArrayList<String> transcript;
  private static final String[] cannedResponses = {
      "Mmmmm!",
      "Interesting.",
      "Tell me more.",
      "Hmmm.",
      "I see."
  };

  // Mirror words and replacements
  private static final String[] mirrorWords = {"I", "me", "am", "you", "my", "your", "are"};
  private static final String[] mirrorReplacements = {"you", "you", "are", "I", "your", "my", "am"};

  /**
   * Constructor
   */
  public Conversation() {
    transcript = new ArrayList<>();
  }

  /**
   * Starts and runs the conversation with the user.
   */
  public void chat() {
    Scanner scanner = new Scanner(System.in);

    // Query user for number of conversation rounds
    System.out.println("How many rounds?");
    while (!scanner.hasNextInt()) {
      System.out.println("Please enter a valid number.");
      scanner.next();
    }
    rounds = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    // Print greeting
    System.out.println("Hi there! What's on your mind?");
    transcript.add("Bot: Hi there! What's on your mind?");

    // Carry out the requested number of conversation rounds
    for (int i = 0; i < rounds; i++) {
      System.out.print("You: ");
      String userInput = scanner.nextLine();
      transcript.add("You: " + userInput);

      String response = respond(userInput);
      System.out.println("Bot: " + response);
      transcript.add("Bot: " + response);
    }

    // Print goodbye message
    System.out.println("See ya!");
    transcript.add("Bot: See ya!");

    scanner.close();
  }

  /**
   * Prints the transcript of the conversation.
   */
  public void printTranscript() {
    System.out.println("\nTRANSCRIPT:");
    for (String line : transcript) {
      System.out.println(line);
    }
  }

  /**
   * Generates an appropriate response (mirrored or canned) to user input.
   * 
   * @param inputString the user's last line of input
   * @return mirrored or canned response to user input
   */
  public String respond(String inputString) {
    String[] inputWords = inputString.split("\\s+");
    StringBuilder responseBuilder = new StringBuilder();
    boolean mirrored = false;

    for (String word : inputWords) {
      String strippedWord = word.replaceAll("[^a-zA-Z]", ""); // Remove punctuation
      String punctuation = word.replaceAll("[a-zA-Z]", ""); // Extract punctuation
      boolean replaced = false;

      // Check for mirror words and replace them
      for (int i = 0; i < mirrorWords.length; i++) {
        if (strippedWord.equalsIgnoreCase(mirrorWords[i])) {
          responseBuilder.append(mirrorReplacements[i]).append(punctuation).append(" ");
          replaced = true;
          mirrored = true;
          break;
        }
      }

      // If no replacement was made, keep the original word
      if (!replaced) {
        responseBuilder.append(word).append(" ");
      }
    }

    // If no mirror words were found, return a random canned response
    if (!mirrored) {
      Random random = new Random();
      return cannedResponses[random.nextInt(cannedResponses.length)];
    }

    // Return the mirrored response
    return responseBuilder.toString().trim();
  }

  public static void main(String[] args) {
    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();
  }
}
