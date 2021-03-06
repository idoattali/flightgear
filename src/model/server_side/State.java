package model.server_side;


public class State<T> {

    protected T state;
    private double cost;
    private State cameFrom;


    public State() {
        this.state = null;
        this.cost = 0;
        this.cameFrom = null;
    }


    public State(T state) {
        this.state = state;
    }


    public boolean equals(State state) {
        return state.equals(state.getState());
    }


    public T getState() {
        return state;
    }


    public void setState(T state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State [state=" + state + ", cost=" + cost + ", cameFrom=" + cameFrom + "]";
    }

    public double getCost() {
        if (this.getCameFrom() != null) {
            return this.getCameFrom().getCost() + cost;
        }

        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public State getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(State cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }
}