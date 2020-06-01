<?php 
	include "connect.php";

    // $soDienThoai = "035";
    // $email = "tuan5@gmail.com";
    // $matKhau = "tuan4";
    // $hoTen = "Tuân 5";
    // $ngaySinh = "1998-01-09";
    // $gioiTinh = 1;
    // $nhanThongBao = 0;
    // $ngayTao = "2020-01-26";
    
	$soDienThoai = $_POST['soDienThoai'];
    $email = $_POST['email'];
    $matKhau = $_POST['matKhau'];
    //$matKhau = md5($_POST['matKhau']);

    $hoTen = $_POST['hoTen'];
    $ngaySinh = $_POST['ngaySinh'];
    $gioiTinh = (int)$_POST['gioiTinh'];
    $nhanThongBao = (int)$_POST['nhanThongBao'];
    $ngayTao = $_POST['ngayTao'];


	//Nhận dữ liệu từ Client gửi
	$query = "INSERT INTO khachhang(sodienthoai, email, matkhau, hoten, ngaysinh, gioitinh, nhanthongbao, ngaytao) 
				VALUES ('$soDienThoai', '$email', '$matKhau', '$hoTen', '$ngaySinh', $gioiTinh, $nhanThongBao, '$ngayTao')";

	if(mysqli_query($conn, $query)){
		$idKhachHang = $conn->insert_id;
		echo json_encode($idKhachHang);
	} else{
		echo json_encode(-1);
	}

	//echo json_encode(-1);
 ?>