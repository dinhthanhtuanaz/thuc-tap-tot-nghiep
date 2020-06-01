<?php 
	/*Trả về JSON gồm các danh mục kèm sản phẩm theo từng danh mục. Optimal Class*/
	include "connect.php";
	$query = "SELECT * FROM danhmuc";
	$data = mysqli_query($conn, $query);
	$arrResult = array();

	while($row = mysqli_fetch_assoc($data)){
		

		$query2 = "SELECT * FROM sanpham WHERE iddanhmuc = " . $row['id'];
		$data2 = mysqli_query($conn, $query2);
		$arrSanPham = array();
		while($row2 = mysqli_fetch_assoc($data2)){
			$sanPham = new SanPham($row2['id'], $row2['ten'], $row2['gia'], $row2['hinh']);
			array_push($arrSanPham, $sanPham);
		}


		$danhMuc = new DanhMuc($row['id'], $row['tendanhmuc'], $row['hinhnen'], $arrSanPham);
		array_push($arrResult, $danhMuc);
	}


	echo json_encode($arrResult);

	class DanhMuc{
		function DanhMuc($id, $tenDanhMuc, $hinhNen, $arrSanPham){
			$this->id = $id;
	        $this->tenDanhMuc = $tenDanhMuc;
	        $this->hinhNen = $hinhNen;
	        $this->arrSanPham = $arrSanPham;
		}
	}

	class SanPham{
		function SanPham($id, $ten, $gia, $hinh){
			$this->id = $id;
	        $this->ten = $ten;
	        $this->gia = $gia;
	        $this->hinh = $hinh;
		}
	}
?>