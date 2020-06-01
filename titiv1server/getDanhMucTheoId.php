<?php 
	include "connect.php";

	$idDanhMuc = $_POST['idDanhMuc'];
	$query = "SELECT * FROM danhmuc WHERE id = $idDanhMuc";
	$data = mysqli_query($conn, $query);

	if(mysqli_num_rows($data) > 0){
		$obj = mysqli_fetch_object($data);

		echo json_encode(new DanhMuc($obj->id, $obj->tendanhmuc, $obj->hinhnen, $obj->mota));

	} else{
		echo json_encode((object)null);
	}

	class DanhMuc{
		function DanhMuc($id, $tenDanhMuc, $hinhNen,$moTa){
			$this->id = $id;
	        $this->tenDanhMuc = $tenDanhMuc;
	        $this->hinhNen = $hinhNen;
	        $this->moTa = $moTa;
		}
	}
?>