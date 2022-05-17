package dev.fumaz.tpsi;

public class Produttore implements Runnable {

    private final Magazzino magazzino;

    public Produttore(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        try {
            while (!magazzino.hasProducedAll()) {
                synchronized (magazzino.getLock()) {
                    while (magazzino.isFull()) {
                        System.out.println("Produttore - n motocoltivtori presenti = " + magazzino.getNumGinko706() + ". Produzione momentaneamente ferma...");
                        magazzino.getLock().wait();
                    }

                    Thread.sleep(1000);

                    int old = magazzino.getNumGinko706();
                    magazzino.produce();

                    System.out.println("Produttore - n motocoltivatori presenti = " + old + ", n motocoltivatori prodotte = " + Magazzino.PRODUCTION_RATE + ", " +
                            "n tot prodotte " + magazzino.getProduced() + " di " + magazzino.getTotal() + " n motocoltivatori aggiornato = " + magazzino.getNumGinko706());

                    magazzino.getLock().notifyAll();
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Produttore interrotto " + ex.getMessage());
        }
    }

}
