package model.interpreter;

import java.util.Observable;
import java.util.Observer;

public class Var extends Observable implements Observer {

    double value;
    String name;
    String Loc;


    public Var() {
    }

    // CTOR.
    public Var(String loc) {
        super();
        Loc = loc;
    }


    public Var(double v) {
        this.value = v;
        this.Loc = null;
    }

    @Override
    public void update(Observable o, Object arg) {
        Double d = new Double(0);
        if (arg.getClass() == (d.getClass())) {
            if (this.value != (double) arg) {
                this.setV((double) arg);
                this.setChanged();
                this.notifyObservers(arg + "");
            }
        }
    }

    @Override
    public String toString() {
        return this.Loc;
    }

    public double getV() {
        return value;
    }

    public void setV(double value) {
        if (this.value != value) {
            this.value = value;
            setChanged();
            notifyObservers(value);
        }
    }

    public String getLoc() {
        return Loc;
    }
}