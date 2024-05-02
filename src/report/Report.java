package report;
import java.time.LocalDate;
public class Report {
	private int guestID;
	private int roomID;
	private String bookingID, name, nrc, phone, city;
	private LocalDate ckinDate, ckoutDate;
	private String roomtype, roomCapacity;
	private int NumberofDay;
	private double totalprice;
	
	public Report(int guestID, int roomID, String bookingID, String name, String nrc, String phone, String city,
			LocalDate ckinDate, LocalDate ckoutDate, String roomtype, String roomCapacity, int numberofDay,
			double totalprice) {
		super();
		this.guestID = guestID;
		this.roomID = roomID;
		this.bookingID = bookingID;
		this.name = name;
		this.nrc = nrc;
		this.phone = phone;
		this.city = city;
		this.ckinDate = ckinDate;
		this.ckoutDate = ckoutDate;
		this.roomtype = roomtype;
		this.roomCapacity = roomCapacity;
		NumberofDay = numberofDay;
		this.totalprice = totalprice;
		
		
	}

	public int getGuestID() {
		return guestID;
	}

	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getBookingID() {
		return bookingID;
	}

	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getCkinDate() {
		return ckinDate;
	}

	public void setCkinDate(LocalDate ckinDate) {
		this.ckinDate = ckinDate;
	}

	public LocalDate getCkoutDate() {
		return ckoutDate;
	}

	public void setCkoutDate(LocalDate ckoutDate) {
		this.ckoutDate = ckoutDate;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public int getNumberofDay() {
		return NumberofDay;
	}

	public void setNumberofDay(int numberofDay) {
		NumberofDay = numberofDay;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	@Override
	public String toString() {
		return "Report [guestID=" + guestID + ", roomID=" + roomID + ", bookingID=" + bookingID + ", name=" + name
				+ ", nrc=" + nrc + ", phone=" + phone + ", city=" + city + ", ckinDate=" + ckinDate + ", ckoutDate="
				+ ckoutDate + ", roomtype=" + roomtype + ", roomCapacity=" + roomCapacity + ", NumberofDay="
				+ NumberofDay + ", totalprice=" + totalprice + "]";
	}
	
	

	
	}