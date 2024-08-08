import java.util.*;

public abstract class Auction {
    private boolean closed = false;
    private List<Item> allItems;


    public Auction(){
        this.allItems = new ArrayList<>();
    }

    public void addBid(Item bidItem, String nameOfBidder, long price){
        if (closed) {
            throw new IllegalStateException("Auktion wurde bereits beendet.");
        }
        if (bidItem == null) {
            throw new NullPointerException("Kein leeres Item kann zur Liste hinzugefügt werden");
        }
        if (nameOfBidder == null) {
            throw new NullPointerException("Name des Bieters darf nicht leer sein");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Gebot darf nicht 0 oder weniger sein");
        }

        if (!allItems.contains(bidItem)) {
            throw new NoSuchElementException("Item ist nicht registriert");
        }

        Person person = new Person(nameOfBidder);
        bidItem.addBid(person, price);


    }

    public String closeAuction() {
        if (closed) {
            throw new IllegalStateException("Auktion ist bereits beendet");
        }
        closed = true;
        return generateItemListString();
    }

    public String generateAllBidsString(Item item) {
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append("All bids:\n");
        List<Bid> allBids = item.getAllBids();
        if (allBids.isEmpty()) {
            return ausgabe.toString();
        }
        for (Bid bid : allBids) {
            ausgabe.append(bid.toString()).append("\n");
        }
        return ausgabe.toString();
    }

    /*public String generateItemListString() {
        StringBuilder ausgabe = new StringBuilder();
        for (Item item : allItems) {
            ausgabe.append(item.getName())
                   .append(": ")
                   .append(item.getDescription())
                   .append(" (minimum bidding price: ")
                   .append(item.getHighestBid())
                   .append(" EUR)\n");

            if (item.getAllBids().isEmpty()) {
                ausgabe.append("No bids placed\n");
            }
        }
        return ausgabe.toString();
    }*/

    /*public String generateItemListString() {
        StringBuilder ausgabe = new StringBuilder();
        for (Item item : allItems) {
            ausgabe.append(item.toString()).append("\n");

            if (item.getAllBids().isEmpty()) {
                ausgabe.append("No bids placed\n");
            } else {
                ausgabe.append(item.getHighestBid().toString()).append("\n").append(generateAllBidsString(item));
            }
        }
        return ausgabe.toString();
    }*/

    public String generateItemListString() {
        StringBuilder ausgabe= new StringBuilder();
        for (Item item : allItems) {
            ausgabe.append(generateItemString(item)).append("\n");
        }
        return ausgabe.toString();
    }


    public void registerItem(Item item){
        if (closed) {
            throw new IllegalStateException("Auktion wurde bereits beendet.");
        }
        if (item == null) {
            throw new NullPointerException("Kein leeres Item kann registriert werden.");
        }
       
        for (Item existingItem : allItems) {
            if (existingItem.getName().equals(item.getName())) {
                throw new IllegalArgumentException("Item mit dem gleichen Namen ist bereits vorhanden.");
            }
        }
        
        allItems.add(item);
    }

    public abstract String generateItemString(Item item);

    public List <Item> getAllItems() {
        return allItems;
    }

}