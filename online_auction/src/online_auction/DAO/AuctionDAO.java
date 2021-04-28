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
	
	public int insertNewAuction(AuctionBean auction) throws SQLException {
		
		String query = "INSERT into auction (auctionId, vendor, startingTime, endingTime, "
				+ "closedFlag, initialPrice, minimumBid) VALUES(?, ?, ?, ?, ?, ?, ?)";
		int code = 0;
		
		try (PreparedStatement pstatement = con.prepareStatement(query);) {
			pstatement.setInt(1, auction.getAuctionId());
			pstatement.setInt(2, auction.getVendor());
			pstatement.setDate(3, auction.getStartingTime());
			pstatement.setDate(4, auction.getEndingTime());
			pstatement.setBoolean(5, auction.getClosedFlag());
			pstatement.setInt(6, auction.getInitialPrice());
			pstatement.setInt(7, auction.getMinimumBid());

			code = pstatement.executeUpdate();
		}
		return code;
		
	}
	
	public List<AuctionBean> getOpenAuctions(UserBean user) throws SQLException {
		
		String query = "SELECT * FROM auction WHERE closedFlag = 0 AND vendor = ?";
		List <AuctionBean> openAuctions = new ArrayList<AuctionBean>();
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
	
	public List<AuctionBean> getClosedAuctions(UserBean user) throws SQLException {
		
		String query = "SELECT * FROM auction WHERE closedFlag = 1 AND vendor = ?";
		List <AuctionBean> closedAuctions = new ArrayList<AuctionBean>();
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
				
				closedAuctions.add(auction);				
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
		return closedAuctions;

	}
	
	public void closeAuction (int auctionId) throws SQLException {
		con.setAutoCommit(false);
		
		String query1 = "UPDATE auction SET closedFlag = 1 WHERE auctionId = ?";
		PreparedStatement pstatement1 = null;

		String query2 = "UPDATE offer SET winningFlag = 1 "
				+ "WHERE auction = ? "
				+ "AND amount = (SELECT max(amount) FROM offer where auction = ?)";
		PreparedStatement pstatement2 = null;
		try {
			pstatement1 = con.prepareStatement(query1);
			pstatement1.setInt(1, auctionId);
			pstatement1.executeUpdate();
			
			pstatement2 = con.prepareStatement(query2);
			pstatement2.setInt(1, auctionId);
			pstatement2.setInt(2, auctionId);
			pstatement2.executeUpdate();
			
			con.commit();
			
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			con.setAutoCommit(true);
			if (pstatement1 != null) {
				try {
					pstatement1.close();
				} catch (Exception e) {
					throw e;
				}
			}
			if (pstatement2 != null) {
				try {
					pstatement2.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}

	}
	
	
}
