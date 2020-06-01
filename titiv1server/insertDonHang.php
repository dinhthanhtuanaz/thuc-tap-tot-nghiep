<?php 
	include "connect.php";

	$idKhachHang = $_POST['idKhachHang'];
	$soDienThoai = $_POST['soDienThoai'];
	$diaChiGiao = $_POST['diaChiGiao'];
	$ngayMua = $_POST['ngayMua'];
	$ghiChu = $_POST['ghiChu'];

	$query = "INSERT INTO donhang(idkhachhang,sodienthoai,diachigiao,ngaymua,ghichu)
	         VALUES ($idKhachHang, '$soDienThoai', '$diaChiGiao','$ngayMua', '$ghiChu')";
	if(mysqli_query($conn, $query)){
		$idDonHang = $conn->insert_id;
		echo $idDonHang;
	} else{
		echo "Thất bại";
	}
 ?>