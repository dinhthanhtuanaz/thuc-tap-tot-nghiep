-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 07, 2020 lúc 06:21 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dbtitiv1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `idsanpham` int(11) NOT NULL,
  `iddonhang` int(11) NOT NULL,
  `tensanpham` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `hinhsanpham` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `idsanpham`, `iddonhang`, `tensanpham`, `hinhsanpham`, `soluong`, `gia`) VALUES
(1, 1, 1, 'Truyện tranh Mắt Biếc', 'sp-truyen-mat-biec.jpg', 2, 140000),
(2, 4, 10, 'Khi hơi thở hóa thinh không', 'sp-khi-hoi-tho-hoa-thinh-khong.jpg', 1, 74120),
(3, 2, 10, 'Xe máy Honda Winner', 'avt-xe-co.png', 1, 45000000),
(4, 1, 11, 'Truyện tranh Mắt Biếc', 'sp-truyen-mat-biec.jpg', 1, 70000),
(5, 1, 12, 'Truyện tranh Mắt Biếc', 'sp-truyen-mat-biec.jpg', 1, 70000),
(6, 3, 13, 'Cô gái trong trang sách', 'sp-co-gai-trong-trang-sach.jpg', 1, 84000),
(7, 5, 14, 'Samsung Galaxy A51', 'ss-a51-xanh-1.png', 1, 7990000),
(8, 2, 15, 'Xe máy Honda Winner', 'avt-xe-co.png', 1, 45000000),
(9, 8, 16, 'Samsung Galaxy Fold', 'samsung-galaxy-fold-black-400x460.png', 1, 50000000),
(10, 12, 16, 'Quạt Đứng Mitsubishi LV16-GV CY-GY', 'quat-dung-hoi-nuoc.jpg', 1, 1369000),
(11, 1, 17, 'Truyện tranh Mắt Biếc', 'sp-truyen-mat-biec.jpg', 3, 210000),
(12, 7, 17, 'Laptop Apple Macbook Air 2019 i5 1.6GHz/8GB/128GB (MVFM2SA/A)\r\n', 'apple-macbook-air-2019-i5-16ghz-8gb-128gb-mvfm2sa-13-32-600x600.jpg', 2, 57980000),
(13, 5, 18, 'Samsung Galaxy A51', 'ss-a51-xanh-1.png', 2, 15980000),
(14, 5, 19, 'Samsung Galaxy A51', 'ss-a51-xanh-1.png', 1, 7990000),
(15, 10, 19, 'Điện thoại iPhone 8 Plus 64GB', 'iphone-8-plus-tet-400x460-400x460.png', 1, 15990000),
(16, 3, 20, 'Cô gái trong trang sách', 'sp-co-gai-trong-trang-sach.jpg', 1, 84000),
(17, 3, 21, 'Cô gái trong trang sách', 'sp-co-gai-trong-trang-sach.jpg', 2, 168000),
(18, 5, 22, 'Samsung Galaxy A51', 'ss-a51-xanh-1.png', 1, 7990000),
(19, 8, 23, 'Samsung Galaxy Fold', 'samsung-galaxy-fold-black-400x460.png', 1, 50000000),
(20, 2, 23, 'Xe máy Honda Winner', 'avt-xe-co.png', 2, 90000000),
(21, 5, 23, 'Samsung Galaxy A51', 'ss-a51-xanh-1.png', 1, 7990000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhmuc`
--

CREATE TABLE `danhmuc` (
  `id` int(11) NOT NULL,
  `tendanhmuc` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `hinhnen` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `mota` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `danhmuc`
--

INSERT INTO `danhmuc` (`id`, `tendanhmuc`, `hinhnen`, `mota`) VALUES
(1, 'Đồ công nghệ', 'bg-do-cong-nghe.jpg', 'do-cong-nghe.png'),
(2, 'Điện gia dụng', 'bg-dien-gia-dung.jpg', 'avt-dien-gia-dung.png'),
(3, 'Bách hóa', 'bg-bach-hoa.png', 'avt-bach-hoa.png'),
(4, 'Đồ chơi mẹ và bé', 'bg-do-choi-me-va-be.jpg', 'avt-do-choi-me-va-be.jpg'),
(5, 'Làm đẹp Sức khỏe', 'bg-lam-dep-suc-khoe.png', 'avt-lam-dep-suc-khoe.png'),
(6, 'Thời trang', 'bg-thoi-trang-phu-kien.jpg', 'avt-thoi-trang-phu-kien.png'),
(7, 'Văn phòng phẩm', 'bg-van-phong-pham.jpg', 'avt-van-phong-pham.png'),
(8, 'Máy ảnh - Máy quay phim', 'bg-may-anh-quay-phim.jpg', 'avt-may-anh-quay-phim.jpg'),
(9, 'Xe cộ', 'bg-xe-co.jpg', 'avt-xe-co.png'),
(10, 'Phụ kiện công nghệ', 'bg-phu-kien-cong-nghe.jpg', 'avt-phu-kien-cong-nghe.jpg'),
(11, 'Nhà sách', 'bg-nha-sach.jpg', 'avt-nha-sach.jpg'),
(19, 'Danh mục 5', 'dog-01.jpg', 'con-bo.png'),
(21, 'Danh mục test', 'ho-guom.jpg', 'ho-guom.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `idkhachhang` int(11) NOT NULL,
  `sodienthoai` varchar(11) COLLATE utf8_vietnamese_ci NOT NULL,
  `diachigiao` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `ngaymua` date NOT NULL,
  `ghichu` text COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `idkhachhang`, `sodienthoai`, `diachigiao`, `ngaymua`, `ghichu`) VALUES
(1, 1, '04313', 'Gia Hòa - Gia Viễn - Ninh Bình', '2020-01-30', NULL),
(2, 1, '345', 'GV-NB', '2020-01-01', 'Giao trước 3h'),
(3, 1, '345', 'GV-NB', '2020-01-01', 'Giao trước 3h'),
(4, 2, '091234', 'Ha Noi', '2020-02-01', 'Giao cho em luc 8h sang'),
(5, 2, '091', 'NB', '2020-02-01', 'NB'),
(6, 2, '092', 'HN', '2020-02-01', 'HN HN HN'),
(7, 13, '092', 'Vinh Phuc', '2020-02-01', 'VP'),
(8, 13, '095', 'Ha Giang', '2020-02-01', 'HG GGG'),
(9, 13, '096', 'Bac Can', '2020-02-01', 'BC'),
(10, 13, '66', '66', '2020-02-01', '66'),
(11, 2, '099', 'Nam Dinh', '2020-02-01', 'Nam Dinh...'),
(12, 2, '031', 'Lai Chau', '2020-02-01', 'Lai Chau...'),
(13, 2, '0168', 'Ninh Binh', '2020-02-01', 'Ninh Binh Que Toi'),
(14, 16, '0932', 'Bac Giang', '2020-02-04', 'GB'),
(15, 16, '0923512', 'Nha Trang', '2020-02-04', 'Test'),
(16, 17, '0342582266', 'Gia Viễn - Ninh Bình', '2020-02-05', 'Giao sớm...'),
(17, 2, '034', 'ninh binh', '2020-02-14', 'abc'),
(18, 20, '05434', 'ha noi', '2020-02-14', 'tuan'),
(19, 22, '098000', 'Tien Hai - Thai Binh', '2020-02-26', ''),
(20, 22, '098000', 'Nhon - Bac Tu Liem', '2020-02-26', 'Giao buoi trua cho em!'),
(21, 22, '098000', 'Cau Giay - HN', '2020-02-26', 'Mua them 2 quyen nua cho vui'),
(22, 1, '0342582266', 'Gia Vien -  Ninh Binh', '2020-04-06', ''),
(23, 1, '0342582266', 'Minh Khai', '2020-05-07', 'OK');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `id` int(11) NOT NULL,
  `sodienthoai` varchar(11) COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `matkhau` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `hoten` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `ngaysinh` date NOT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `nhanthongbao` tinyint(1) NOT NULL,
  `ngaytao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`id`, `sodienthoai`, `email`, `matkhau`, `hoten`, `ngaysinh`, `gioitinh`, `nhanthongbao`, `ngaytao`) VALUES
(1, '034', 'tuan1@gmail.com', 'tuan1809', 'Tuân 1', '1998-01-01', 1, 1, '2020-01-26'),
(2, '', 'tuan2@gmail.com', 'tuan2', 'Tuân 2', '0000-00-00', 1, 0, '2020-01-26'),
(3, '', 'tuan3@gmail.com', 'tuan3', '', '0000-00-00', 0, 0, '0000-00-00'),
(13, '034', 'tuan4@gmail.com', 'tuan4', 'tuan4', '2009-04-17', 1, 0, '2020-01-27'),
(14, '034', 'tuan5@gmail.com', 'tuan4', 'tuan4', '2009-04-17', 0, 0, '2020-01-27'),
(15, '6', '6', '6', '6', '2020-01-16', 0, 1, '2020-01-27'),
(16, '099', 'tung@gmail.com', 'tung', 'Dinh Thanh Tung', '1992-02-11', 0, 1, '2020-02-01'),
(17, '0342582266', 'dinhthanhtuanaz@gmail.com', 'tuan12345', 'Đinh Thanh Tuân', '1998-09-18', 1, 1, '2020-02-05'),
(18, '032', 'ha@gmail.com', 'ha', 'Ha', '2012-02-12', 0, 1, '2020-02-05'),
(19, '0234235', 'tuan10@gmail.com', 'tuan10', 'tuan10', '2014-02-05', 1, 1, '2020-02-05'),
(20, '035', 'tu@gmail.com', 'tu', 'doa tu', '2020-02-11', 1, 1, '2020-02-14'),
(21, '944174', 'tuan@gmail. com', 'tuan', 'Đinh Thanh Tuân', '2019-10-19', 0, 1, '2020-02-19'),
(22, '098000', 'phamvantuan@gmail.com', 'tuan', 'pham van tuan', '1998-02-26', 1, 1, '2020-02-26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `id` int(11) NOT NULL,
  `tendangnhap` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `matkhau` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `hoten` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `diachi` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `sodienthoai` varchar(15) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `anhdaidien` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `trangthai` tinyint(4) NOT NULL DEFAULT 1,
  `quyen` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `tendangnhap`, `matkhau`, `email`, `hoten`, `ngaysinh`, `diachi`, `sodienthoai`, `gioitinh`, `anhdaidien`, `trangthai`, `quyen`) VALUES
(3, 'nhanvien2', 'nhanvien2', 'nhanvien2@gmail.com', 'Nhân viên 2', NULL, NULL, NULL, 0, NULL, 1, 0),
(4, 'thuthuy123', 'thuy123', 'thuthuy123@gmail.com', 'Phạm Thị Thu Thủy', '1998-10-26', 'Phú Xuyên - Hà Nội', '099999', 0, 'tiger.jpg', 1, 0),
(5, 'nguyenthu', 'thu123', 'nguyenthu@gmail.com', 'Nguyễn Thị Thu', '1998-01-01', 'Bắc Ninh', '09765456', 0, NULL, 1, 0),
(13, 'nv1', 'nv1', 'nv1@gmail.com', 'Đinh Thanh Tuân', '1998-09-18', 'Gia Viễn - Ninh Bình', '0342582266', 1, NULL, 1, 0),
(14, 'nv2', 'nv2`', 'nv2@gmail.com', 'Nhân viên 2', NULL, 'Hải Phòng', '034252324', 0, NULL, 1, 0),
(15, 'nv3', 'nv3', 'nv3@gmail.com', 'Nhân viên 3', '2000-01-03', 'Hà Nội', '03466342', 1, NULL, 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `ten` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenkhongdau` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `gia` int(15) DEFAULT NULL,
  `hinh` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `mota` text COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `thongsochitiet` text COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `albumhinh` text COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `iddanhmuc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `ten`, `tenkhongdau`, `gia`, `hinh`, `mota`, `thongsochitiet`, `albumhinh`, `iddanhmuc`) VALUES
(1, 'Truyện tranh Mắt Biếc', 'truyen-tranh-mat-biec', 70000, 'sp-truyen-mat-biec.jpg', 'Mắt biếc là một tác phẩm được nhiều người bình chọn là hay nhất của nhà văn Nguyễn Nhật Ánh. Tác phẩm này cũng đã được dịch giả Kato Sakae dịch sang tiếng Nhật để giới thiệu với độc giả Nhật Bản. \r\n\r\n“Tôi gửi tình yêu cho mùa hè, nhưng mùa hè không giữ nổi. Mùa hè chỉ biết ra hoa, phượng đỏ sân trường và tiếng ve nỉ non trong lá. Mùa hè ngây ngô, giống như tôi vậy. Nó chẳng làm được những điều tôi ký thác. Nó để Hà Lan đốt tôi, đốt rụi. Trái tim tôi cháy thành tro, rơi vãi trên đường về.”\r\n\r\n… Bởi sự trong sáng của một tình cảm, bởi cái kết thúc buồn, rất buồn khi xuyên suốt câu chuyện vẫn là những điều vui, buồn lẫn lộn …', '[{\"Tác giả\" : \"Paul Kalanithi\"}, {\"Số trang\" : \"236\"}]', '', 11),
(2, 'Xe máy Honda Winner', 'Xe may Honda Winner', 45000000, 'avt-xe-co.png', 'Xe Máy Honda Winner X vừa ra mắt thị trường Việt Nam. Đây là bản nâng cấp đáng chú ý với tham vọng lật đổ Yamaha Exciter. Phân khúc xe côn tay phổ thông trở thành của “sân nhà” của Yamaha nói chung và của Yamaha Exciter nói riêng trong nhiều năm, cho tới khi có sự xuất hiện của Honda Winner.\r\n\r\nHonda Winner ra mắt lần đầu thị trường Việt Nam vào năm 2016, và có khởi đầu khá chậm chạp, chưa thực sự tạo nên sức ép cho đối thủ Exciter. Winner khi ấy bị coi là có thiết kế quá phổ thông và không “chất” như những gì Exciter mang lại.', '[{\"Thương hiệu\":\"Honda\"},{\"Xuất xứ thương hiệu\":\"Nhật Bản\"},{\"Nơi sản xuất\":\"Việt Nam\"}]', NULL, 9),
(3, 'Cô gái trong trang sách', 'co-gai-trong-trang-sach', 84000, 'sp-co-gai-trong-trang-sach.jpg', 'Đây là sách Cô Gái Trong Trang Sách', '[{\"Tác giả\" : \"Paul Kalanithi\"}, {\"Số trang\" : \"236\"}]', '', 11),
(4, 'Khi hơi thở hóa thinh không', 'khi-hoi-tho-hoa-thinh-khong', 74120, 'sp-khi-hoi-tho-hoa-thinh-khong.jpg', 'Khi Hơi Thở Hóa Thinh Không là tự truyện của một bác sĩ bị mắc bệnh ung thư phổi. Trong cuốn sách này, tác giả đã chia sẻ những trải nghiệm từ khi mới bắt đầu học ngành y, tiếp xúc với bệnh nhân cho tới khi phát hiện ra mình bị ung thư và phải điều trị lâu dài.\r\n\r\nKalanithi rất yêu thích văn chương nên câu chuyện của anh đã được thuật lại theo một phong cách mượt mà, dung dị và đầy cảm xúc. Độc giả cũng được hiểu thêm về triết lý sống, triết lý nghề y của Kalanithi, thông qua ký ức về những ngày anh còn là sinh viên, rồi thực tập, cho đến khi chính thức hành nghề phẫu thuật thần kinh. “Đối với bệnh nhân và gia đình, phẫu thuật não là sự kiện bi thảm nhất mà họ từng phải đối mặt và nó có tác động như bất kỳ một biến cố lớn lao trong đời. Trong những thời điểm nguy cấp đó, câu hỏi không chỉ đơn thuần là sống hay chết mà còn là cuộc sống nào đáng sống.” – Kalanithi luôn biết cách đưa vào câu chuyện những suy nghĩ sâu sắc và đầy sự đồng cảm như thế.\r\n\r\nBạn bè và gia đình đã dành tặng những lời trìu mến nhất cho con người đáng kính trọng cả về tài năng lẫn nhân cách này. Dù không thể vượt qua cơn bệnh nan y, nhưng thông điệp của tác giả sẽ còn khiến người đọc nhớ mãi.', '[{\"Công ty phát hành\" : \"Omega Plus\"}, {\"Tác giả\" : \"Paul Kalanithi\"}, {\"Ngày xuất bản\": \"07-2017\"}, \r\n{\"Kích thước\": \"14 x 20.5 cm\"}, {\"Dịch Giả\": \"Trần Thanh Hương\"}, {\"Loại bìa\": \"Bìa mềm\"}, \r\n{\"Số trang\" : \"236\"}, {\"Nhà xuất bản\": \"Nhà Xuất Bản Lao Động\"}]', '', 11),
(5, 'Samsung Galaxy A51', 'Samsung Galaxy A51', 7990000, 'ss-a51-xanh-1.png', 'Tự hào là smartphone đầu tiên trên thế giới được tích hợp camera Macro hỗ trợ chụp ảnh cận cảnh, Galaxy A51 đem tới trải nghiệm chụp hình vượt ngoài mong đợi với khả năng tái tạo đến từng chi tiết nhỏ nhất. Ngoài ra, sự ưu việt của màn hình vô cực kết hợp với cấu hình mạnh mẽ và viên pin lớn 4.000 mAh sạc siêu nhanh khiến Galaxy A51 có thể làm vừa lòng bất cứ ai ngay từ lần trải nghiệm đầu tiên.', '[{\"Màn hình\":\"6.5 inchs, Full HD+ (1080 x 2400 Pixels), 1080 x 2340 Pixels\"},\r\n	{\"Camera trước\":\"32.0Mp\"},{\"RAM\":\"6 GB\"},{\"Bộ nhớ trong\":\"128 GB\"},{\"CPU\":\"Exynos 9611, 8, 4 nhân 2.3 Ghz & 4 nhân 1.7 GHz\"},\r\n	{\"GPU\":\"Mali-G72 MP3\"},{\"Dung lượng pin\":\"4000mAh\"},{\"Hệ điều hành\":\"Android 10\"},{\"Thẻ SIM\":\"Nano SIM, 2 Sim\"},\r\n	{\"Xuất xứ\":\"Việt Nam\"},{\"Năm sản xuất\":\"2019\"}]', NULL, 1),
(6, 'Điện thoại iPhone 11 64GB', 'Dien thoai iPhone 11 64GB', 21990000, 'iphone-11-tet-400x460-400x460.png', 'Sau bao nhiêu chờ đợi cũng như đồn đoán thì cuối cùng Apple đã chính thức giới thiệu bộ 3 siêu phẩm iPhone 11 mạnh mẽ nhất của mình vào tháng 9/2019. Có mức giá rẻ nhất nhưng vẫn được nâng cấp mạnh mẽ như chiếc iPhone Xr năm ngoái, đó chính là phiên bản iPhone 11 64GB.\r\nNâng cấp mạnh mẽ về camera\r\nNói về nâng cấp thì camera chính là điểm có nhiều cải tiến nhất trên thế hệ iPhone mới.', '[{\"Công nghệ màn hình\":\"IPS LCD\"},{\"Độ phân giải\":\"828 x 1792 Pixels\"},{\"Màn hình rộng\":\"6.1\"},{\"Mặt kính cảm ứng\":\"Kính cường lực oleophobic (ion cường lực)\"}]', NULL, 1),
(7, 'Laptop Apple Macbook Air 2019 i5 1.6GHz/8GB/128GB (MVFM2SA/A)\r\n', 'Laptop Apple Macbook Air 2019 i5 1.6GHz/8GB/128GB (MVFM2SA/A)', 28990000, 'apple-macbook-air-2019-i5-16ghz-8gb-128gb-mvfm2sa-13-32-600x600.jpg', NULL, NULL, NULL, 1),
(8, 'Samsung Galaxy Fold', 'Samsung Galaxy Fold', 50000000, 'samsung-galaxy-fold-black-400x460.png', 'Sau rất nhiều chờ đợi thì Samsung Galaxy Fold - chiếc smartphone màn hình gập đầu tiên của Samsung cũng đã chính thức trình làng với thiết kế mới lạ.\r\nThiết kế 2 màn hình, màn hình uốn dẻo\r\nSamsung Galaxy Fold không chỉ sở hữu một màn hình có thể uốn dẻo mà còn có một màn hình riêng, để có thể sử dụng độc lập khi gập máy lại.\r\n\r\n', NULL, NULL, 1),
(9, 'Điện thoại Samsung Galaxy S10+ (512GB)', 'Dien thoai Samsung Galaxy S10+ (512GB)', 18990000, 'sieu-pham-galaxy-s-moi-2-512gb-black-400x460.png', 'Samsung Galaxy S10+ 512GB - phiên bản kỷ niệm 10 năm chiếc Galaxy S đầu tiên ra mắt, là một chiếc smartphone hội tủ đủ các yếu tố mà bạn cần ở một chiếc máy cao cấp trong năm 2019.\r\nKhác biệt tới từ màn hình Infinity-O\r\nSamsung Galaxy S10+ (512GB) đi theo kiểu thiết kế màn hình Infinity-O với phần camera được đặt phía trong màn hình rất độc đáo.', NULL, NULL, 1),
(10, 'Điện thoại iPhone 8 Plus 64GB', 'Dien thoai iPhone 8 Plus 64GB', 15990000, 'iphone-8-plus-tet-400x460-400x460.png', 'Thừa hưởng những thiết kế đã đạt đến độ chuẩn mực, thế hệ iPhone 8 Plus thay đổi phong cách bóng bẩy hơn và bổ sung hàng loạt tính năng cao cấp cho trải nghiệm sử dụng vô cùng tuyệt vời.Thiết kế từ kính và kim loại Smartphone iPhone 8 Plus giữ nguyên hoàn toàn những đường nét thiết kế đã hoàn thiện từ thế hệ trước nhưng sử dụng phong cách 2 mặt kính cường lực kết hợp bộ khung kim loại.', NULL, NULL, 1),
(11, 'Điện thoại BlackBerry KEY2', 'Dien thoai BlackBerry KEY2', 15990000, 'blackberry-key2-3-400x460.png', 'BlackBerry Key2 là một bản nâng cấp toàn diện cho chiếc KeyOne với rất nhiều thay đổi và điều đáng mừng là yếu tố đặc trưng nhất của dòng điện thoại BlackBerry là bàn phím vật lý cổ điển vẫn được giữ lại.\r\nThiết kế sang chảnh\r\nThiết kế của những chiếc máy BlackBerry luôn được người dùng đánh giá cao bởi chất doanh nhân của máy.', NULL, NULL, 1),
(12, 'Quạt Đứng Mitsubishi LV16-GV CY-GY', 'Quat Dung Mitsubishi LV16-GV CY-GY', 1369000, 'quat-dung-hoi-nuoc.jpg', 'Đường kính cánh 40cm\r\nCông Suất 46W\r\nLưu lượng gió 75 m3/min\r\n3 cấp độ vận hành\r\nChế độ gió tự nhiên\r\nĐộng cơ nhỏ gọn, êm ái, an toàn\r\nChiều cao điều chỉnh được\r\nCó bánh xe di chuyển tiện lợi\r\nNgắt điện an toàn khi quá tải\r\nSản phẩm không có remote', NULL, NULL, 2),
(13, 'Máy Ép Chậm Panasonic', 'May Ep Cham Panasonic', 5168000, '1450150378_may-ep-cham-panasonic-mj-l500.jpg', NULL, NULL, NULL, 2),
(14, 'Khô gà lá chanh chất lượng Tân Lộc Phát', 'Kho ga la chanh chat luong Tan Loc Phat', 120000, 'ga-kho-la-chanh.jpg', 'Xuất xứ: Việt Nam\r\nHSD: 6 tháng từ ngày sản xuất.\r\nĐảm bảo vệ sinh an toàn thực phẩm, thương hiệu nhãn mác và ngày sản xuất rõ ràng', NULL, NULL, 3),
(15, 'Boardgame Lớp Học Mật Ngữ - Cuộc Đua Sao Chổi', 'Boardgame Lop Hoc Mat Ngu - Cuoc Dua Sao Choi', 440000, 'do-choi-ma-thuat.jpg', 'Boardgame Hot nhất năm 2018\r\n\r\nChuyển thể từ bộ truyện tranh nổi tiếng cùng tên TOP 1 Best Seller Fahasa 2016-2018\r\n\r\nChơi từ 2 - 6 người, thích hợp cho nhóm bạn thân lầy lội\r\n\r\nThiết kế siêu đẹp, dễ thương và đặc sắc được sáng tạo từ chính nhóm tác giả B.R.O\r\nDễ chơi mà vui bá cháy bằng cách Nhập vai cung hoàng dạo của bạn giải cứu hành tinh cầu vồng xinh đẹp\r\nNgười chơi chiến thắng khi lấy được nhiều Thùng đồ tiếp tế nhất.\r\n\r\nDi chuyển bằng cách tung xúc xắc, Sử dụng các lá chức năng để chiến thắng hoặc ngăn cản người chơi khác chiến thắng\r\nTuổi: từ 10 tuổi trở lên\r\n\r\nThời gian chơi: 30p', NULL, NULL, 4),
(16, 'Son Bút Chì 3 Trong 1 Cho Bờ Môi Mềm Mượt Mamonde Creamy Tint Color Balm Intense (2.5g)', 'Son But Chi 3 Trong 1 Cho Bo Moi Mem Muot Mamonde Creamy Tint Color Balm Intense (2.5g)', 220000, 'son-but-chi.jpg', 'Sự kết hợp 3 trong 1 giữa: son thỏi, son tint và son dưỡng\r\n\r\nMàu sắc chuẩn xác, mịn mượt như nhung\r\n\r\n20 tông màu cho bạn thoả thích lựa chọn', NULL, NULL, 5),
(17, 'Túi Đeo Chéo Nam HARAS', 'Tui Deo Cheo Nam HARAS', 35000, 'tui-deo-cheo.jpg', 'Thiết kế trẻ trung, năng động\r\n\r\nKích thước nhỏ gọn, tiện lợi\r\n\r\nChất liệu vải oxford dày dặn, độ bền cao\r\n\r\nThích hợp mang đi học, chơi thể thao', NULL, NULL, 6),
(18, 'Máy Tính Khoa Học Casio FX-580VN X', 'May Tinh Khoa Hoc Casio FX-580VN X', 565000, 'may-tinh-casio.jpg', 'Tính năng đột phá với 521 tính năng, vượt trội so với 453 tính năng dòng FX570VNP\r\n\r\nMàn hình LCD độ phân giải cao, hiển thị đầy đủ phép tính\r\n\r\nChất liệu bề mặt cao cấp, vân nổi 3D hiện đại, trẻ trung\r\n\r\nThiết kế riêng theo yêu cầu của học sinh và giáo viên Việt Nam\r\n\r\nPhù hợp với hình thức thi trắc nghiệm hiện nay\r\n\r\nGiao diện 2 ngôn ngữ: Anh - Việt\r\n\r\nTốc độ xử lý nhanh gấp 6 lần so với FX570VNPLUS\r\n\r\nLà sản phẩm duy nhất có tại Việt Nam và dành riêng cho thị trường Việt Nam\r\n\r\nĐược bộ GDĐT cho phép mang vào phòng thi theo công văn số 1568/BGDĐT-CNTT ngày 19/04/2018', NULL, NULL, 7),
(19, 'Máy Ảnh Sony Alpha A6000 + 16-50mm', 'May Anh Sony Alpha A6000 + 16-50mm', 11990000, 'may-anh-sony.jpeg', 'Cảm biến: APS HD CMOS 24.3MP\r\n\r\nBộ xử lý hình ảnh: BIONZ X\r\n\r\nISO: 100 - 25600\r\n\r\nHệ thống lấy nét: 25 điểm\r\n\r\nTốc độ lấy nét cực nhanh 0.06 giây\r\n\r\nChụp ảnh liên tục: 11 ảnh/ giây\r\n\r\nChuyển hình nhanh với kêt nối Wifi', NULL, NULL, 8),
(20, 'Hộp túi chống sốc đa năng đựng phụ kiện công nghệ cáp sạc, tai nghe, sạc dự phòng, mỹ phẩm', 'Hop tui chong soc da nang dung phu kien cong nghe cap sac, tai nghe, sac du phong, mỹ pham', 39900, 'tui-dung-phu-kien.jpg', 'Phù hợp để lưu trữ các phụ kiện công nghệ: cáp sạc, tai nghe, sạc dự phòng, mỹ phẩm ..\r\n\r\nChất liệu vải dày dặn nhiều lớp và thiết kế chuyên dụng để chống sốc\r\n\r\nBên trong túi có tích hợp lớp túi lưới giúp lưu trữ tốt hơn\r\n\r\nChất liệu nhẹ, đường chỉ may chắc chắn tinh xảo, chống thấm nước, chống sốc\r\n\r\nThiết kế tối giản, nhỏ gọn, dễ dàng mang theo mọi nơi\r\n\r\nKích thước bên ngoài túi: 220mm x 150mm', NULL, NULL, 10),
(21, 'SP thuộc DM5', 'sp-thuoc-dm5', 1345634, 'avt-do-cong-nghe.jpg', 'Mô tả', NULL, '', 19);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `danhmuc`
--
ALTER TABLE `danhmuc`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `danhmuc`
--
ALTER TABLE `danhmuc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
