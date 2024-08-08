public class Bid {
    private final long price;
    private Person bidder;

    public Bid(Person bidder, long price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Gebort darf nicht 0 sein.");
        }
        if (bidder == null) {
            throw new NullPointerException("Es muss einen Bieter geben.");
        }
        this.price = price;
        this.bidder = bidder;
    }

    public Person getBidder() {
        return bidder;
    }

    public long getPrice() {
        return price;
    }

    public String toString() {
        return price + " EUR by " + getBidder();  
    }
}