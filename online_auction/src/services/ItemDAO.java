package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import online_auction.beans.*;

public class ItemDAO {

	private Connection con;

	public ItemDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public int insertItem(Item item) throws SQLException{
		
		String query = "INSERT into item (itemId, name, owner, auction, image, description)   VALUES(?, ?, ?, ?, ?, ?)";
		int code = 0;
		PreparedStatement pstatement = null;
		
		try {
			pstatement = con.prepareStatement(query);

			pstatement.setInt(1, item.getItemId());
			pstatement.setString(2, item.getName());
			pstatement.setInt(3, item.getOwner());
			pstatement.setInt(4, item.getAuction());
			pstatement.setBlob(5, item.getImage());  //To be checked
			pstatement.setString(6, item.getDescription());
			
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
	
	public List<Item> getItems(int ownerId) throws SQLException{
		
		List<Item> items = new ArrayList<Item>();
		Item item = null;
		String query = "SELECT * FROM item WHERE owner = ?";

		try (PreparedStatement pstatement = con.prepareStatement(query);) {
			pstatement.setInt(1, ownerId);

			try (ResultSet result = pstatement.executeQuery();) {
				while (result.next()) {
					item = new Item();
					item.setItemId(result.getInt("offerId"));
					item.setAuction(result.getInt("auction"));
					item.setDescription(result.getString("description"));
					item.setImage(result.getBlob("image"));
					item.setOwner(result.getInt("owner"));
					item.setName(result.getString("name"));
					
					items.add(item);
				}
			}
		}

		if (!items.isEmpty())
			return items;
		else
			return null;
		
	}

	
}
