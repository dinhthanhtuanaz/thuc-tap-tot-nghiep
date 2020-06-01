<?php 
	include "connect.php";

	$email = $_POST['email'];

	$query = "SELECT * FROM khachhang WHERE email = '$email'";
	$data = mysqli_query($conn, $query);

	if(mysqli_num_rows($data) > 0){
		//Tồn tại
		echo json_encode(1);
	} else{
		//Không tồn tại
		echo json_encode(0);
	}
?>