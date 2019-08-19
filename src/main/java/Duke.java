import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<String> arr = new ArrayList<>();
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
    public void EchoAndAdd(String line, ArrayList<String> arr) {
        System.out.println("added: " + line);
        arr.add(line);
    }
    public void ListEverything(ArrayList<String> arr) {
        int counter = 0;
        for (String i : arr) {
            counter += 1;
            System.out.println(counter + ". " + i);
        }
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
            else if (line.equals("list")) {
                duke.ListEverything(duke.arr);
            }
            else {
                duke.EchoAndAdd(line, duke.arr);
            }
        }
    }
}