package online_auction.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import online_auction.beans.*;

public class OfferDAO {

	private Connection con;

	public OfferDAO(Connection con) {
		super();
		this.con = con;
	}

	public List<Offer> getOffers(int auction) throws SQLException {

		List<Offer> offers = new ArrayList<Offer>();
		Offer offer = null;
		String query = "SELECT * FROM offer WHERE offer.auction = ?";

		try (PreparedStatement pstatement = con.prepareStatement(query);) {
			pstatement.setInt(1, auction);

			try (ResultSet result = pstatement.executeQuery();) {
				while (result.next()) {
					offer = new Offer();
					offer.setOfferId(result.getInt("offerId"));
					offer.setAmount(result.getInt("amount"));
					offer.setAuction(result.getInt("auction"));
					offer.setBidder(result.getInt("bidder"));
					offer.setDatetime(result.getDate("datetime"));
					offers.add(offer);
				}
			}
		}

		if (!offers.isEmpty())
			return offers;
		else
			return null;
	}

	public int insertOffer(Offer offer) throws SQLException{
		String query = "INSERT into offer (offerId, amount, auction, bidder, datetime)   VALUES(?, ?, ?, ?, ?)";
		int code = 0;
		PreparedStatement pstatement = null;
		
		try {
			pstatement = con.prepareStatement(query);

			pstatement.setInt(1, offer.getOfferId());
			pstatement.setInt(2, offer.getAmount());
			pstatement.setInt(3, offer.getAuction());
			pstatement.setInt(4, offer.getBidder());
			pstatement.setDate(5, offer.getDatetime());
			
			code = pstatement.executeUpdate();
			
		}catch(SQLException e) {
			throw new SQLException(e);
		}finally {
			try {
				pstatement.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		return code;
	}

	
	public List<Offer> getWinningOffers(int userId) throws SQLException {

		Offer offer = null;
		List<Offer> offers = new ArrayList();
		String query = "SELECT * FROM offer WHERE bidder = ? AND winningOffer != 0";

		try (PreparedStatement pstatement = con.prepareStatement(query);) {
			pstatement.setInt(1, userId);

			try (ResultSet result = pstatement.executeQuery();) {
				while (result.next()) {
					offer = new Offer();
					offer.setOfferId(result.getInt("offerId"));
					offer.setAmount(result.getInt("amount"));
					offer.setAuction(result.getInt("auction"));
					offer.setBidder(result.getInt("bidder"));
					offer.setDatetime(result.getDate("datetime"));
					offers.add(offer);
				}
			}
		}
		
		if(offers.isEmpty()) 
			return null;
		else 
			return offers;
	}
	
	

	public Offer getAuctionWinningOffers(int auctionId) throws SQLException {

		Offer offer = null;
		List<Offer> offers = new ArrayList();
		int maximumBid = 0;
		String query = "SELECT MAX(amount) FROM offer WHERE auction = ?";

		try (PreparedStatement pstatement = con.prepareStatement(query);) {
			pstatement.setInt(1, auctionId);
			try (ResultSet result = pstatement.executeQuery();) {
				maximumBid = result.getInt("amount");
				
				//check if a tuple was actually found
				if(maximumBid == 0) return null;
			}
		}

		String query2 = "SELECT * FROM offer where auction = ? and amount = ?";
		try (PreparedStatement pstatement = con.prepareStatement(query);) {
			pstatement.setInt(1, auctionId);
			pstatement.setInt(2, maximumBid);
			try (ResultSet result = pstatement.executeQuery();) {
				while (result.next()) {
					offer = new Offer();
					offer.setOfferId(result.getInt("offerId"));
					offer.setAmount(result.getInt("amount"));
					offer.setAuction(result.getInt("auction"));
					offer.setBidder(result.getInt("bidder"));
					offer.setDatetime(result.getDate("datetime"));
					offers.add(offer);
				}
			}
		}
		
		if (offers.size() == 1)
			return offers.get(0);
		else
			return null;
	}

}
