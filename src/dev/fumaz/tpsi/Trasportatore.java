package dev.fumaz.tpsi;

public class Trasportatore implements Runnable{

    private final Magazzino magazzino;

    public Trasportatore(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        try {
            while (!magazzino.hasTransportedAll()) {
                synchronized (magazzino.getLock()) {
                    while (!magazzino.isFull()) {
                        System.out.println("Trasportatore - n motocoltivatori presenti = " + magazzino.getNumGinko706() + ". Trasportatore in attesa della merce...");
                        magazzino.getLock().wait();
                    }

                    Thread.sleep(1000);

                    int old = magazzino.getNumGinko706();
                    magazzino.transport();

                    System.out.println("Trasportatore - n motocoltivatori presenti = " + old + ", n motocoltivatori caricate = " + Magazzino.MAX_CAPACITY + ", n tot trasportate " +
                            magazzino.getTransported() + " di " + magazzino.getTotal() + ", n motocoltivatori aggiornato = " + magazzino.getNumGinko706());

                    magazzino.getLock().notifyAll();
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Trasportatore interrotto " + ex.getMessage());
        }
    }

}
