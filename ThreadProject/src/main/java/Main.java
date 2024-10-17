import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        String[][] str ={{"ticket_1", "신원섭1"},{"ticket_22", "신원섭4"},{"ticket_1","신원섭2"},{"ticket_2","신원섭3"}};
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for(int i=0; i<str.length; i++){
            int finalI = i;
            Future<String> future = threadPool.submit(() -> ticket.reservation(str[finalI][0],str[finalI][1]));
            try {
                future.get();
            } catch (ExecutionException e) {
                System.out.println("해당하는 티켓이 없어 예약이 취소되었습니다.");
            } finally {
                threadPool.shutdown();

            }
        }



//        Thread t1 = new Thread(() ->ticket.reservation("ticket_1","신원섭1"));
//        Thread t4 = new Thread(() ->ticket.reservation("ticket_22","신원섭4"));
//        Thread t2 = new Thread(() ->ticket.reservation("ticket_1","신원섭2"));
//        Thread t3 = new Thread(() ->ticket.reservation("ticket_2","신원섭3"));
//
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//
//        t1.join();
//        t2.join();
//        t3.join();
//        t4.join();

        ticket.getPrint();
    }
}
