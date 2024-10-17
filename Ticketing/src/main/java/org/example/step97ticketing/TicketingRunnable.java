package org.example.step97ticketing;

import java.util.Random;

public class TicketingRunnable implements Runnable {
    private Ticket ticket;
    private Random random;

    public TicketingRunnable(Ticket ticket) {
        this.ticket = ticket;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            // 0 또는 1의 랜덤 인덱스 선택
            int ticketIndex = random.nextInt(2); // 0 또는 1 선택
            boolean success = ticket.pickTicket(ticketIndex);
            if (success) {
                System.out.println(Thread.currentThread().getName() + "가 티켓 " + ticketIndex + "을(를) 선택했습니다.");
                break;
            } else {
                if (ticket.allTicketsPicked()) {
                    System.out.println(Thread.currentThread().getName() + "가 대기 중입니다...");
                    try {
                        ticket.cancelTicket(random.nextInt(2));
                        System.out.println(Thread.currentThread().getName() + "가 선택했던 티켓을 취소했습니다");
                        Thread.sleep(1000); // 1초 대기
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // 스레드 상태 복구
                        System.err.println("스레드가 인터럽트되었습니다.");
                    }
                }
            }
        }
    }
}
