package checkIn;

public class CheckIn {
		private String guestID,name, nrc, address, phone, email, city;

		public CheckIn(String guestID, String name, String nrc, String address, String phone, String email,
				String city) {
			super();
			this.guestID = guestID;
			this.name = name;
			this.nrc = nrc;
			this.address = address;
			this.phone = phone;
			this.email = email;
			this.city = city;
		}

		public String getGuestID() {
			return guestID;
		}

		public void setGuestID(String guestID) {
			this.guestID = guestID;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return "CheckIn [guestID=" + guestID + ", name=" + name + ", nrc=" + nrc + ", address=" + address
					+ ", phone=" + phone + ", email=" + email + ", city=" + city + "]";
		}
		
		
}
	
