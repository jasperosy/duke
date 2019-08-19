import java.util.Scanner;

public class Duke {
    public Duke () {
        hello();
    }
    public void hello() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public void GreetEchoExit(String line) {
        System.out.println(line);
    }
    public void SayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scan = new Scanner(System.in);
        while (true) {
            String line = scan.nextLine();
            if (line.equals("bye")) {
                duke.SayBye();
                break;
            }
            duke.GreetEchoExit(line);
        }
    }
}