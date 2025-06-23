package models;

public abstract class Data {
    protected int id;

    public Data(int id) {
        this.id = id;
    }

    public abstract void setID(int id);
    public abstract int getID();
}
