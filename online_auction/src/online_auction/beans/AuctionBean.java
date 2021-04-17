package online_auction.beans;

import java.sql.Date;

public class AuctionBean {
	
	private int auctionId;
	private int vendor;
	private Date startingTime;
	private Date endingTime;
	private Boolean closedFlag;
	private int initialPrice;
	private int minimumBid;
	
	//auctionId getter and setter
	public int getAuctionId() {
		return auctionId;
	}
	
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	//vendor getter and setter
	public int getVendor() {
		return vendor;
	}
	
	public void setVendor(int vendor) {
		this.vendor = vendor;
	}
	
	//startingTime getter and setter
	public Date getStartingTime() {
		return startingTime;
	}
	
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}
	
	//edingTime getter and setter
	public Date getEndingTime() {
		return endingTime;
	}
	
	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}
	
	//closedFlag getter and setter
	public Boolean getClosedFlag() {
		return closedFlag;
	}
	
	public void setClosedFlag(Boolean closedFlag) {
		this.closedFlag = closedFlag;
	}
	
	//initialPrice getter and setter
	public int getInitialPrice() {
		return initialPrice;
	}
	
	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}
	
	//minimumBid getter and setter
	public int getMinimumBid() {
		return minimumBid;
	}
	
	public void setMinimumBid(int minimumBid) {
		this.minimumBid = minimumBid;
	}
}
