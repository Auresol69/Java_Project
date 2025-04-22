-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2025 at 09:44 AM
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
-- Database: `sieuthimini`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `maaccount` int(20) NOT NULL,
  `mastaff` int(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `powergroupid` int(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`maaccount`, `mastaff`, `username`, `password`, `powergroupid`, `status`, `email`) VALUES
(1, 1, 'roy', '123', 1, 1, 'A@gm.com');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `mabill` int(20) NOT NULL,
  `macustomer` int(20) NOT NULL,
  `mapayby` int(20) NOT NULL,
  `ngaymua` datetime(6) DEFAULT current_timestamp(6),
  `tongtien` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bill_product`
--

CREATE TABLE `bill_product` (
  `mabill` int(20) NOT NULL,
  `masp` int(20) NOT NULL,
  `soluong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `bill_product`
--
DELIMITER $$
CREATE TRIGGER `after_insert_bill_product` AFTER INSERT ON `bill_product` FOR EACH ROW BEGIN
    UPDATE product SET soluong = soluong - NEW.soluong WHERE masp = NEW.masp;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `macomment` int(11) NOT NULL,
  `noidung` text NOT NULL,
  `ngaydang` datetime(6) DEFAULT current_timestamp(6),
  `masp` int(20) NOT NULL,
  `mauser` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `macustomer` int(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `detail_entry_form`
--

CREATE TABLE `detail_entry_form` (
  `maphieunhap` int(20) DEFAULT NULL,
  `masp` int(20) NOT NULL,
  `dongianhap` int(11) NOT NULL,
  `ngayhethan` datetime(6) DEFAULT NULL,
  `soluongnhap` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `detail_entry_form`
--
DELIMITER $$
CREATE TRIGGER `after_insert_detail_entry_form` AFTER INSERT ON `detail_entry_form` FOR EACH ROW BEGIN
	DECLARE profit DECIMAL(5,2);

	SELECT loinhuan
    INTO profit
    FROM entry_form
    WHERE maphieunhap= NEW.maphieunhap;
    
    UPDATE product SET soluong = soluong + NEW.soluongnhap, dongiasanpham = ROUND(NEW.dongianhap * (1 + profit / 100), -2) where masp = NEW.masp;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `entry_form`
--

CREATE TABLE `entry_form` (
  `maphieunhap` int(20) NOT NULL,
  `ngaynhap` datetime(6) DEFAULT current_timestamp(6),
  `mancc` int(20) NOT NULL,
  `maaccount` int(20) NOT NULL,
  `loinhuan` decimal(5,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `func`
--

CREATE TABLE `func` (
  `funcid` int(20) NOT NULL,
  `funcname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payby`
--

CREATE TABLE `payby` (
  `mapayby` int(20) NOT NULL,
  `paybyname` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`details`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `permission`
--

CREATE TABLE `permission` (
  `permissionid` int(20) NOT NULL,
  `permissionname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `powergroup`
--

CREATE TABLE `powergroup` (
  `powergroupid` int(20) NOT NULL,
  `powergroupname` varchar(50) NOT NULL,
  `status` tinyint(1) DEFAULT 1,
  `created_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `powergroup`
--

INSERT INTO `powergroup` (`powergroupid`, `powergroupname`, `status`, `created_time`, `last_updated`) VALUES
(1, 'Admin', 1, '2025-04-14 23:35:21', '2025-04-14 23:35:21');

-- --------------------------------------------------------

--
-- Table structure for table `powergroup_func_permission`
--

CREATE TABLE `powergroup_func_permission` (
  `powergroupid` int(20) NOT NULL,
  `funcid` int(20) NOT NULL,
  `permissionid` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `masp` int(20) NOT NULL,
  `tensp` varchar(50) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongiasanpham` int(11) DEFAULT NULL,
  `maloaisp` int(20) NOT NULL,
  `mancc` int(20) NOT NULL,
  `img` varchar(100) NOT NULL DEFAULT 'img/Blank.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`masp`, `tensp`, `soluong`, `dongiasanpham`, `maloaisp`, `mancc`, `img`) VALUES
(1, 'Nước ngọt Coca-Cola', 106, 8000, 1, 1, 'img/coca.png'),
(2, 'Snack khoai tây Lay\'s', 155, 12000, 2, 2, 'img/snack.png'),
(3, 'Mì Hảo Hảo tôm chua cay', 207, 3500, 3, 1, 'img/haohao.png'),
(4, 'Sữa tươi Vinamilk 1L', 80, 28000, 4, 3, 'img/vinamilk.png'),
(5, 'Bánh Oreo socola', 90, 15000, 5, 4, 'img/oreo.png');

-- --------------------------------------------------------

--
-- Table structure for table `producttype`
--

CREATE TABLE `producttype` (
  `maloaisp` int(20) NOT NULL,
  `tenloaisp` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `producttype`
--

INSERT INTO `producttype` (`maloaisp`, `tenloaisp`) VALUES
(1, 'Nước giải khát'),
(2, 'Snack'),
(3, 'Mì ăn liền'),
(4, 'Sữa'),
(5, 'Bánh kẹo');

-- --------------------------------------------------------

--
-- Table structure for table `remove_product`
--

CREATE TABLE `remove_product` (
  `masp` int(20) NOT NULL,
  `soluong` int(11) NOT NULL,
  `ngayxoa` datetime(6) DEFAULT current_timestamp(6),
  `lido` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `mastaff` int(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `tennhanstaff` varchar(50) NOT NULL,
  `dienthoai` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`mastaff`, `address`, `tennhanstaff`, `dienthoai`) VALUES
(1, 'hau giang', 'Roy', '1234567891');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `mancc` int(20) NOT NULL,
  `tencc` varchar(25) NOT NULL,
  `diachi` varchar(100) NOT NULL,
  `dienthoai` varchar(11) NOT NULL,
  `sofax` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`mancc`, `tencc`, `diachi`, `dienthoai`, `sofax`) VALUES
(1, 'Công ty ABC', 'Hà Nội', '0123456789', '0241234567'),
(2, 'Snack XYZ', 'TP. Hồ Chí Minh', '0987654321', '0282345678'),
(3, 'Vinamilk', 'TP. Hồ Chí Minh', '0909090909', '0289090909'),
(4, 'Kinh Đô', 'Bình Dương', '0911223344', '0274223344'),
(5, 'Tân Hiệp Phát', 'Bình Dương', '0933112233', '0274112233');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`maaccount`),
  ADD KEY `fk_account_powergroup` (`powergroupid`),
  ADD KEY `fk_account_mastaff` (`mastaff`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`mabill`),
  ADD KEY `fk_bill_macustomer` (`macustomer`),
  ADD KEY `fk_bill_mapayby` (`mapayby`);

--
-- Indexes for table `bill_product`
--
ALTER TABLE `bill_product`
  ADD KEY `fk_bill_product_mabill` (`mabill`),
  ADD KEY `fk_bill_product_masp` (`masp`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`macomment`),
  ADD KEY `fk_comment_masp` (`masp`),
  ADD KEY `fk_comment_mauser` (`mauser`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`macustomer`);

--
-- Indexes for table `detail_entry_form`
--
ALTER TABLE `detail_entry_form`
  ADD KEY `fk_detail_entry_form_maphieunhap` (`maphieunhap`),
  ADD KEY `fk_detail_entry_form_masp` (`masp`);

--
-- Indexes for table `entry_form`
--
ALTER TABLE `entry_form`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `fk_entry_form_mancc` (`mancc`),
  ADD KEY `fk_entry_form_maaccount` (`maaccount`);

--
-- Indexes for table `func`
--
ALTER TABLE `func`
  ADD PRIMARY KEY (`funcid`);

--
-- Indexes for table `payby`
--
ALTER TABLE `payby`
  ADD PRIMARY KEY (`mapayby`);

--
-- Indexes for table `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`permissionid`);

--
-- Indexes for table `powergroup`
--
ALTER TABLE `powergroup`
  ADD PRIMARY KEY (`powergroupid`);

--
-- Indexes for table `powergroup_func_permission`
--
ALTER TABLE `powergroup_func_permission`
  ADD KEY `fk_powergroup_func_permission_powergroup` (`powergroupid`),
  ADD KEY `fk_powergroup_func_permission_func` (`funcid`),
  ADD KEY `fk_powergroup_func_permission_permission` (`permissionid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`masp`),
  ADD KEY `fk_product_maloaisp` (`maloaisp`),
  ADD KEY `fk_product_mancc` (`mancc`);

--
-- Indexes for table `producttype`
--
ALTER TABLE `producttype`
  ADD PRIMARY KEY (`maloaisp`);

--
-- Indexes for table `remove_product`
--
ALTER TABLE `remove_product`
  ADD KEY `fk_remove_product_masp` (`masp`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`mastaff`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`mancc`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `maaccount` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `mabill` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `macomment` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `macustomer` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `entry_form`
--
ALTER TABLE `entry_form`
  MODIFY `maphieunhap` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `func`
--
ALTER TABLE `func`
  MODIFY `funcid` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payby`
--
ALTER TABLE `payby`
  MODIFY `mapayby` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permission`
--
ALTER TABLE `permission`
  MODIFY `permissionid` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `powergroup`
--
ALTER TABLE `powergroup`
  MODIFY `powergroupid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `masp` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `producttype`
--
ALTER TABLE `producttype`
  MODIFY `maloaisp` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `mastaff` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `mancc` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_account_mastaff` FOREIGN KEY (`mastaff`) REFERENCES `staff` (`mastaff`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_account_powergroup` FOREIGN KEY (`powergroupid`) REFERENCES `powergroup` (`powergroupid`) ON DELETE CASCADE;

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `fk_bill_macustomer` FOREIGN KEY (`macustomer`) REFERENCES `customer` (`macustomer`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_bill_mapayby` FOREIGN KEY (`mapayby`) REFERENCES `payby` (`mapayby`) ON DELETE CASCADE;

--
-- Constraints for table `bill_product`
--
ALTER TABLE `bill_product`
  ADD CONSTRAINT `fk_bill_product_mabill` FOREIGN KEY (`mabill`) REFERENCES `bill` (`mabill`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_bill_product_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_comment_mauser` FOREIGN KEY (`mauser`) REFERENCES `customer` (`macustomer`) ON DELETE CASCADE;

--
-- Constraints for table `detail_entry_form`
--
ALTER TABLE `detail_entry_form`
  ADD CONSTRAINT `fk_detail_entry_form_maphieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `entry_form` (`maphieunhap`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detail_entry_form_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE;

--
-- Constraints for table `entry_form`
--
ALTER TABLE `entry_form`
  ADD CONSTRAINT `fk_entry_form_maaccount` FOREIGN KEY (`maaccount`) REFERENCES `account` (`maaccount`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_entry_form_mancc` FOREIGN KEY (`mancc`) REFERENCES `supplier` (`mancc`) ON DELETE CASCADE;

--
-- Constraints for table `powergroup_func_permission`
--
ALTER TABLE `powergroup_func_permission`
  ADD CONSTRAINT `fk_powergroup_func_permission_func` FOREIGN KEY (`funcid`) REFERENCES `func` (`funcid`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_powergroup_func_permission_permission` FOREIGN KEY (`permissionid`) REFERENCES `permission` (`permissionid`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_powergroup_func_permission_powergroup` FOREIGN KEY (`powergroupid`) REFERENCES `powergroup` (`powergroupid`) ON DELETE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_maloaisp` FOREIGN KEY (`maloaisp`) REFERENCES `producttype` (`maloaisp`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_product_mancc` FOREIGN KEY (`mancc`) REFERENCES `supplier` (`mancc`) ON DELETE CASCADE;

--
-- Constraints for table `remove_product`
--
ALTER TABLE `remove_product`
  ADD CONSTRAINT `fk_remove_product_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
