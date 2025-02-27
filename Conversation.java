import java.util.Scanner;
import java.util.Arrays;

/**
 * initializes conversation and impliments chatbot
 */
class Conversation implements Chatbot {

  // Attributes 
  int rounds;
  String[] transcript;
  static String[] cannedResponses = {
      "Mmmmm!",
      "Interesting.",
      "Tell me more.",
      "Hmmm.",
      "I see.",
  };

  // Mirror words and replacements
  static String[] mirrorWords = {"I", "me", "am", "you", "my", "your", "are"};
  static String[] mirrorReplacements = {"you", "you", "are", "I", "your", "my", "am"};

  /**
   * Constructor 
   */
  public Conversation() {
    // Initialize the transcript array with a size of 100 for demonstration
    transcript = new String[100];
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner scanner = new Scanner(System.in);

    // Query user for number of conversation rounds
    System.out.println("Enter the number of conversation rounds: ");
    rounds = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (rounds > transcript.length) {
      System.out.println("Maximum conversation rounds is " + transcript.length + ". Setting rounds to " + transcript.length + ".");
      rounds = transcript.length;
    }

    // Carry out the requested number of conversation rounds
    for (int i = 0; i < rounds; i++) {
      System.out.print("You: ");
      String userInput = scanner.nextLine();
      String response = respond(userInput);
      System.out.println("Bot: " + response);
      transcript[i] = "You: " + userInput + " | Bot: " + response;
    }

    scanner.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("Transcript of the conversation:");
    for (int i = 0; i < rounds; i++) {
      System.out.println(transcript[i]);
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  
   public String respond(String inputString) {
    String returnString = ""; 
    String[] inputArray = inputString.split(" ");
    String[] outputArray = new String[inputArray.length];
    for (int i = 0; i < inputArray.length; i++) {
      outputArray[i]= inputArray[i];
      for (int j = 0; j < mirrorWords.length; j++) {
        if (inputArray[i].equals(mirrorWords[j])) {
          outputArray[i] = mirrorReplacements[j];
        }
      }
    }
    returnString = String.join(" ", outputArray);

    // If no mirror words were found, return a random canned response
    if (returnString.equalsIgnoreCase(inputString)) {
      int randomIndex = (int) (Math.random() * cannedResponses.length);
      returnString = cannedResponses[randomIndex];
    }

    return returnString; 
  }

  public static void main(String[] arguments) {
    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();
  }
}
