-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 07, 2024 at 04:09 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotelbooking`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingID` int(10) NOT NULL,
  `guestID` int(10) NOT NULL,
  `roomID` int(10) NOT NULL,
  `checkinDate` date NOT NULL,
  `checkoutDate` date NOT NULL,
  `numberOfDay` int(5) NOT NULL,
  `totalPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingID`, `guestID`, `roomID`, `checkinDate`, `checkoutDate`, `numberOfDay`, `totalPrice`) VALUES
(20, 1027, 102, '2024-04-03', '2024-04-04', 1, 20000),
(21, 1028, 330, '2024-04-03', '2024-04-05', 2, 80000);

-- --------------------------------------------------------

--
-- Table structure for table `guest`
--

CREATE TABLE `guest` (
  `guestID` int(30) NOT NULL,
  `roomID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nrc` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `guest`
--

INSERT INTO `guest` (`guestID`, `roomID`, `name`, `nrc`, `phone`, `email`, `address`, `city`) VALUES
(1027, 102, 'Htoo Sett Paing', '9/yamana(N)123456', '094599433443', 'htoosettpaing@gmail.com', 'Dagon Township', 'Yangon'),
(1028, 330, 'Kyaw', '8/yasaka(n)123456', '09999', 'kyaw@gmail.com', 'Yangon', 'Yangon');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomID` int(11) NOT NULL,
  `roomtype` varchar(20) DEFAULT NULL,
  `roomCapacity` varchar(20) DEFAULT NULL,
  `is_empty` tinyint(1) DEFAULT NULL,
  `pricePerNight` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomID`, `roomtype`, `roomCapacity`, `is_empty`, `pricePerNight`) VALUES
(101, 'ECONOMY', 'Single', 1, 20000.00),
(102, 'ECONOMY', 'Single', 1, 20000.00),
(104, 'ECONOMY', 'Single', 1, 20000.00),
(105, 'ECONOMY', 'Single', 1, 20000.00),
(106, 'ECONOMY', 'Single', 1, 20000.00),
(107, 'ECONOMY', 'Single', 1, 20000.00),
(108, 'ECONOMY', 'Single', 1, 20000.00),
(109, 'ECONOMY', 'Single', 1, 20000.00),
(110, 'ECONOMY', 'Single', 1, 20000.00),
(111, 'NORMAL', 'Single', 1, 25000.00),
(112, 'NORMAL', 'Single', 1, 25000.00),
(113, 'NORMAL', 'Single', 1, 25000.00),
(114, 'NORMAL', 'Single', 1, 25000.00),
(115, 'NORMAL', 'Single', 1, 25000.00),
(116, 'NORMAL', 'Single', 1, 25000.00),
(117, 'NORMAL', 'Single', 1, 25000.00),
(118, 'NORMAL', 'Single', 1, 25000.00),
(119, 'NORMAL', 'Single', 1, 25000.00),
(120, 'NORMAL', 'Single', 1, 25000.00),
(121, 'VIP', 'Single', 1, 30000.00),
(122, 'VIP', 'Single', 1, 30000.00),
(123, 'VIP', 'Single', 1, 30000.00),
(124, 'VIP', 'Single', 1, 30000.00),
(125, 'VIP', 'Single', 1, 30000.00),
(126, 'VIP', 'Single', 1, 30000.00),
(127, 'VIP', 'Single', 1, 30000.00),
(128, 'VIP', 'Single', 1, 30000.00),
(129, 'VIP', 'Single', 1, 30000.00),
(130, 'VIP', 'Single', 1, 30000.00),
(201, 'ECONOMY', 'Double', 1, 25000.00),
(202, 'ECONOMY', 'Double', 1, 25000.00),
(203, 'ECONOMY', 'Double', 1, 25000.00),
(204, 'ECONOMY', 'Double', 1, 25000.00),
(205, 'ECONOMY', 'Double', 1, 25000.00),
(206, 'ECONOMY', 'Double', 1, 25000.00),
(207, 'ECONOMY', 'Double', 1, 25000.00),
(208, 'ECONOMY', 'Double', 1, 25000.00),
(209, 'ECONOMY', 'Double', 1, 25000.00),
(210, 'ECONOMY', 'Double', 1, 25000.00),
(211, 'NORMAL', 'Double', 1, 30000.00),
(212, 'NORMAL', 'Double', 1, 30000.00),
(213, 'NORMAL', 'Double', 1, 30000.00),
(214, 'NORMAL', 'Double', 1, 30000.00),
(215, 'NORMAL', 'Double', 1, 30000.00),
(216, 'NORMAL', 'Double', 1, 30000.00),
(217, 'NORMAL', 'Double', 1, 30000.00),
(218, 'NORMAL', 'Double', 1, 30000.00),
(219, 'NORMAL', 'Double', 1, 30000.00),
(220, 'NORMAL', 'Double', 1, 30000.00),
(221, 'VIP', 'Double', 1, 35000.00),
(222, 'VIP', 'Double', 1, 35000.00),
(223, 'VIP', 'Double', 1, 35000.00),
(224, 'VIP', 'Double', 1, 35000.00),
(225, 'VIP', 'Double', 1, 35000.00),
(226, 'VIP', 'Double', 1, 35000.00),
(227, 'VIP', 'Double', 1, 35000.00),
(228, 'VIP', 'Double', 1, 35000.00),
(229, 'VIP', 'Double', 1, 35000.00),
(230, 'VIP', 'Double', 1, 35000.00),
(300, 'ECONOMY', 'Triple', 1, 30000.00),
(301, 'ECONOMY', 'Triple', 1, 30000.00),
(302, 'ECONOMY', 'Triple', 1, 30000.00),
(303, 'ECONOMY', 'Triple', 1, 30000.00),
(304, 'ECONOMY', 'Triple', 1, 30000.00),
(305, 'ECONOMY', 'Triple', 1, 30000.00),
(306, 'ECONOMY', 'Triple', 1, 30000.00),
(307, 'ECONOMY', 'Triple', 1, 30000.00),
(308, 'ECONOMY', 'Triple', 1, 30000.00),
(309, 'ECONOMY', 'Triple', 1, 30000.00),
(310, 'ECONOMY', 'Triple', 1, 30000.00),
(311, 'NORMAL', 'Triple', 1, 35000.00),
(312, 'NORMAL', 'Triple', 1, 35000.00),
(313, 'NORMAL', 'Triple', 1, 35000.00),
(314, 'NORMAL', 'Triple', 1, 35000.00),
(315, 'NORMAL', 'Triple', 1, 35000.00),
(316, 'NORMAL', 'Triple', 1, 35000.00),
(317, 'NORMAL', 'Triple', 1, 35000.00),
(318, 'NORMAL', 'Triple', 1, 35000.00),
(319, 'NORMAL', 'Triple', 1, 35000.00),
(320, 'NORMAL', 'Triple', 1, 35000.00),
(321, 'VIP', 'Triple', 1, 40000.00),
(322, 'VIP', 'Triple', 1, 40000.00),
(323, 'VIP', 'Triple', 1, 40000.00),
(324, 'VIP', 'Triple', 1, 40000.00),
(325, 'VIP', 'Triple', 1, 40000.00),
(326, 'VIP', 'Triple', 1, 40000.00),
(327, 'VIP', 'Triple', 1, 40000.00),
(328, 'VIP', 'Triple', 1, 40000.00),
(329, 'VIP', 'Triple', 1, 40000.00),
(330, 'VIP', 'Triple', 1, 40000.00);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffID` int(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `is_admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffID`, `username`, `password`, `is_admin`) VALUES
(1, 'waiphone', '12345', 1),
(2, 'thomas', '12345', 0),
(3, 'admin', '12345', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingID`),
  ADD KEY `roomID` (`roomID`),
  ADD KEY `guestID` (`guestID`);

--
-- Indexes for table `guest`
--
ALTER TABLE `guest`
  ADD PRIMARY KEY (`guestID`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `guest`
--
ALTER TABLE `guest`
  MODIFY `guestID` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1031;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `roomID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=331;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staffID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
