<?php 
	include "connect.php";

	$query = "SELECT * FROM danhmuc";
	$data = mysqli_query($conn, $query);
	$arrDanhMuc = array();

	while($row = mysqli_fetch_assoc($data)){
		$danhMuc = new DanhMuc($row['id'], $row['tendanhmuc'], $row['hinhnen'], $row['mota']);
		array_push($arrDanhMuc, $danhMuc);
	}

	echo json_encode($arrDanhMuc);

	class DanhMuc{
		function DanhMuc($id, $tenDanhMuc, $hinhNen,$moTa){
			$this->id = $id;
	        $this->tenDanhMuc = $tenDanhMuc;
	        $this->hinhNen = $hinhNen;
	        $this->moTa = $moTa;
		}
	}
?>