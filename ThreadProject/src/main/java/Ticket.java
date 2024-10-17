import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ticket {
    private final static int maxCount = 3;
    private static AtomicInteger count = new AtomicInteger(maxCount);
    private static String[] ticketName = {"ticket_1", "ticket_2", "ticket_3"};
    private static boolean[] reserved = new boolean[maxCount];
    private static String[] reservedName = new String[maxCount];


    public static<T> int find(T[] a, T target) {
        return IntStream.range(0, a.length)
                .filter(i -> target.equals(a[i]))
                .findFirst()
                .orElseThrow(() ->new RuntimeException("해당하는 티켓이름이 없습니다."));    // 타겟을 찾지 못하면 -1 반환
    }

    public int findNumber(boolean[] a){
        int count = 0;
        for(int i=0; i<a.length; i++){
            if(!a[i]) count++;
        }
        return count;
    }

    public synchronized String reservation(String name, String user){
        System.out.println(user+"님 " + name +"티켓 예약 시작");
        int ticketIndex = find(ticketName, name);
        if(!reserved[ticketIndex]) {
            reserved[ticketIndex] = true;
            reservedName[ticketIndex] = user;
            count.getAndDecrement();
            if (count.get() != findNumber(reserved)) {
                System.out.println("count.get() = " + count.get());
                System.out.println("findNumber(reserved) = " + findNumber(reserved));
                throw new RuntimeException("예약이 잘못되었습니다.");
            }
            return name + " 티켓이 " + user + "님에게 예약되었습니다. 남은 티켓 수 : " + count.get();
        }
        else {
            return name + "티켓이 이미 예약되었습니다. 남은 티켓 수 : " + count.get();
        }
    }

    public void getPrint() {
        System.out.println("----------------------------------------------------");
        for(int i=0; i<maxCount; i++){
            System.out.println(ticketName[i] + "티켓은 " + (reserved[i]==true?"예약되었습니다 (" + reservedName[i] + ")":"남아있습니다."));
        }
        System.out.println("----------------------------------------------------");

    }
}
