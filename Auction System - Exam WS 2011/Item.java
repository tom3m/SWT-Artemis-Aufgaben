import java.util.*;

public class Item {
    private String name;
    private String description;
    private long minPrice;
    private List<Bid> allBids;

    public Item(String name, String description, long minPrice) {
        if (minPrice <= 0) {
            throw new IllegalArgumentException("Preis muss größer als 0 sein");
        }
        if (name == null) {
            throw new NullPointerException("Name des Items muss vorhanden sein");
        }
        if (name == "") {
            throw new IllegalArgumentException("Name des Items muss vorhanden sein");
        }
        if (description == null) {
            throw new NullPointerException("Name des Items muss vorhanden sein");
        }
        if (description == "") {
            throw new IllegalArgumentException("Name des Items muss vorhanden sein");
        }
        this.name = name;
        this.description = description;
        this.minPrice = minPrice;
        this.allBids = new ArrayList<>();
    }

    public void addBid(Person bidder, long price) {
        if (bidder == null) {
            throw new NullPointerException("Der Bieter darf nicht null sein.");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Das Gebot darf nich kleiner gleich 0 sein.");
        }

        if (description == null) {
            throw new NullPointerException("Argument muss vorhanden sein.");
        }

        if (description == "") {
            throw new IllegalArgumentException("Argument muss vorhanden sein.");
        }

        
       /*  if (!allBids.isEmpty()) {
            if (price <= getHighestBid().getPrice()) {
            throw new IllegalArgumentException("Gebot muss über dem Mindestgebot sein");
            }
        }*/
        Bid highestBid = getHighestBid();
        if (highestBid != null && price <= highestBid.getPrice()) {
            return;
        }
        
        if (price >= minPrice) {
            Bid bid = new Bid(bidder, price);
            allBids.add(bid);
        }
        /*Bid bid = new Bid(bidder, price);
        allBids.add(bid);*/
    }

    public List<Bid> getAllBids() {
        return allBids;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        if (allBids.isEmpty()) {
            return null;
        }
        Bid highestBid = allBids.get(0);

        for (Bid bid : allBids) {
            if (bid.getPrice() > highestBid.getPrice()) {
                highestBid = bid;
            }
        }
        return highestBid;
    }

    public String toString() {
        return name + ": " + description + " (minimum bidding price: " + minPrice + " EUR)";
    }

}