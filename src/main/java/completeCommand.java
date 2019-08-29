public class completeCommand extends Command {

    int index;

    public completeCommand(int index) {
        super();
        this.index = index;
    }
    public void execute(TaskList arr, Ui ui, Storage storage) throws DukeException {
        if (index >= arr.getSize() || index < 0) {
            throw new DukeException("\u2639 OOPS!!! Invalid number!");
        }
        else {
            Task task = arr.getTask(index);
            task.setStatus();
            ui.completeMessage(task);
        }
    }
    public boolean isExit() {
        return false;
    }
}
