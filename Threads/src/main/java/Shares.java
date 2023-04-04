public class Shares {
     public Shares(String company, float price, int amount) {
        this.company = company;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Shares{" +
                "company='" + company + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    private String company;
    public float price;
    private int amount;

    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompany() {
        return this.company;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return this.amount;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return this.price;
    }
    public void changePrice(){
        float percentage = (float)Math.random()*3;
        int a = (int) (Math.random() * 2);
        float change = this.price /100*percentage;
        if (a == 1){
            this.price+=change;
        } else {
            this.price-=change;
    }
}
}

