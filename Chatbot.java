import java.awt.Robot;
import java.awt.event.KeyEvent;

// A robot that posts text in the meeting's chat using KeyEvent.
class Chatbot {
  Robot chatbot;

  public Chatbot() throws Exception {
    chatbot = new Robot();
  }

  public void write(String text) {
    chatbot.keyPress(KeyEvent.VK_ALT);
    chatbot.keyPress(KeyEvent.VK_H);
    chatbot.keyRelease(KeyEvent.VK_ALT);
    chatbot.keyRelease(KeyEvent.VK_H);

    for (int i = 0; i < text.length(); i++) {
      int keyCode = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(text.charAt(i));
      if(Character.isUpperCase(text.charAt(i))) {
        chatbot.keyPress(KeyEvent.VK_SHIFT);
      } else if (text.charAt(i) == '?') {
        chatbot.keyPress(KeyEvent.VK_SHIFT);
        chatbot.keyPress(KeyEvent.VK_PLUS);
        chatbot.keyRelease(KeyEvent.VK_SHIFT);
        chatbot.keyRelease(KeyEvent.VK_PLUS);
        break;
      }

      chatbot.keyPress(keyCode);
      chatbot.keyRelease(keyCode);
      chatbot.keyRelease(KeyEvent.VK_SHIFT);
    }

    chatbot.keyPress(KeyEvent.VK_ENTER);
    chatbot.keyRelease(KeyEvent.VK_ENTER);
    chatbot.keyPress(KeyEvent.VK_ESCAPE);
    chatbot.keyRelease(KeyEvent.VK_ESCAPE);
  }
}
