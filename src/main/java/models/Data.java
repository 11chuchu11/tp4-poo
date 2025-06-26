package models;

public abstract class Data {
    protected int id;

    protected Data(int id) {
        this.id = id;
    }

    public abstract int getID();

    public abstract void setID(int id);
}
