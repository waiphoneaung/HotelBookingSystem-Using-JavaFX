package checkOut;

public class CheckOut {
	private int bookingID;
	private int roomID;
	private String name;
	private String nrc;
	private String CheckInDate;
	private String CheckOutDate;
	
	private double pricePerNight;
	public CheckOut(int bookingID, int roomID, String name, String nrc, String checkInDate, String checkOutDate,
			double pricePerNight) {
		super();
		this.bookingID = bookingID;
		this.roomID = roomID;
		this.name = name;
		this.nrc = nrc;
		CheckInDate = checkInDate;
		CheckOutDate = checkOutDate;
		this.pricePerNight = pricePerNight;
	}
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public String getCheckInDate() {
		return CheckInDate;
	}
	public void setCheckInDate(String checkInDate) {
		CheckInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return CheckOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		CheckOutDate = checkOutDate;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	@Override
	public String toString() {
		return "CheckOut [bookingID=" + bookingID + ", roomID=" + roomID + ", name=" + name + ", nrc=" + nrc
				+ ", CheckInDate=" + CheckInDate + ", CheckOutDate=" + CheckOutDate + ", pricePerNight=" + pricePerNight
				+ "]";
	}

	
}

