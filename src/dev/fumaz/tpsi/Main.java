package dev.fumaz.tpsi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il numero totale da produrre: ");

        try {
            int total = scanner.nextInt();
            Magazzino magazzino = new Magazzino(total);

            Thread produttore = new Thread(new Produttore(magazzino), "Produttore");
            Thread trasportatore = new Thread(new Trasportatore(magazzino), "Trasportatore");

            produttore.start();
            trasportatore.start();

            produttore.join();
            trasportatore.join();
        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
            main(args);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
