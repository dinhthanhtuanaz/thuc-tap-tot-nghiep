<?php 
	include "connect.php";

	$idKhachHang = $_POST['idKhachHang']; // $_POST['idKhachHang'];

	$query = "SELECT distinct idsanpham, tensanpham, hinhsanpham, soluong, gia from khachhang, donhang, chitietdonhang where khachhang.id = donhang.idkhachhang and donhang.id = chitietdonhang.iddonhang and khachhang.id = $idKhachHang";
	$data = mysqli_query($conn, $query);
	$arrSanPhamDaMua = array();
	while($row = mysqli_fetch_assoc($data)){
		$obj = new SanPhamDaMua($row['idsanpham'], $row['tensanpham'], $row['hinhsanpham'], $row['gia']/$row['soluong'] . "");
		array_push($arrSanPhamDaMua, $obj);
	}

	echo json_encode($arrSanPhamDaMua);

	class SanPhamDaMua{
		function SanPhamDaMua($id, $tensp, $hinhsp, $giamotsp){
			$this->id = $id;
			$this->tenSP = $tensp;
			$this->hinhSP = $hinhsp;
			$this->giaMotSP = $giamotsp;
		}
	}
?>