import java.nio.Buffer;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

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
        writeToFile("output.txt");
        System.out.println("Nice! I've marked this task as done: \n" + arr.get(num));
    }
    public void listTask(Task line) {
        System.out.println("Got it. I've added this task:");
        System.out.println(line);
        writeToFile("output.txt");
        if (arr.size() > 1) {
            System.out.println("Now you have " + arr.size() + " tasks in your list.");
        }
        else {
            System.out.println("Now you have " + arr.size() + " task in the list.");
        }
    }
    public void addToDo(String line) throws DukeException {
        line = line.trim();
        if (line.length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new Todo(line);
        arr.add(task);
        listTask(task);
    }
    public void addDeadline(String line) throws DukeException {
        String linesplit[] = line.split("/by");
        if (linesplit.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline needs a due date.");
        }
        String start = linesplit[0].trim();
        String end = linesplit[1].trim();
        Task task = new Deadline(start, end);
        arr.add(task);
        listTask(task);
    }
    public void addEvent(String line) throws DukeException {
        String linesplit[] = line.split("/at");
        if (linesplit.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of an event needs a date.");
        }
        String start = linesplit[0].trim();
        String end = linesplit[1].trim();
        if (end.length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new Event(start, end);
        arr.add(task);
        listTask(task);
    }
    public void writeToFile(String filename) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < arr.size(); i++) {
                out.write(arr.get(i) + "\n");
            }
            out.close();
        }
        catch (IOException e) {
            System.out.println(e + ", thus please try inputting other things.");
        }
    }
    public void readFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter += 1;
                if (line.startsWith("[T]")) {
                    boolean checker = checkDone(line);
                    line = line.substring(7).trim();
                    Task task = new Todo(line);
                    arr.add(task);
                    if (checker) {
                        arr.get(counter - 1).setStatus();
                    }
                }
                else if (line.startsWith("[D]")) {
                    boolean checker = checkDone(line);
                    line = line.substring(7).trim();
                    String linesplit[] = line.split("\\(by:");
                    String start = linesplit[0].trim();
                    String end = linesplit[1].trim();
                    end = end.substring(0, end.length() - 1);
                    Task task = new Deadline(start, end);
                    arr.add(task);
                    if (checker) {
                        arr.get(counter - 1).setStatus();
                    }
                }
                else if (line.startsWith("[E]")) {
                    boolean checker = checkDone(line);
                    line = line.substring(7).trim();
                    String linesplit[] = line.split("\\(at:");
                    String start = linesplit[0].trim();
                    String end = linesplit[1].trim();
                    end = end.substring(0, end.length() - 1);
                    Task task = new Event(start, end);
                    arr.add(task);
                    if (checker) {
                        arr.get(counter - 1).setStatus();
                    }
                }
            }
            reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
            System.out.println("Look at the output.txt for any irregularities!");
        }
    }
    public boolean checkDone(String line) {
        if (line.charAt(4) == '\u2713') {
            return true;
        }
        return false;
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
        File file = new File("output.txt");
        if (file.exists()) {
            duke.readFromFile("output.txt");
        }
        while (true) {
            try {
                String line = scan.nextLine();
                if (line.equals("bye")) {
                    duke.SayBye();
                    break;
                } else if (line.equals("list")) {
                    duke.ListEverything();
                } else if (line.startsWith("done")) {
                    int num = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (num < 0 || num >= duke.arr.size()) {
                        System.out.println("Invalid number!");
                    } else {
                        duke.CompleteTask(num);
                    }
                } else if (line.startsWith("todo")) {
                    line = line.replaceFirst("todo", "");
                    duke.addToDo(line);
                } else if (line.startsWith("deadline")) {
                    line = line.replaceFirst("deadline", "");
                    duke.addDeadline(line);
                } else if (line.startsWith("event")) {
                    line = line.replaceFirst("event", "");
                    duke.addEvent(line);
                }
                else {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException exception) {
                String message = exception.getMessage();
                System.out.println("\t " + message);
                System.out.println("Please enter another task!");
            }
        }
    }
}