import java.util.Timer;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

class ZoomBotMain {
  public static void main(String[] args) {
    // 1: Allow the default browser to open Zoom.

    // 2: Paste the invitation link.
    String inviteURL = "";

    // 3: Decide when to join the meeting. (Windows users can easily automate the launch of the SimpleZoomStandIn with Task Scheduler.)
    Date timeToJoin = setTime(2022, 06, 24, 7, 55);

    // 4: Decide when to start the chatbot
    Date timeToChat = setTime(2022, 06, 24, 8, 00);  // Year, month, day, hour, minute

    // 5: Decide when to leave the meeting.
    Date timeToLeave = setTime(2022, 06, 24, 9, 00); // Year, month, day, hour, minute

    // 6: Write your message in the run method of the Zoom chat.

    // 7: Ready to go.
    start(inviteURL, timeToJoin, timeToChat, timeToLeave);
  }


  // Method: Calls the run() methods of Launch, Chat and Terminator objects.
  private static void start(String inviteURL, Date timeToJoin, Date timeToChat, Date timeToLeave) {
    timerControl(timeToJoin, timeToChat, timeToLeave); // Makes sure that entered dates and times are coherent.
    System.out.println("Joining meeting at " + timeToJoin);
    System.out.println("Chatbot will be activated at " + timeToChat);
    System.out.println("Leaving meeting at " + timeToLeave);

    ZoomLauncher joinMeeting = null; // Creating an object of the Task class named "Launcher". This will join the meeting.
    joinMeeting = new ZoomLauncher(inviteURL);

    Timer joinTimer = new Timer();
    joinTimer.schedule(joinMeeting, timeToJoin);

    ZoomChat chat = null; // Creating an object of the Task class named "Chat". This will post messages in the chat.

    try {
      chat = new ZoomChat();
    } catch (Exception e) {
      System.out.println("Error: Unable to activate chatbot");
    }

    Timer chatTimer = new Timer();
    chatTimer.schedule(chat, timeToChat);

    ZoomTerminator leaveMeeting = null; // Creating an object of the Task class named "Terminator". This will leave the meeting.

    try {
      leaveMeeting = new ZoomTerminator();
    } catch (Exception e) {
      System.out.println("Error: Unable to leave meeting");
    }

    Timer leaveTimer = new Timer(); // Creating a Timer object that will call the run() metod of the Terminator at set date and time.
    leaveTimer.schedule(leaveMeeting, timeToLeave);
  }


  // Method: Setting the timer.
  private static Date setTime(int year, int month, int day, int hour, int minute) {
    GregorianCalendar timer = new GregorianCalendar();
    timer.set(year, month-1, day, hour, minute, 00); // Note that January is 00
    return timer.getTime();
  }


  // Method: If the entered dates and times are incoherent, the method will call exit().
  private static void timerControl(Date join, Date chat, Date leave) {
    if (join.compareTo(new Date()) <= 0 || chat.compareTo(join) <= 0 || leave.compareTo(chat) <= 0) {
      System.out.println("Error: Invalid time value(s)");
      System.exit(1);
    } else {
      return;
    }
  }
}
