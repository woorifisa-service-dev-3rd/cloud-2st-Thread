package org.example.step97ticketing;

public class Ticket {
    //하나의 티켓들 공유
    private boolean[] tickets = new boolean[2];

    // 선택 (해당 인덱스의 티켓을 선택)
    public synchronized boolean pickTicket(int index) {
        if (index < 0 || index >= tickets.length) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다");
        }

        if (!tickets[index]) {
            tickets[index] = true;
            return true;
        }
        // 이미 선택된 경우 false 반환
        return false;
    }

    // 조회
    public synchronized void checkTickets() {
        for (int i = 0; i < tickets.length; i++) {
            System.out.println(tickets[i]);
        }
    }

    // 모든 티켓이 선택되었는지 확인
    public synchronized boolean allTicketsPicked() {
        for (boolean ticket : tickets) {
            if (!ticket) {
                return false;
            }
        }
        return true;
    }

    // 취소
    public synchronized void cancelTicket(int index) {
        if (index < 0 || index >= tickets.length) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다");
        }

        // tickets[index]의 값이 1인 경우에만 값을 0으로 변경
        if (tickets[index]) {
            tickets[index] = false; // 1을 0으로 변경
        }
    }
}
