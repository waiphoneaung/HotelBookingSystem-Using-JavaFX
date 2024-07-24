package room;

public class Room {

	private int RoomID;
	private String RoomType;
	private String RoomCapacity;
	private double PricePerNight;
	public Room(int roomID, String roomType, String roomCapacity, double pricePerNight) {
		super();
		RoomID = roomID;
		RoomType = roomType;
		RoomCapacity = roomCapacity;
		PricePerNight = pricePerNight;
	}
	public int getRoomID() {
		return RoomID;
	}
	public void setRoomID(int roomID) {
		RoomID = roomID;
	}
	public String getRoomType() {
		return RoomType;
	}
	public void setRoomType(String roomType) {
		RoomType = roomType;
	}
	public String getRoomCapacity() {
		return RoomCapacity;
	}
	public void setRoomCapacity(String roomCapacity) {
		RoomCapacity = roomCapacity;
	}
	public double getPricePerNight() {
		return PricePerNight;
	}
	public void setPricePerNight(double pricePerNight) {
		PricePerNight = pricePerNight;
	}
	@Override
	public String toString() {
		return "Room [RoomID=" + RoomID + ", RoomType=" + RoomType + ", RoomCapacity=" + RoomCapacity
				+ ", PricePerNight=" + PricePerNight + "]";
	}
	
}