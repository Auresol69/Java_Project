-- Xoá bảng nếu tồn tại
USE sieuthi_mini;
DROP TABLE IF EXISTS 
    remove_product,
    comment,
    detail_entry_form,
    bill_product,
    bill,
    entry_form,
    account,
    staff,
    product,
    producttype,
    customer,
    supplier,
    payby,
    powergroup_func,
    powergroup,
    func;


-- Bảng nhà cung cấp
CREATE TABLE IF NOT EXISTS supplier (
    mancc VARCHAR(20) NOT NULL PRIMARY KEY,
    tencc VARCHAR(25) NOT NULL,
    diachi VARCHAR(100) NOT NULL,
    dienthoai VARCHAR(11) NOT NULL,
    sofax VARCHAR(11) NOT NULL
);

-- Bảng nhân viên
CREATE TABLE IF NOT EXISTS staff (
    mastaff VARCHAR(20) NOT NULL PRIMARY KEY,
    address VARCHAR(100),
    tennhanstaff VARCHAR(50) NOT NULL,
    dienthoai VARCHAR(11) NOT NULL
);

-- Bảng nhóm quyền (INT)
CREATE TABLE IF NOT EXISTS powergroup (
    powergroupid INT NOT NULL PRIMARY KEY,
    powergroupname VARCHAR(50) NOT NULL
);

-- Bảng chức năng
CREATE TABLE IF NOT EXISTS func (
    funcid VARCHAR(20) NOT NULL PRIMARY KEY,
    funcname VARCHAR(50) NOT NULL
);

-- Bảng liên kết nhóm quyền - chức năng
CREATE TABLE IF NOT EXISTS powergroup_func (
    powergroupid INT NOT NULL,
    funcid VARCHAR(20) NOT NULL,
    PRIMARY KEY (powergroupid, funcid),
    FOREIGN KEY (powergroupid) REFERENCES powergroup(powergroupid) ON DELETE CASCADE,
    FOREIGN KEY (funcid) REFERENCES func(funcid) ON DELETE CASCADE
);

-- Bảng tài khoản
CREATE TABLE IF NOT EXISTS account (
    maaccount VARCHAR(20) NOT NULL PRIMARY KEY,
    mastaff VARCHAR(20) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    powergroupid INT,
    email VARCHAR(255),
    FOREIGN KEY (mastaff) REFERENCES staff(mastaff) ON DELETE CASCADE,
    FOREIGN KEY (powergroupid) REFERENCES powergroup(powergroupid) ON DELETE SET NULL
);

-- Bảng phiếu nhập
CREATE TABLE IF NOT EXISTS entry_form (
    maphieunhap VARCHAR(20) NOT NULL PRIMARY KEY,
    ngaynhap DATETIME(6),
    mancc VARCHAR(20) NOT NULL,
    maaccount VARCHAR(20) NOT NULL,
    FOREIGN KEY (mancc) REFERENCES supplier(mancc) ON DELETE CASCADE,
    FOREIGN KEY (maaccount) REFERENCES account(maaccount) ON DELETE CASCADE
);

-- Bảng loại sản phẩm
CREATE TABLE IF NOT EXISTS producttype (
    maloaisp VARCHAR(20) NOT NULL PRIMARY KEY,
    tenloaisp VARCHAR(50) NOT NULL
);

-- Bảng sản phẩm
CREATE TABLE IF NOT EXISTS product (
    masp VARCHAR(20) NOT NULL PRIMARY KEY,
    tensp VARCHAR(50) NOT NULL,
    soluong INT(11) NOT NULL,
    dongiasanpham INT(11),
    maloaisp VARCHAR(20) NOT NULL,
    mancc VARCHAR(20) NOT NULL,
    img VARCHAR(100) NOT NULL DEFAULT 'img/Blank.png',
    FOREIGN KEY (maloaisp) REFERENCES producttype(maloaisp) ON DELETE CASCADE,
    FOREIGN KEY (mancc) REFERENCES supplier(mancc) ON DELETE CASCADE
);

-- Bảng chi tiết phiếu nhập
CREATE TABLE IF NOT EXISTS detail_entry_form (
    maphieunhap VARCHAR(20) NOT NULL,
    masp VARCHAR(20) NOT NULL,
    dongianhap INT(11) NOT NULL,
    ngayhethan DATETIME(6) NOT NULL,
    soluongnhap INT(11) NOT NULL,
    PRIMARY KEY (maphieunhap, masp),
    FOREIGN KEY (maphieunhap) REFERENCES entry_form(maphieunhap) ON DELETE CASCADE,
    FOREIGN KEY (masp) REFERENCES product(masp) ON DELETE CASCADE
);

-- Bảng khách hàng
CREATE TABLE IF NOT EXISTS customer (
    macustomer VARCHAR(20) NOT NULL PRIMARY KEY,
    address VARCHAR(100),
    phone VARCHAR(11),
    name VARCHAR(25)
);

-- Bảng bình luận
CREATE TABLE IF NOT EXISTS comment (
    macomment INT(11) AUTO_INCREMENT PRIMARY KEY,
    noidung TEXT NOT NULL,
    ngaydang DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    masp VARCHAR(20) NOT NULL,
    mauser VARCHAR(20) NOT NULL,
    FOREIGN KEY (masp) REFERENCES product(masp) ON DELETE CASCADE,
    FOREIGN KEY (mauser) REFERENCES customer(macustomer) ON DELETE CASCADE
);

-- Bảng phiếu xoá sản phẩm
CREATE TABLE IF NOT EXISTS remove_product (
    masp VARCHAR(20) NOT NULL,
    soluong INT(11) NOT NULL,
    ngayxoa DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    lido VARCHAR(255) NOT NULL,
    FOREIGN KEY (masp) REFERENCES product(masp) ON DELETE CASCADE
);

-- Bảng phương thức thanh toán
CREATE TABLE IF NOT EXISTS payby (
    mapayby VARCHAR(20) NOT NULL PRIMARY KEY,
    paybyname VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    details JSON NOT NULL
);

-- Bảng hoá đơn
CREATE TABLE IF NOT EXISTS bill (
    mabill VARCHAR(20) NOT NULL PRIMARY KEY,
    macustomer VARCHAR(20) NOT NULL,
    mapayby VARCHAR(20) NOT NULL,
    ngaymua DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    tongtien INT(11) DEFAULT 0,
    FOREIGN KEY (macustomer) REFERENCES customer(macustomer) ON DELETE CASCADE,
    FOREIGN KEY (mapayby) REFERENCES payby(mapayby) ON DELETE CASCADE
);

-- Bảng chi tiết hoá đơn
CREATE TABLE IF NOT EXISTS bill_product (
    mabill VARCHAR(20) NOT NULL,
    masp VARCHAR(20) NOT NULL,
    soluong INT(11) NOT NULL,
    PRIMARY KEY (mabill, masp),
    FOREIGN KEY (mabill) REFERENCES bill(mabill) ON DELETE CASCADE,
    FOREIGN KEY (masp) REFERENCES product(masp) ON DELETE CASCADE
);
-- ========================
-- TRIGGER SINH MÃ TỰ ĐỘNG
-- ========================
DELIMITER $$

CREATE TRIGGER before_insert_customer
BEFORE INSERT ON customer
FOR EACH ROW
BEGIN
    DECLARE new_id INT;
    SELECT COALESCE(MAX(CAST(SUBSTRING(macustomer, 4) AS UNSIGNED)), 0) + 1 INTO new_id FROM customer;
    SET NEW.macustomer = CONCAT('CUS', LPAD(new_id, 3, '0'));
END$$

CREATE TRIGGER before_insert_product
BEFORE INSERT ON product
FOR EACH ROW
BEGIN
    DECLARE new_id INT;
    SELECT COALESCE(MAX(CAST(SUBSTRING(masp, 4) AS UNSIGNED)), 0) + 1 INTO new_id FROM product;
    SET NEW.masp = CONCAT('PRO', LPAD(new_id, 3, '0'));
END$$

CREATE TRIGGER before_insert_producttype
BEFORE INSERT ON producttype
FOR EACH ROW
BEGIN
    DECLARE new_id INT;
    SELECT COALESCE(MAX(CAST(SUBSTRING(maloaisp, 4) AS UNSIGNED)), 0) + 1 INTO new_id FROM producttype;
    SET NEW.maloaisp = CONCAT('TYP', LPAD(new_id, 3, '0'));
END$$

CREATE TRIGGER before_insert_supplier
BEFORE INSERT ON supplier
FOR EACH ROW
BEGIN
    DECLARE new_id INT;
    SELECT COALESCE(MAX(CAST(SUBSTRING(mancc, 4) AS UNSIGNED)), 0) + 1 INTO new_id FROM supplier;
    SET NEW.mancc = CONCAT('SUP', LPAD(new_id, 3, '0'));
END$$

CREATE TRIGGER after_insert_detail_entry_form
AFTER INSERT ON detail_entry_form
FOR EACH ROW
BEGIN
    UPDATE product
    SET soluong = soluong + NEW.soluongnhap,
        dongiasanpham = NEW.dongianhap
    WHERE masp = NEW.masp;
END$$

CREATE TRIGGER after_insert_bill_product
AFTER INSERT ON bill_product
FOR EACH ROW
BEGIN
    UPDATE product
    SET soluong = soluong - NEW.soluong
    WHERE masp = NEW.masp;
END$$

DELIMITER ;

-- ========================
-- DỮ LIỆU MẪU
-- ========================

-- Nhóm quyền
INSERT INTO powergroup (powergroupid, powergroupname) VALUES
(1, 'Admin'),
(2, 'Nhân viên'),
(3, 'Khách hàng');

-- Chức năng
INSERT INTO func (funcid, funcname) VALUES
('FUNC01', 'Quản lý tài khoản'),
('FUNC02', 'Quản lý sản phẩm'),
('FUNC03', 'Xem hoá đơn');

-- Liên kết quyền - chức năng
INSERT INTO powergroup_func (powergroupid, funcid) VALUES
(1, 'FUNC01'),
(1, 'FUNC02'),
(1, 'FUNC03'),
(2, 'FUNC02'),
(2, 'FUNC03');

-- Nhân viên và tài khoản
INSERT INTO staff (mastaff, address, tennhanstaff, dienthoai) VALUES
('STF001', '123 Nguyễn Văn Cừ, Q5', 'Nguyễn Văn A', '0909123456'),
('STF002', '456 Lê Lợi, Q1', 'Trần Thị B', '0911222333');

INSERT INTO account (maaccount, mastaff, username, password, powergroupid, email) VALUES
('ACC001', 'STF001', 'admin', '123456', 1, 'admin@example.com'),
('ACC002', 'STF002', 'sale', 'sale', 2, 'staff@example.com');
ALTER TABLE account
ADD COLUMN trangthai BOOLEAN DEFAULT TRUE;
-- Thêm loại sản phẩm (để trigger tự sinh ID)
INSERT INTO producttype (tenloaisp) VALUES ('Điện thoại'), ('Laptop');

-- Thêm nhà cung cấp
INSERT INTO supplier (tencc, diachi, dienthoai, sofax) VALUES
('FPT', 'Hà Nội', '0241234567', '0247654321'),
('Thế giới di động', 'TP.HCM', '0289876543', '0281234567');

-- Thêm sản phẩm
INSERT INTO product (tensp, soluong, dongiasanpham, maloaisp, mancc, img) VALUES
('iPhone 14', 50, 22000000, 'TYP001', 'SUP001', 'img/iphone14.png'),
('MacBook Air', 30, 28000000, 'TYP002', 'SUP002', 'img/macbook.png');

-- Khách hàng
INSERT INTO customer (address, phone, name) VALUES
('789 Nguyễn Huệ', '0933445566', 'Lê Văn C'),
('321 Trần Hưng Đạo', '0944556677', 'Phạm Thị D');

-- Phương thức thanh toán
INSERT INTO payby (mapayby, paybyname, address, details) VALUES
('PAY001', 'Chuyển khoản', 'Ngân hàng A', JSON_OBJECT('stk', '123456789', 'nganhang', 'Vietcombank')),
('PAY002', 'Tiền mặt', 'Tại cửa hàng', JSON_OBJECT('ghichu', 'Thanh toán trực tiếp'));

-- Hóa đơn
INSERT INTO bill (mabill, macustomer, mapayby, tongtien) VALUES
('BILL001', 'CUS001', 'PAY001', 22000000);

-- Chi tiết hoá đơn
INSERT INTO bill_product (mabill, masp, soluong) VALUES
('BILL001', 'PRO001', 1);

-- Phiếu nhập
INSERT INTO entry_form (maphieunhap, ngaynhap, mancc, maaccount) VALUES
('ENT001', NOW(), 'SUP001', 'ACC001');

-- Chi tiết phiếu nhập
INSERT INTO detail_entry_form (maphieunhap, masp, dongianhap, ngayhethan, soluongnhap) VALUES
('ENT001', 'PRO001', 20000000, '2026-12-31', 20);

-- Bình luận
INSERT INTO comment (noidung, masp, mauser) VALUES
('Sản phẩm tốt lắm!', 'PRO001', 'CUS001');

-- Phiếu xoá sản phẩm
INSERT INTO remove_product (masp, soluong, lido) VALUES
('PRO002', 5, 'Hỏng, không thể bán');