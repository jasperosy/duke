public class Task {
    protected String line;
    protected boolean status;
    public Task(String line) {
        this.line = line;
        this.status = false;
    }
    public void setStatus() {
        this.status = true;
    }
    public String getStatusIcon() {
        return (status ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + line;
    }
}
