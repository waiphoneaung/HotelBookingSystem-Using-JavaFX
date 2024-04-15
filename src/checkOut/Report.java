package checkOut;

public class Report {
	private int bookingID;
	private int roomID;
	private String name;
	private String nrc;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String CheckInDate;
	private String CheckOutDate;
	public Report(int bookingID, int roomID, String name, String nrc, String phone, String email, String address,
			String city, String checkInDate, String checkOutDate) {
		super();
		this.bookingID = bookingID;
		this.roomID = roomID;
		this.name = name;
		this.nrc = nrc;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		CheckInDate = checkInDate;
		CheckOutDate = checkOutDate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	@Override
	public String toString() {
		return "Report [bookingID=" + bookingID + ", roomID=" + roomID + ", name=" + name + ", nrc=" + nrc + ", phone="
				+ phone + ", email=" + email + ", address=" + address + ", city=" + city + ", CheckInDate="
				+ CheckInDate + ", CheckOutDate=" + CheckOutDate + "]";
	}
}
