@extends('layouts.master')
@section('main')
@php
@endphp

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
        {{-- <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
            <a href="{{ url('admin/don-hang/chi-tiet-don-hang') }}" class="btn btn-primary float-right">Thêm</a>
        </div> --}}
    </div>
</div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th scope="col" class="width-45">#</th>
                <th scope="col">Tên khách hàng</th>
                <th scope="col">Số điện thoại</th>
                <th scope="col">Email</th>
                <th scope="col">Ngày sinh</th>
                <th scope="col">Giới tính</th>
                <th scope="col">Nhận thông báo</th>
                <th scope="col">Ngày tham gia</th>
                <th scope="col" class="" style="width:200px;">Thao tác</th>
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

                foreach ($customers as $key => $value) {
                ?>
                    <tr>
                    <th scope="row">{{$index++}}</th>
                    <td>{{$value->hoten}}</td>
                    <td>{{$value->sodienthoai}}</td>
                    <td>{{$value->email}}</td>
                    <td>{{date('d-m-Y', strtotime($value->ngaysinh))}}</td>
                    <td>
                        {{$value->gioitinh == env('GENDER_MEN') ? "Nam" : "Nữ"}}
                    </td>
                    <td>{{$value->nhanthongbao == 1 ? "Có" : "Không"}}</td>
                    <td>{{date('d-m-Y', strtotime($value->ngaytao))}} </td>
                    <td>
                    <a class="" href="{{ url('admin/khach-hang') . "/" . $value->id }}/san-pham-da-mua">Sản phẩm đã mua</a>
                    </td>
                    </tr>
                <?php
                }
            ?>
        </tbody>
    </table>
    <div class="row ">
        <div class="col-12 pagination-center">
            {{$customers->links()}}
        </div>
    </div>
</div>
@stop
