package proxy.remoteProxy.service;

import proxy.remoteProxy.service.impl.HasQuarterState;
import proxy.remoteProxy.service.impl.NoQuarterState;
import proxy.remoteProxy.service.impl.SoldOutState;
import proxy.remoteProxy.service.impl.SoldState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {
    private State soldState;
    private State soldOutState;
    private State hasQuarterState;
    private State noQuarterState;
    private State winnerState;

    private State state = soldOutState;
    private String location;
    private int count = 0;

    public GumballMachine(String location, int numberGumballs) throws RemoteException{
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        count = numberGumballs;
        this.location = location;
        if (count > 0) {
            state = noQuarterState;
        }

    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.insertQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }


    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count--;
        }
    }

    public void refill(int count) {
        this.count = count;
        state = noQuarterState;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getLocation() {
        return location;
    }
}
