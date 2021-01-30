package edu.kis.powp.jobs2d.command.manager;
// Class representation of Command needed to use with Gson
public class Command {
    String operation;
    int x;
    int y;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Command() {
    }

    public Command(String operation, int x, int y) {
        this.operation = operation;
        this.x = x;
        this.y = y;
    }
}
