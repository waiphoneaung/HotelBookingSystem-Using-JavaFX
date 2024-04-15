package guest;

public class Guest {
	private int guestID;
	private int roomID;
	private String name;
	private String nrc;
	private String phone;
	private String email;
	private String address;
	private String city;
	
	
	public Guest(int guestID, int roomID, String name, String nrc, String phone, String email, String address,
			String city) {
		super();
		this.guestID = guestID;
		this.roomID = roomID;
		this.name = name;
		this.nrc = nrc;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
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
	@Override
	public String toString() {
		return "Guest [guestID=" + guestID + ", roomID=" + roomID + ", name=" + name + ", nrc=" + nrc + ", phone="
				+ phone + ", email=" + email + ", address=" + address + ", city=" + city + "]";
	}
	
	
}