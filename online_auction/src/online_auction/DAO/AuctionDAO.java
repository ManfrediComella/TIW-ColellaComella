package online_auction.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online_auction.beans.AuctionBean;
import online_auction.beans.UserBean;

public class AuctionDAO {
	private Connection con;
	
	public AuctionDAO(Connection connection) {
		this.con = connection;
	}
	
	public List<AuctionBean> getOpenAuctions(UserBean user) throws SQLException {
		
		List <AuctionBean> openAuctions = new ArrayList<AuctionBean>();
		String query = "SELECT * FROM auction WHERE closedFlag = 0 AND vendor = ?";
		ResultSet result = null;
		PreparedStatement pstatement = null;
		
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setInt(1, user.getUserId());
			result = pstatement.executeQuery();
			
			while (result.next()) {
				AuctionBean auction = new AuctionBean();
				
				auction.setAuctionId(result.getInt("auctionId"));
				auction.setVendor(result.getInt("vendor"));
				auction.setStartingTime(result.getDate("startingTime"));
				auction.setEndingTime(result.getDate("endingTime"));
				auction.setClosedFlag(result.getBoolean("closedFlag"));
				auction.setInitialPrice(result.getInt("initialPrice"));
				auction.setMinimumBid(result.getInt("minimumBid"));
				
				openAuctions.add(auction);				
			}
		} catch (SQLException e) {
				throw new SQLException (e);
		} finally {
			try {
				if (result != null)
					result.close();
			} catch (Exception e1) {
				throw new SQLException(e1);
			}
			try {
				if (pstatement != null)
					pstatement.close();
			} catch (Exception e2) {
			throw new SQLException(e2);
		}
		}
		return openAuctions;
		
		}
}
