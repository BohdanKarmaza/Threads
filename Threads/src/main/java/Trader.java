import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Trader {

    public Trader(String name, List<Shares> target, List<Shares> stockPortfolio) {
        this.name = name;
        this.target = target;
        this.stockPortfolio = stockPortfolio;
    }
    private String name;
    public List<Shares> stockPortfolio;
    public List<Shares> target;



    public void tryToBuy(Trader trader, ArrayList<Shares> target, ArrayList<Shares> shares){
        for (Shares targetShare : target) {
            boolean shareBought = false;
            for (Shares share : shares) {
                if (share.getCompany().equals(targetShare.getCompany())
                        && share.getPrice() <= targetShare.getPrice()
                        && share.getAmount() >= targetShare.getAmount()
                        && !shareBought) {

                    boolean sharesAlreadyInPortfolio = false;
                    for (Shares portfolioShare : trader.getStockPortfolio()) {
                        if (portfolioShare.getCompany().equals(targetShare.getCompany())
                                && portfolioShare.getAmount() == targetShare.getAmount()) {
                            sharesAlreadyInPortfolio = true;
                            break;
                        }
                    }

                    if (!sharesAlreadyInPortfolio) {
                        System.out.println(LocalDateTime.now().withNano(0) + " Спроба покупки акцій " + share.getCompany() + " для " + trader.getName() + " успішна. Куплено " + targetShare.getAmount() + " акцій.");
                        share.setAmount(share.getAmount() - targetShare.getAmount());

                        trader.getStockPortfolio().add(new Shares(targetShare.getCompany(), share.getPrice(), targetShare.getAmount()));
                        shareBought = true;
                    }
                }
            }
            if (!shareBought) {
                System.out.println(LocalDateTime.now().withNano(0) + " Спроба покупки акцій " + targetShare.getCompany() + " для " + trader.getName() + " неуспішна.");
            }
        }
    }


    public List<Shares> getStockPortfolio() {
        return stockPortfolio;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", stockPortfolio=" + stockPortfolio +
                ", target=" + target +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

