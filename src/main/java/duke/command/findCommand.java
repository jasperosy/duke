package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class findCommand extends Command {

    String line;

    public findCommand(String line) {
        super();
        this.line = line;
    }
    public void execute(TaskList arr, Ui ui, Storage storage) throws DukeException {
        String linesplit[] = line.split(" ");
        if (linesplit.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a find cannot be empty.");
        }
        String item = linesplit[1].trim();
        ArrayList<Task> foundarr = new ArrayList<>();
        for (int i = 0; i < arr.getSize(); i++) {
            if (arr.getTask(i).getLine().contains(item)) {
                foundarr.add(arr.getTask(i));
            }
        }
        if (foundarr.size() == 0) {
            System.out.println("\tThere are no matching tasks in your list!");
        }
        else {
            ui.showLine();
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < foundarr.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + foundarr.get(i));
            }
            ui.showLine();
        }
    }
    public boolean isExit() {
        return false;
    }
}
