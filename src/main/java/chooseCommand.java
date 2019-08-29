public class chooseCommand {

    static Command choose (String line) throws DukeException {
        if (line.equals("bye")) {
            return new exitCommand();
        }
        else if (line.equals("list")) {
            return new ListCommand();
        }
        else if (line.startsWith("done")) {
            int num = Integer.parseInt(line.split(" ")[1]) - 1;
            return new completeCommand(num);
        }
        else if (line.startsWith("todo")) {
            line = line.replaceFirst("todo", "");
            return new AddToDoCommand(line);
        }
        else if (line.startsWith("deadline")) {
            line = line.replaceFirst("deadline", "");
            return new addDeadlineCommand(line);
        }
        else if (line.startsWith("event")) {
            line = line.replaceFirst("event", "");
            return new addEventCommand(line);
        }
        else if (line.startsWith("delete")) {
            int num = Integer.parseInt(line.split(" ")[1]) - 1;
            return new deleteCommand(num);
        }
        else if (line.startsWith("find")) {
            return new findCommand(line);
        }
        else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
