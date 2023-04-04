import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Threads {
    static long stopTime = System.currentTimeMillis() + 600000;
    public static final Object LOCK = new Object();
    public static void main(String[] args) {
        Shares shares1 = new Shares("AAPL", 141, 100);
        Shares shares2 = new Shares("COKE", 387, 1000);
        Shares shares3 = new Shares("IBM", 137, 200);

        List<Shares> shares = new ArrayList<>();
        shares.add(shares1);
        shares.add(shares2);
        shares.add(shares3);

        List<Shares> AliceTargets = new ArrayList<>();
        AliceTargets.add(new Shares("AAPL", 100, 10));
        AliceTargets.add(new Shares("COKE", 390, 20));

        List<Shares> AliceStockPortfolio = new ArrayList<>();

        Trader Alice = new Trader("Alice", AliceTargets, AliceStockPortfolio);

        List<Shares> BobTargets = new ArrayList<>();
        BobTargets.add(new Shares("AAPL", 140, 10));
        BobTargets.add(new Shares("IBM", 135, 20));

        List<Shares> BobStockPortfolio = new ArrayList<>();

        Trader Bob = new Trader("Bob", BobTargets, BobStockPortfolio);

        List<Shares> CharlieTargets = new ArrayList<>();
        CharlieTargets.add(new Shares("COKE", 370, 300));

        List<Shares> CharlieStockPortfolio = new ArrayList<>();

        Trader Charlie = new Trader("Charlie", CharlieTargets, CharlieStockPortfolio);

        new Thread(()->{
            synchronized (LOCK){
                try {
                    while (System.currentTimeMillis() < stopTime) {
                        shares1.changePrice();
                        System.out.printf(LocalDateTime.now().withNano(0) + " Ціна акцій компанії " + shares1.getCompany() + " змінилась. Поточна вартість %.2f\n", shares1.getPrice());
                        LOCK.wait(30000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{
            synchronized (LOCK){
                try {
                    LOCK.wait(10000);
                    while (System.currentTimeMillis() < stopTime) {
                        shares2.changePrice();
                        System.out.printf(LocalDateTime.now().withNano(0) + " Ціна акцій компанії " + shares2.getCompany() + " змінилась. Поточна вартість %.2f\n", shares2.getPrice());
                        LOCK.wait(30000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{
            synchronized (LOCK){
                try {
                    LOCK.wait(20000);
                    while (System.currentTimeMillis() < stopTime) {
                        shares3.changePrice();
                        System.out.printf(LocalDateTime.now().withNano(0) + " Ціна акцій компанії " + shares3.getCompany() + " змінилась. Поточна вартість %.2f\n", shares3.getPrice());
                        LOCK.wait(30000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{
            synchronized (LOCK){
                try{
                    LOCK.wait(1700);
                    while (System.currentTimeMillis() < stopTime) {
                        Alice.tryToBuy(Alice, (ArrayList<Shares>) AliceTargets, (ArrayList<Shares>) shares);
                        LOCK.wait(5000);
                    }

                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }

        ).start();

        new Thread(()->{
            synchronized (LOCK){
                try{
                    LOCK.wait(3350);
                    while (System.currentTimeMillis() < stopTime) {
                        Bob.tryToBuy(Bob, (ArrayList<Shares>) BobTargets, (ArrayList<Shares>) shares);
                        LOCK.wait(5000);
                    }

                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }

        ).start();

        new Thread(()->{
            synchronized (LOCK){
                try{
                    while (System.currentTimeMillis() < stopTime) {
                        Charlie.tryToBuy(Charlie, (ArrayList<Shares>) CharlieTargets, (ArrayList<Shares>) shares);
                        LOCK.wait(5000);
                    }

                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }

        ).start();

        new Thread(()->{
            synchronized (LOCK) {
                try {
                    LOCK.wait(601000);
                    System.out.println(LocalDateTime.now().withNano(0) + " Фінальна статистика:");
                    System.out.println(shares1);
                    System.out.println(shares2);
                    System.out.println(shares3);
                    System.out.println(Alice);
                    System.out.println(Bob);
                    System.out.println(Charlie);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                }
            }
        ).start();

    }
}
