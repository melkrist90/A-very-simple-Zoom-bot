import java.util.TimerTask;
import java.awt.Desktop;
import java.net.URI;
import java.util.Calendar;

// Task: Launches Zoom by opening the invite in the user's default browser.
class ZoomLauncher extends TimerTask {
  Desktop desktop;
  String inviteURL;

  public ZoomLauncher(String inviteURL) {
    desktop = Desktop.getDesktop();
    this.inviteURL = inviteURL;
  }

  @Override
  public void run() {
    try {
      desktop.browse(new URI(inviteURL));
      System.out.println("Joined meeting at " + Calendar.getInstance().getTime());
    } catch (Exception e) {
      System.out.println("Error: The invite URL is invalid");
    }
  }
}
