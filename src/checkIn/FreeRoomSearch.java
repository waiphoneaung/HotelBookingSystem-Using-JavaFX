package checkIn;

public class FreeRoomSearch {
	private int roomId;
	private String roomtype;
	private String roomCapacity;
	private double pricePerNight;
	public FreeRoomSearch(int roomId, String roomType, String roomCapacity, double pricePerNight) {
		super();
		this.roomId = roomId;
		this.roomtype = roomType;
		this.roomCapacity = roomCapacity;
		this.pricePerNight = pricePerNight;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomtype;
	}
	public void setRoomType(String roomtype) {
		this.roomtype = roomtype;
	}
	public String getRoomCapacity() {
		return roomCapacity;
	}
	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	
	@Override
	public String toString() {
		return "FreeRoomSearch [roomId=" + roomId + ", roomtype=" + roomtype + ", roomCapacity=" + roomCapacity
				+ ", pricePerNight=" + pricePerNight + "]";
	}
	
}
