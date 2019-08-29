import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }
    public void execute(TaskList arr, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println("\t" + (i + 1) + ". " + arr.getTask(i));
        }
        ui.showLine();
    }
    public boolean isExit() {
        return false;
    }
}
