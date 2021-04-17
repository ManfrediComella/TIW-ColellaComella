package online_auction.beans;

import java.sql.Date;

public class Offer {
	
	private int offerId;
	
	private int bidder;
	
	private int auction;
	
	private int amount;
	
	private Date datetime;

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	

}
