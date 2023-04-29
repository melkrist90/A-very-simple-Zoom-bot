import java.util.TimerTask;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Calendar;

// Task: Use keyboard shortcuts (Alt + Q) to leave the meeting.
class ZoomTerminator extends TimerTask {
  Robot robot;

  public ZoomTerminator() throws Exception {
    robot = new Robot();
  }

  @Override
  public void run() {
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_Q);
    robot.keyRelease(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_Q);

    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);

    System.out.println("Left meeting at " + Calendar.getInstance().getTime());
    System.exit(0);
  }
}
