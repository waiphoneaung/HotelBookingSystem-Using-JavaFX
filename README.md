# Hotel Booking System

The Hotel Booking System is a Java-based project developed as a final project for METRO IT & JAPANESE LANGUAGE CENTER's System Creator Course, created by 9 batch students. It aims to demonstrate proficiency in Java programming and software development principles through the creation of a functional hotel management system.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Functions](#functions)
- [Contributing](#contributing)
- [License](#license)
- [Contact Information](#contact-information)

## Features

- Room booking management
- Staff management
- Customer management
- Reports generation (by date range, room type, room capacity)
- Export reports to CSV
- User authentication (Admin and Staff)

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/waiphoneaung/HotelBookingSystem-Using-JavaFX.git
    ```
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Ensure you have JDK 8 or later installed.
4. Build the project to resolve any dependencies.

## Usage

1. Run the application by executing the main class: `Main.java`.
2. The application will open with the login screen.
    ![Login Screen](screenshots/login_screen.png)
3. Use the following credentials to log in:
    - **Admin:** Username: `admin`, Password: `admin`
    - **Staff:** Username: `staff`, Password: `staff`
4. After logging in, navigate through the different functionalities using the menu options.
    ![Main Menu](screenshots/main_menu.png)

## Functions

### 1. Room Booking Management

- **Add Booking:** Allows the user to add a new booking by selecting the customer, room type, and dates.
    ![Add Booking](screenshots/add_booking.png)
- **Update Booking:** Enables updating existing booking details.
- **Delete Booking:** Allows deleting a booking.
- **View Bookings:** Displays a list of all bookings with details.

### 2. Staff Management

- **Add Staff:** Allows adding new staff members with details such as ID, username, password, and position (Admin/Staff).
    ![Add Staff](screenshots/add_staff.png)
- **Update Staff:** Enables updating existing staff details.
- **Delete Staff:** Allows deleting a staff member.
- **View Staff:** Displays a list of all staff members.

### 3. Customer Management

- **Add Customer:** Allows adding new customers with details such as name, contact information, and ID.
    ![Add Customer](screenshots/add_customer.png)
- **Update Customer:** Enables updating existing customer details.
- **Delete Customer:** Allows deleting a customer.
- **View Customers:** Displays a list of all customers.

### 4. Reports Generation

- **Generate Report by Date Range:** Generates a report based on a specified date range, showing bookings, revenue, and occupancy rates.
    ![Generate Report](screenshots/generate_report.png)
- **Generate Report by Room Type:** Generates a report based on room type.
- **Generate Report by Room Capacity:** Generates a report based on room capacity.
- **Export to CSV:** Allows exporting the generated report to a CSV file for saving and recovery.

### 5. User Authentication

- **Admin Access:** Admins have full access to all features and functionalities.
- **Staff Access:** Staff members have restricted access based on their role.

## Contributing

1. Fork the repository.
2. Create a new branch:
    ```bash
    git checkout -b feature/your-feature-name
    ```
3. Make your changes and commit them:
    ```bash
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```bash
    git push origin feature/your-feature-name
    ```
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact Information

For questions, support, or inquiries regarding the Hotel Booking System project, please contact:

- **Wai Phone Aung**
- **Email:** waiphoneaung.metro@gmail.com

You can also access the source code on GitHub:
[Hotel Booking System GitHub Repository](https://github.com/waiphoneaung/HotelBookingSystem-Using-JavaFX)

Thank you for your interest and support!
