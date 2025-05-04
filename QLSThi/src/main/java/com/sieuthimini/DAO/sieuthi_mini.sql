-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 04, 2025 lúc 05:36 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sieuthi_mini2`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
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
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`maaccount`, `mastaff`, `username`, `password`, `powergroupid`, `status`, `email`) VALUES
(4, 1, 'admin123', '$2a$12$gYsrimCAJvUmBxC8Y8K9ueKrP1CVyW/84t3BXv4XP525.e2.xXDCe', 1, 1, NULL),
(6, 2, 'Sale123', '$2a$12$HDwiDoA1Wm8tSvafSQEc9uuDpWXK2nCbBMGuSugpuIXIIvPcLBnoC', 11, 1, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
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
-- Cấu trúc bảng cho bảng `bill_product`
--

CREATE TABLE `bill_product` (
  `mabill` int(20) NOT NULL,
  `masp` int(20) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongiasanpham` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Bẫy `bill_product`
--
DELIMITER $$
CREATE TRIGGER `after_insert_bill_product` AFTER INSERT ON `bill_product` FOR EACH ROW BEGIN
    UPDATE product SET soluong = soluong - NEW.soluong WHERE masp = NEW.masp;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_insert_bill_product` BEFORE INSERT ON `bill_product` FOR EACH ROW BEGIN
    DECLARE gia_sp INT;

    SELECT dongiasanpham INTO gia_sp
    FROM product
    WHERE masp = NEW.masp;

    SET NEW.dongiasanpham = gia_sp;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
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
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `macustomer` int(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  `ngaythamgia` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`macustomer`, `address`, `phone`, `name`, `status`, `ngaythamgia`) VALUES
(1, '123 Đường Lê Lợi, Q1', '0901234567', 'Nguyễn Văn A', 1, '2025-04-24 14:56:47'),
(2, '456 Đường Trần Hưng Đạo, Q5', '0912345678', 'Trần Thị B', 1, '2025-04-24 14:56:47'),
(3, '789 Đường Nguyễn Huệ, Q1', '0923456789', 'Lê Văn C', 1, '2025-04-24 14:56:47'),
(4, '12 Đường Cách Mạng Tháng 8, Q10', '0934567890', 'Phạm Thị D', 1, '2025-04-24 14:56:47'),
(5, '34 Đường Hai Bà Trưng, Q3', '0945678901', 'Hoàng Văn E', 1, '2025-04-24 14:56:47'),
(6, '56 Đường Nguyễn Trãi, Q5', '0956789012', 'Đỗ Thị F', 1, '2025-04-24 14:56:47'),
(7, '78 Đường Pasteur, Q1', '0967890123', 'Phan Văn G', 1, '2025-04-24 14:56:47'),
(8, '90 Đường Điện Biên Phủ, Q3', '0978901234', 'Vũ Thị H', 1, '2025-04-24 14:56:47'),
(9, '23 Đường Võ Thị Sáu, Q1', '0989012345', 'Trịnh Văn I', 1, '2025-04-24 14:56:47'),
(10, '45 Đường Nam Kỳ Khởi Nghĩa, Q3', '0990123456', 'Ngô Thị J', 1, '2025-04-24 14:56:47'),
(12, 'Q1', '012356789', 'Khanh Tuấn', 0, '2025-04-24 15:22:49'),
(13, 'Q1', '0123456789', 'Khanh Tuấn', 1, '2025-04-24 15:28:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `detail_entry_form`
--

CREATE TABLE `detail_entry_form` (
  `maphieunhap` int(20) DEFAULT NULL,
  `masp` int(20) NOT NULL,
  `dongianhap` int(11) NOT NULL,
  `ngayhethan` datetime(6) DEFAULT NULL,
  `soluongnhap` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Bẫy `detail_entry_form`
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
-- Cấu trúc bảng cho bảng `entry_form`
--

CREATE TABLE `entry_form` (
  `maphieunhap` int(20) NOT NULL,
  `ngaynhap` datetime(6) DEFAULT current_timestamp(6),
  `mancc` int(20) NOT NULL,
  `maaccount` int(20) NOT NULL,
  `loinhuan` decimal(5,2) DEFAULT 0.00,
  `status` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DELIMITER $$
CREATE TRIGGER `after_update_entry_form` AFTER UPDATE ON `entry_form` FOR EACH ROW BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE masp_input INT;
    DECLARE soluong_input INT;
    DECLARE current_stock INT;
    DECLARE out_of_stock BOOLEAN DEFAULT FALSE;

    DECLARE cur CURSOR FOR
        SELECT masp, soluongnhap
        FROM detail_entry_form
        WHERE maphieunhap = NEW.maphieunhap;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Chỉ xử lý khi chuyển trạng thái từ 1 -> 0 (hủy phiếu)
    IF OLD.status = 1 AND NEW.status = 0 THEN
        OPEN cur;

        read_loop: LOOP
            FETCH cur INTO masp_input, soluong_input;
            IF done THEN
                LEAVE read_loop;
            END IF;

            -- Kiểm tra tồn kho hiện tại
            SELECT soluong INTO current_stock
            FROM product
            WHERE masp = masp_input;

            -- Nếu không đủ hàng thì đánh dấu lỗi
            IF current_stock < soluong_input THEN
                SET out_of_stock = TRUE;
            END IF;
        END LOOP;

        CLOSE cur;

        -- Nếu có lỗi tồn kho thì báo lỗi
        IF out_of_stock THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Không đủ hàng trong kho để hủy phiếu nhập.';
        ELSE
            -- Nếu đủ tồn kho, tiến hành trừ kho
            SET done = FALSE; -- Reset lại biến done để duyệt lại
            OPEN cur;

            read_loop_update:LOOP
                FETCH cur INTO masp_input, soluong_input;
                IF done THEN
                    LEAVE read_loop_update;
                END IF;

                UPDATE product
                SET soluong = soluong - soluong_input
                WHERE masp = masp_input;
            END LOOP;

            CLOSE cur;
        END IF;
    END IF;
END
$$
DELIMITER ;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `func`
--

CREATE TABLE `func` (
  `funcid` int(20) NOT NULL,
  `funcname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `func`
--

INSERT INTO `func` (`funcid`, `funcname`) VALUES
(1, 'Quản lý khách hàng'),
(2, 'Quản lý nhân viên'),
(3, 'Quản lý nhóm quyền'),
(4, 'Quản lý nhà cung cấp'),
(5, 'Quản lý tài khoản'),
(6, 'Quản lý sản phẩm'),
(7, 'Quản lý thống kê'),
(8, 'Quản lý xuất hàng'),
(9, 'Quản lý sản phẩm hủy');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payby`
--

CREATE TABLE `payby` (
  `mapayby` int(20) NOT NULL,
  `paybyname` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`details`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permission`
--

CREATE TABLE `permission` (
  `permissionid` int(20) NOT NULL,
  `permissionname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `permission`
--

INSERT INTO `permission` (`permissionid`, `permissionname`) VALUES
(1, 'Xem'),
(2, 'Tạo mới'),
(3, 'Cập nhật'),
(4, 'Xóa');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `powergroup`
--

CREATE TABLE `powergroup` (
  `powergroupid` int(20) NOT NULL,
  `powergroupname` varchar(50) NOT NULL,
  `status` tinyint(1) DEFAULT 1,
  `created_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `powergroup`
--

INSERT INTO `powergroup` (`powergroupid`, `powergroupname`, `status`, `created_time`, `last_updated`) VALUES
(1, 'Admin', 1, '2025-04-14 23:35:21', '2025-04-14 23:35:21'),
(10, 'Kho', 1, '2025-04-24 06:05:23', '2025-04-24 06:32:54'),
(11, 'Sale', 1, '2025-04-24 06:33:46', '2025-04-24 06:34:19');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `powergroup_func_permission`
--

CREATE TABLE `powergroup_func_permission` (
  `powergroupid` int(20) NOT NULL,
  `funcid` int(20) NOT NULL,
  `permissionid` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `powergroup_func_permission`
--

INSERT INTO `powergroup_func_permission` (`powergroupid`, `funcid`, `permissionid`) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),
(1, 2, 1),
(1, 2, 2),
(1, 2, 3),
(1, 2, 4),
(1, 3, 1),
(1, 3, 2),
(1, 3, 3),
(1, 3, 4),
(1, 4, 1),
(1, 4, 2),
(1, 4, 3),
(1, 4, 4),
(1, 5, 1),
(1, 5, 2),
(1, 5, 3),
(1, 5, 4),
(1, 6, 1),
(1, 6, 2),
(1, 6, 3),
(1, 6, 4),
(1, 7, 1),
(1, 7, 2),
(1, 7, 3),
(1, 7, 4),
(1, 8, 1),
(1, 8, 2),
(1, 8, 3),
(1, 8, 4),
(1, 9, 1),
(1, 9, 2),
(1, 9, 3),
(1, 9, 4),
(10, 1, 1),
(10, 2, 1),
(10, 3, 1),
(10, 4, 1),
(10, 5, 1),
(10, 6, 1),
(10, 7, 1),
(10, 8, 1),
(10, 9, 1),
(11, 1, 1),
(11, 1, 2),
(11, 1, 3),
(11, 1, 4),
(11, 2, 1),
(11, 3, 1),
(11, 4, 1),
(11, 5, 1),
(11, 6, 1),
(11, 7, 1),
(11, 8, 1),
(11, 9, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `masp` int(20) NOT NULL,
  `tensp` varchar(50) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongiasanpham` int(11) DEFAULT NULL,
  `maloaisp` int(20) NOT NULL,
  `img` varchar(100) NOT NULL DEFAULT 'img/Blank.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`masp`, `tensp`, `soluong`, `dongiasanpham`, `maloaisp`, `img`) VALUES
(1, 'Nước ngọt Coca-Cola', 106, 8000, 1, 'img/coca.png'),
(2, 'Snack khoai tây Lay\'s', 155, 12000, 2, 'img/snack.png'),
(3, 'Mì Hảo Hảo tôm chua cay', 207, 3500, 3, 'img/haohao.png'),
(4, 'Sữa tươi Vinamilk 1L', 80, 28000, 4, 'img/vinamilk.png'),
(5, 'Bánh Oreo socola', 90, 15000, 5, 'img/oreo.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `producttype`
--

CREATE TABLE `producttype` (
  `maloaisp` int(20) NOT NULL,
  `tenloaisp` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `producttype`
--

INSERT INTO `producttype` (`maloaisp`, `tenloaisp`) VALUES
(1, 'Nước giải khát'),
(2, 'Snack'),
(3, 'Mì ăn liền'),
(4, 'Sữa'),
(5, 'Bánh kẹo');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `remove_product`
--

CREATE TABLE `remove_product` (
  `masp` int(20) NOT NULL,
  `soluong` int(11) NOT NULL,
  `ngayxoa` datetime(6) DEFAULT current_timestamp(6),
  `lido` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `mastaff` int(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `tenstaff` varchar(50) NOT NULL,
  `dienthoai` varchar(11) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`mastaff`, `address`, `tenstaff`, `dienthoai`, `status`) VALUES
(1, 'hau giang', 'Roy', '1234567891', 1),
(2, 'HCM', 'dabeo', '0909234567', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `mancc` int(20) NOT NULL,
  `tencc` varchar(25) NOT NULL,
  `diachi` varchar(100) NOT NULL,
  `dienthoai` varchar(11) NOT NULL,
  `sofax` varchar(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `supplier`
--

INSERT INTO `supplier` (`mancc`, `tencc`, `diachi`, `dienthoai`, `sofax`, `status`) VALUES
(1, 'Công ty ABC', 'Hà Nội', '0123456789', '0241234567', 1),
(2, 'Snack XYZ', 'TP. Hồ Chí Minh', '0987654321', '0282345678', 1),
(3, 'Vinamilk', 'TP. Hồ Chí Minh', '0909090909', '0289090909', 1),
(4, 'Kinh Đô', 'Bình Dương', '0911223344', '0274223344', 1),
(5, 'Tân Hiệp Phát', 'Bình Dương', '0933112233', '0274112233', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`maaccount`),
  ADD KEY `fk_account_powergroup` (`powergroupid`),
  ADD KEY `fk_account_mastaff` (`mastaff`);

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`mabill`),
  ADD KEY `fk_bill_macustomer` (`macustomer`),
  ADD KEY `fk_bill_mapayby` (`mapayby`);

--
-- Chỉ mục cho bảng `bill_product`
--
ALTER TABLE `bill_product`
  ADD KEY `fk_bill_product_mabill` (`mabill`),
  ADD KEY `fk_bill_product_masp` (`masp`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`macomment`),
  ADD KEY `fk_comment_masp` (`masp`),
  ADD KEY `fk_comment_mauser` (`mauser`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`macustomer`);

--
-- Chỉ mục cho bảng `detail_entry_form`
--
ALTER TABLE `detail_entry_form`
  ADD KEY `fk_detail_entry_form_maphieunhap` (`maphieunhap`),
  ADD KEY `fk_detail_entry_form_masp` (`masp`);

--
-- Chỉ mục cho bảng `entry_form`
--
ALTER TABLE `entry_form`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `fk_entry_form_mancc` (`mancc`),
  ADD KEY `fk_entry_form_maaccount` (`maaccount`);

--
-- Chỉ mục cho bảng `func`
--
ALTER TABLE `func`
  ADD PRIMARY KEY (`funcid`);

--
-- Chỉ mục cho bảng `payby`
--
ALTER TABLE `payby`
  ADD PRIMARY KEY (`mapayby`);

--
-- Chỉ mục cho bảng `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`permissionid`);

--
-- Chỉ mục cho bảng `powergroup`
--
ALTER TABLE `powergroup`
  ADD PRIMARY KEY (`powergroupid`);

--
-- Chỉ mục cho bảng `powergroup_func_permission`
--
ALTER TABLE `powergroup_func_permission`
  ADD PRIMARY KEY (`powergroupid`,`funcid`,`permissionid`),
  ADD KEY `fk_powergroup_func_permission_powergroup` (`powergroupid`),
  ADD KEY `fk_powergroup_func_permission_func` (`funcid`),
  ADD KEY `fk_powergroup_func_permission_permission` (`permissionid`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`masp`),
  ADD KEY `fk_product_maloaisp` (`maloaisp`);

--
-- Chỉ mục cho bảng `producttype`
--
ALTER TABLE `producttype`
  ADD PRIMARY KEY (`maloaisp`);

--
-- Chỉ mục cho bảng `remove_product`
--
ALTER TABLE `remove_product`
  ADD KEY `fk_remove_product_masp` (`masp`);

--
-- Chỉ mục cho bảng `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`mastaff`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`mancc`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `maaccount` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `mabill` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `macomment` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `macustomer` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `entry_form`
--
ALTER TABLE `entry_form`
  MODIFY `maphieunhap` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `func`
--
ALTER TABLE `func`
  MODIFY `funcid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `payby`
--
ALTER TABLE `payby`
  MODIFY `mapayby` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `permission`
--
ALTER TABLE `permission`
  MODIFY `permissionid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `powergroup`
--
ALTER TABLE `powergroup`
  MODIFY `powergroupid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `masp` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `producttype`
--
ALTER TABLE `producttype`
  MODIFY `maloaisp` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `mastaff` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `supplier`
--
ALTER TABLE `supplier`
  MODIFY `mancc` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_account_mastaff` FOREIGN KEY (`mastaff`) REFERENCES `staff` (`mastaff`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_account_powergroup` FOREIGN KEY (`powergroupid`) REFERENCES `powergroup` (`powergroupid`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `fk_bill_macustomer` FOREIGN KEY (`macustomer`) REFERENCES `customer` (`macustomer`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_bill_mapayby` FOREIGN KEY (`mapayby`) REFERENCES `payby` (`mapayby`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `bill_product`
--
ALTER TABLE `bill_product`
  ADD CONSTRAINT `fk_bill_product_mabill` FOREIGN KEY (`mabill`) REFERENCES `bill` (`mabill`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_bill_product_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_comment_mauser` FOREIGN KEY (`mauser`) REFERENCES `customer` (`macustomer`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `detail_entry_form`
--
ALTER TABLE `detail_entry_form`
  ADD CONSTRAINT `fk_detail_entry_form_maphieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `entry_form` (`maphieunhap`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detail_entry_form_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `entry_form`
--
ALTER TABLE `entry_form`
  ADD CONSTRAINT `fk_entry_form_maaccount` FOREIGN KEY (`maaccount`) REFERENCES `account` (`maaccount`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_entry_form_mancc` FOREIGN KEY (`mancc`) REFERENCES `supplier` (`mancc`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `powergroup_func_permission`
--
ALTER TABLE `powergroup_func_permission`
  ADD CONSTRAINT `fk_powergroup_func_permission_func` FOREIGN KEY (`funcid`) REFERENCES `func` (`funcid`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_powergroup_func_permission_permission` FOREIGN KEY (`permissionid`) REFERENCES `permission` (`permissionid`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_powergroup_func_permission_powergroup` FOREIGN KEY (`powergroupid`) REFERENCES `powergroup` (`powergroupid`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_maloaisp` FOREIGN KEY (`maloaisp`) REFERENCES `producttype` (`maloaisp`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `remove_product`
--
ALTER TABLE `remove_product`
  ADD CONSTRAINT `fk_remove_product_masp` FOREIGN KEY (`masp`) REFERENCES `product` (`masp`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
