public class AllPayAuction extends Auction {
    public AllPayAuction() {
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
            sb.append("\nAll bids:");
            for (Bid bid : item.getAllBids()) {
                sb.append("\n").append(bid.toString());
            }
        }
        return sb.toString();
    }
}