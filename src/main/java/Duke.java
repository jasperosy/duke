import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> arr = new ArrayList<>();
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
    public void EchoAndAdd(Task line) {
        arr.add(line);
        System.out.println("added: " + line.getLine());
    }
    public void ListEverything() {
        for (int i = 0; i < arr.size(); i++) {
            String status = arr.get(i).getStatusIcon();
            String line = arr.get(i).getLine();
            System.out.println((i + 1) + ".[ " + status + " ] " + line);
        }
    }
    public void CompleteTask(int num) {
        arr.get(num).setStatus();
        String status = arr.get(num).getStatusIcon();
        String line = arr.get(num).getLine();
        System.out.println("Nice! I've marked this task as done: \n" + "[ " + status + " ] " + line);
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
                duke.ListEverything();
            }
            else if (line.split(" ")[0].equals("done")) {
                int num = Integer.parseInt(line.split(" ")[1]) - 1;
                if (num < 0 || num >= duke.arr.size()) {
                    System.out.println("Invalid number!");
                }
                else {
                    duke.CompleteTask(num);
                }
            }
            else {
                Task task = new Task(line);
                duke.EchoAndAdd(task);
            }
        }
    }
}