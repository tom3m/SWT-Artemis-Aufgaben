public class EnglishAuction extends Auction {
    public EnglishAuction() {
        super();
    }
    
    @Override
    public String generateItemString(Item item) {
        StringBuilder sb = new StringBuilder();
        sb.append(item.toString());
        if (item.getAllBids().isEmpty()) {
            sb.append("\nNo bids placed");
        } else {
            sb.append("\nHighest bid: ").append(item.getHighestBid().toString());
        }
        return sb.toString();
       
    }
}