import java.util.TimerTask;
import java.awt.Robot;
import java.awt.event.KeyEvent;

// Task: Uses the Chatbot to post messages in the meeting chat.
class ZoomChat extends TimerTask {
  Chatbot chatbot;

  public ZoomChat() throws Exception {
    chatbot = new Chatbot();
  }

  @Override
  public void run() {
    chatbot.write("Good morning.");

    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      System.out.println("Error: Thread failed in the run method of ZoomChat");
    }

    chatbot.write("I'm running a bit late.");
  }
}
