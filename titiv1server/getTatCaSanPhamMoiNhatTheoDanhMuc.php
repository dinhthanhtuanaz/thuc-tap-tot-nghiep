<?php 
	include "connect.php";
	//SELECT * FROM sanpham WHERE iddanhmuc = 9
	$idDanhMuc = (int)$_POST['idDanhMuc'];
	//SELECT * FROM sanpham WHERE iddanhmuc = 11 order by id DESC limit 0, 10

	$query = "SELECT * FROM sanpham WHERE iddanhmuc = $idDanhMuc ORDER BY id DESC LIMIT 0, 10";
	$data = mysqli_query($conn, $query);
	$arrSanPham = array();
	if(mysqli_num_rows($data) > 0){
		

		while($row = mysqli_fetch_assoc($data)){
			$obj = new SanPham($row['id'], $row['ten'], $row['gia'], $row['hinh'],
								$row['mota'], $row['thongsochitiet'], 
								$row['albumhinh'], $row['iddanhmuc']);
			array_push($arrSanPham, $obj);
		}

		echo json_encode($arrSanPham);
	} else{
		echo json_encode($arrSanPham);
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