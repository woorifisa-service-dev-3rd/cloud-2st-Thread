package org.example.step97ticketing;

public class Ticketing {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TicketingRunnable(ticket));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
