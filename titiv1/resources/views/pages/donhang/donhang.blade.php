@extends('layouts.master')
@section('main')
@php
    //echo "Chào các bạn";
    //print_r($donHangs);
    // $data = json_decode($data);
    // print_r($data);
    // $donHangs = $data->data;
    // print_r($donHangs);
    // foreach ($donHangs as $key => $value) {
    //     echo $value->id;
    // }
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
                <th scope="col">Địa chỉ giao</th>
                <th scope="col">Ngày mua</th>
                <th scope="col">Ghi chú</th>
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

                foreach ($orders as $key => $value) {
                ?>
                    <tr>
                    <th scope="row">{{$index++}}</th>
                    <td>{{$value->hoten}}</td>
                    <td>{{$value->sodienthoai}}</td>
                    <td>{{$value->diachigiao}}</td>
                    <td>{{$value->ngaymua}}</td>
                    <td>{{$value->ghichu}}</td>
                    <td>
                    <a class="btn btn-primary" href="{{ url('admin/don-hang') . "/" . $value->id }}/chi-tiet">Chi tiết</a>
                    </td>
                    </tr>
                <?php
                }
            ?>
        </tbody>
    </table>
    <div class="row ">
        <div class="col-12 pagination-center">
            {{$orders->links()}}
        </div>
    </div>
</div>
@stop
