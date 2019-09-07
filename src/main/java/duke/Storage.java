package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;

public class Storage {

    public void writeToFile(TaskList tasklist) throws DukeException {
        try {
            ArrayList<Task> arr = tasklist.getTasks();
            String filename = "output.txt";
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
    protected ArrayList<Task> arr = new ArrayList<Task>();
    public ArrayList<Task> readFromFile() throws DukeException {
        try {
            String filename = "output.txt";
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
            return arr;
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
            System.out.println("Look at the output.txt for any irregularities!");
        }
        return arr;
    }
    public boolean checkDone(String line) {
        if (line.charAt(4) == '\u2713') {
            return true;
        }
        return false;
    }
}
