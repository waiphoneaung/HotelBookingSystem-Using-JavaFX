package room;

public class Room {

	private int RoomID;
	private String RoomType;
	private String RoomCapacity;
	private boolean Is_Empty;
	private double PricePerNight;
	public Room(int roomID, String roomType, String roomCapacity, boolean is_Empty, double pricePerNight) {
		super();
		RoomID = roomID;
		RoomType = roomType;
		RoomCapacity = roomCapacity;
		Is_Empty = is_Empty;
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
	public boolean isIs_Empty() {
		return Is_Empty;
	}
	public void setIs_Empty(boolean is_Empty) {
		Is_Empty = is_Empty;
	}
	public double getPricePerNight() {
		return PricePerNight;
	}
	public void setPricePerNight(double pricePerNight) {
		PricePerNight = pricePerNight;
	}
	@Override
	public String toString() {
		return "Room [RoomID=" + RoomID + ", RoomType=" + RoomType + ", RoomCapacity=" + RoomCapacity + ", Is_Empty="
				+ Is_Empty + ", PricePerNight=" + PricePerNight + "]";
	}
	
}
