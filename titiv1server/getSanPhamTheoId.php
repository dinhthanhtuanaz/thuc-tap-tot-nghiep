<?php 
	include "connect.php";
	//SELECT * FROM sanpham WHERE iddanhmuc = 9
	$id = $_POST['id'];
	$query = "SELECT * FROM sanpham WHERE id = $id";
	$data = mysqli_query($conn, $query);

	if(mysqli_num_rows($data) > 0){
		$obj = mysqli_fetch_object($data);
		echo json_encode(new SanPham($obj->id, $obj->ten, $obj->gia, $obj->hinh,
								$obj->mota, $obj->thongsochitiet, 
								$obj->albumhinh, $obj->iddanhmuc));
	} else{
		echo json_encode((object)null);
	}

	class SanPham{
		function SanPham($id, $ten, $gia, $hinh, $moTa,
                   $thongSoChiTiet, $albumHinh, $idDanhMuc){
			$this->id = $id;
	        $this->ten = $ten;
	        $this->gia = $gia;
	        $this->hinh = $hinh;
	        $this->moTa = $moTa;
	        $this->thongSoChiTiet = $thongSoChiTiet;
	        $this->albumHinh = $albumHinh;
	        $this->idDanhMuc = $idDanhMuc;
		}
	}
?>