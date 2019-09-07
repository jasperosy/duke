package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }
    public TaskList() {
        this.arr = new ArrayList<Task>();
    }
    public ArrayList<Task> getTasks() {
        return arr;
    }
    public int getSize() {
        return arr.size();
    }
    public Task getTask(int index) {
        return arr.get(index);
    }
    public void addTask(Task task) {
        arr.add(task);
    }
    public void deleteTask(int index) {
        arr.remove(index);
    }
}
