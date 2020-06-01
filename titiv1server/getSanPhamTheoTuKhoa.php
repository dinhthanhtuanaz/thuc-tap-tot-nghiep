<?php 
	include "connect.php";
	//SELECT * FROM sanpham WHERE iddanhmuc = 9
	$keyWord = $_POST['keyWord']; // $_POST['keyWord'];

	$query = "SELECT * FROM `sanpham` WHERE tenkhongdau like '%$keyWord%'";
	$data = mysqli_query($conn, $query);

	$arrSanPham = array();
	while($row = mysqli_fetch_assoc($data)){
		$obj = new SanPham($row['id'], $row['ten'],$row['gia'], $row['hinh']);
		array_push($arrSanPham, $obj);
	}

	echo json_encode($arrSanPham);


	class SanPham{
		function SanPham($id, $ten, $gia, $hinh){
			$this->id = $id;
	        $this->ten = $ten;
	        $this->gia = $gia;
	        $this->hinh = $hinh;
		}
	}
?>