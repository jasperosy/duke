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
    public void SayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void ListEverything() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println((i + 1) + ". " + arr.get(i));
        }
    }
    public void CompleteTask(int num) {
        arr.get(num).setStatus();
        System.out.println("Nice! I've marked this task as done: \n" + arr.get(num));
    }
    public void listTask(Task line) {
        System.out.println("Got it. I've added this task:");
        System.out.println(line);
        if (arr.size() > 1) {
            System.out.println("Now you have " + arr.size() + " tasks in your list.");
        }
        else {
            System.out.println("Now you have " + arr.size() + " task in the list.");
        }
    }
    public void addToDo(String line) {
        line = line.trim();
        Task task = new Todo(line);
        arr.add(task);
        listTask(task);
    }
    public void addDeadline(String line) {
        String start = line.split("/by")[0].trim();
        String end = line.split("/by")[1].trim();
        Task task = new Deadline(start, end);
        arr.add(task);
        listTask(task);
    }
    public void addEvent(String line) {
        String start = line.split("/at")[0].trim();
        String end = line.split("/at")[1].trim();
        Task task = new Event(start, end);
        arr.add(task);
        listTask(task);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
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
            else if (line.startsWith("done")) {
                int num = Integer.parseInt(line.split(" ")[1]) - 1;
                if (num < 0 || num >= duke.arr.size()) {
                    System.out.println("Invalid number!");
                }
                else {
                    duke.CompleteTask(num);
                }
            }
            else if (line.startsWith("todo")) {
                line = line.replaceFirst("todo", "");
                duke.addToDo(line);
            }
            else if (line.startsWith("deadline")) {
                line = line.replaceFirst("deadline", "");
                duke.addDeadline(line);
            }
            else if (line.startsWith("event")) {
                line = line.replaceFirst("event", "");
                duke.addEvent(line);
            }
        }
    }
}