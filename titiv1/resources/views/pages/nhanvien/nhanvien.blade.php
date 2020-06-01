{{-- Phân quyền, chỉ ADMIN mới được vào đây --}}
@extends('layouts.master')
@section('main')
<?php
//Nếu không là Admin
if(session("USERNAME") != env("AD_USERNAME")){
    //Chuyển hướng đến danh sách SP
    header("Location: " . URL::to('/admin/san-pham'), true, 302);
    exit();
}

//print_r($employees);
?>

<div id="wrap-content">

    @if ( Session::has('success') )
	<div class="alert alert-success alert-dismissible" role="alert">
		<strong>{{ Session::get('success') }}</strong>
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
			<span class="sr-only">Close</span>
		</button>
	</div>
@endif

<div class="wrap-content_head">
    <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
            <div class="breadcome-heading">
                <form role="search" class="sr-input-func" id="search-form" method="POST" action="">
                    <input type="text" placeholder="Tìm kiếm..." class="search-int form-control" style="background:#fff">
                    <button type="submit"><i class="fa fa-search"></i></button>
                </form>
            </div>
        </div>
        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
            <a href="{{ url('admin/nhan-vien/them') }}" class="btn btn-primary float-right">Thêm</a>
        </div>
    </div>
</div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th scope="col" class="width-45">#</th>
                <th scope="col">Tên đăng nhập</th>
                <th scope="col">Email</th>
                <th scope="col">Họ tên</th>
                <th scope="col">Số điện thoại</th>
                <th scope="col">Giới tính</th>
                <th scope="col">Trạng thái</th>
                <th scope="col" class="width-110">Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <?php
                $index=1;
                if(isset($_GET["page"])){
                    $page = $_GET["page"];
                    $index = (($page-1) * env('ITEMPERPAGE'))+1;
                } else{
                    //Tức đang page 1 đấy
                }

                foreach ($employees as $key => $value) {
                ?>
                    <tr>
                    <th scope="row">{{$index++}}</th>
                    <td>{{$value->tendangnhap}}</td>
                    <td>{{$value->email}}</td>
                    <td>{{$value->hoten}}</td>
                    <td>{{$value->sodienthoai}}</td>
                    <td>
                        {{$value->gioitinh == env('GENDER_MEN') ? "Nam" : "Nữ"}}
                    </td>
                    <td>{{$value->trangthai == 1 ? "Hoạt động" : "Khóa"}}</td>
                    <td>
                        <a href="{{ url('admin/nhan-vien') . "/" . $value->id }}/sua" class="btn-custom-1">
                            <i class="far fa-edit text-warning fs-24"></i>
                        </a>
                        <a href="{{ url('admin/nhan-vien') . "/" . $value->id }}/xoa" class="btn-custom-1"
                            onclick="return confirm('Bạn có chắc chắn xóa không ?')">
                            <i class="far fa-trash-alt fs-24 text-danger"></i>
                        </a>
                    </td>
                    </tr>
                <?php
                }
            ?>
        </tbody>
    </table>
    <div class="row ">
        <div class="col-12 pagination-center">
            {{$employees->links()}}
        </div>
    </div>
</div>
@stop


