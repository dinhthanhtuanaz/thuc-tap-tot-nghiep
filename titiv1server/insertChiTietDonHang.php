<?php 
	include "connect.php";
	//type is JSON

	$dataFromClient = $_POST['strChiTietDH'];

	// $dataFromClient = '[{"gia":74120,"hinhSP":"sp-khi-hoi-tho-hoa-thinh-khong.jpg","idDH":10,"idSP":4,"soLuong":1,"tenSP":"Khi hơi thở hóa thinh không"},{"gia":45000000,"hinhSP":"avt-xe-co.png","idDH":10,"idSP":2,"soLuong":1,"tenSP":"Xe máy Honda Winner"}]';
	//echo $dataFromClient;
	$arrChiTietDH = json_decode($dataFromClient, true);
	foreach ($arrChiTietDH as $key => $value) {
		$idSP = $value['idSP'];
		$idDH = $value['idDH'];
		$tenSP = $value['tenSP'];
		$hinhSP = $value['hinhSP'];
		$gia = $value['gia'];
		$soLuong = $value['soLuong'];

		$query = "INSERT INTO chitietdonhang(idsanpham,iddonhang,tensanpham,hinhsanpham,soluong,gia)
					VALUES ($idSP, $idDH, '$tenSP', '$hinhSP', $soLuong, $gia)";
		$data = mysqli_query($conn, $query);
	}

	if($data){
		echo "1";
	} else{
		echo "0";
	}
?>