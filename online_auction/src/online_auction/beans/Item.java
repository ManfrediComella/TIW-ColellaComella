package online_auction.beans;

import java.sql.Blob;

public class Item {
	
	private int itemId;
	
	private String name;
	
	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getAuction() {
		return auction;
	}

	public void setAuction(int auction) {
		this.auction = auction;
	}

	private int owner;
	
	private int auction;
	
	private Blob image;
	
	private String description;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	


}
