package dev.fumaz.tpsi;

public class Magazzino {

    public final static int MAX_CAPACITY = 60;
    public final static int PRODUCTION_RATE = 30;

    private final int total;
    private final Object lock;

    private int numGinko706 = 0;
    private int produced = 0;
    private int transported = 0;


    public Magazzino(int total) {
        if (total % 60 != 0) {
            throw new IllegalArgumentException("Il totale deve essere multiplo di 60");
        }

        this.total = total;
        this.lock = new Object();
    }

    public int getTotal() {
        return total;
    }

    public int getNumGinko706() {
        return numGinko706;
    }

    public void setNumGinko706(int numGinko706) {
        this.numGinko706 = numGinko706;
    }

    public int getProduced() {
        return produced;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }

    public int getTransported() {
        return transported;
    }

    public void setTransported(int transported) {
        this.transported = transported;
    }

    public Object getLock() {
        return lock;
    }

    public synchronized void produce() {
        if (numGinko706 >= MAX_CAPACITY) {
            throw new IllegalStateException("Il magazzino è pieno!");
        }

        numGinko706 += PRODUCTION_RATE;
        produced += PRODUCTION_RATE;
    }

    public synchronized void transport() {
        if (numGinko706 < MAX_CAPACITY) {
            throw new IllegalStateException("Il magazzino non é pieno!");
        }

        numGinko706 -= MAX_CAPACITY;
        transported += MAX_CAPACITY;
    }

    public boolean isFull() {
        return numGinko706 >= MAX_CAPACITY;
    }

    public boolean hasProducedAll() {
        return produced >= total;
    }

    public boolean hasTransportedAll() {
        return transported >= total;
    }

}
