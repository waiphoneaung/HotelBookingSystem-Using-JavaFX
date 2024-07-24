package checkOut;

public class CheckOut {
	private int bookingID;
	private int roomID;
	private String name;
	private String nrc;
	private String phone;
	private String email;
	private String CheckInDate;
	private String CheckOutDate;
	private String numberOfDay;
	private double pricePerNight;
	private String roomCapacity;
	private double totalPrice;
	public CheckOut(int bookingID, int roomID, String name, String nrc, String phone, String email, String checkInDate,
			String checkOutDate, String numberOfDay, double pricePerNight, String roomCapacity, double totalPrice) {
		super();
		this.bookingID = bookingID;
		this.roomID = roomID;
		this.name = name;
		this.nrc = nrc;
		this.phone = phone;
		this.email = email;
		CheckInDate = checkInDate;
		CheckOutDate = checkOutDate;
		this.numberOfDay = numberOfDay;
		this.pricePerNight = pricePerNight;
		this.roomCapacity = roomCapacity;
		this.totalPrice = totalPrice;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getNumberOfDay() {
		return numberOfDay;
	}
	public void setNumberOfDay(String numberOfDay) {
		this.numberOfDay = numberOfDay;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	public String getRoomCapacity() {
		return roomCapacity;
	}
	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "CheckOut [bookingID=" + bookingID + ", roomID=" + roomID + ", name=" + name + ", nrc=" + nrc
				+ ", phone=" + phone + ", email=" + email + ", CheckInDate=" + CheckInDate + ", CheckOutDate="
				+ CheckOutDate + ", numberOfDay=" + numberOfDay + ", pricePerNight=" + pricePerNight + ", roomCapacity="
				+ roomCapacity + ", totalPrice=" + totalPrice + "]";
	}
}