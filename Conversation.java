import java.util.Scanner;

class Conversation implements Chatbot {

  // Attributes 
  int rounds;
  String[] transcript;
  static String[] cannedResponses = {
      "Mmmmm!",
      "Interesting.",
      "Tell me more.",
      "Go on.",
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
    System.out.print("Enter the number of conversation rounds: ");
    rounds = scanner.nextInt();
    scanner.nextLine(); // Consume newline

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
    String returnString = inputString; 

    // Iterate through mirror words and replace them with mirror replacements
    for (int i = 0; i < mirrorWords.length; i++) {
      returnString = returnString.replaceAll("\\b" + mirrorWords[i] + "\\b", mirrorReplacements[i]);
    }

    // If no mirror words were found, return a random canned response
    if (returnString.equals(inputString)) {
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
