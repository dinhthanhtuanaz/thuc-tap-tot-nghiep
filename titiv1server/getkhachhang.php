<?php 
	include "connect.php";

	$taiKhoan = $_POST['taiKhoan'];
	//$matKhau = $_POST['matKhau'];
	//$matKhau = md5($_POST['matKhau']);
	$matKhau = $_POST['matKhau'];
	// $taiKhoan = "tuan2@gmail.com";
	// $matKhau = "tuan2";

	$query = "SELECT * FROM khachhang WHERE email = '$taiKhoan' AND matkhau = '$matKhau'";
	$data = mysqli_query($conn, $query);

	if(mysqli_num_rows($data) > 0){
		$obj = mysqli_fetch_object($data);
		//echo $obj->id;

		//echo json_encode($obj->hoten);

		echo json_encode(new KhachHang($obj->id, $obj->sodienthoai, $obj->email, $obj->matkhau,
								        $obj->hoten, $obj->gioitinh, $obj->nhanthongbao, $obj->ngaysinh,
								        $obj->ngaytao));

		
	} else {
		// $obj = "{}";
		//echo json_encode((object)null);
		echo json_encode((object)null);
	}
	
	class KhachHang{
		function KhachHang($id,$soDienThoai, $email, $matKhau, $hoTen, $gioiTinh, 
				$nhanThongBao, $ngaySinh, $ngayTao) {
	        $this->id = $id;
	        $this->soDienThoai = $soDienThoai;
	        $this->email = $email;
	        $this->matKhau = $matKhau;
	        $this->hoTen = $hoTen;
	        $this->gioiTinh = $gioiTinh;
	        $this->nhanThongBao = $nhanThongBao;
	        $this->ngaySinh = $ngaySinh;
	        $this->ngayTao = $ngayTao;
    	}
	}
	

	//Dưới đây là trả về cũng 1 User nhưng nằm trong Array => Thừa thãi.
	// $query = "SELECT * FROM khachhang WHERE email = '$taiKhoan' AND matkhau = '$matKhau'";
	// $data = mysqli_query($conn, $query);
	// $arrKhachHang = array();

	// while($row = mysqli_fetch_assoc($data)){
	// 	array_push($arrKhachHang, 
	// 			new KhachHang($row['id'], $row['sodienthoai'], $row['email'], $row['matkhau'], $row['hoten'], $row['ngaysinh'], 
	// 				$row['gioitinh'], $row['nhanthongbao'], $row['ngaytao']));
	// }
	// echo json_encode($arrKhachHang);
	// class KhachHang{
	// 	function KhachHang($id,$soDienThoai, $email, $matKhau, $hoTen, $gioiTinh, 
	// 			$nhanThongBao, $ngaySinh, $ngayTao) {
	//         $this->id = $id;
	//         $this->soDienThoai = $soDienThoai;
	//         $this->email = $email;
	//         $this->matKhau = $matKhau;
	//         $this->hoTen = $hoTen;
	//         $this->gioiTinh = $gioiTinh;
	//         $this->nhanThongBao = $nhanThongBao;
	//         $this->ngaySinh = $ngaySinh;
	//         $this->ngayTao = $ngayTao;
 //    	}
	// }
	
?>