@extends('layouts.master')
@section('main')

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
            <a href="{{ url('admin/san-pham/them') }}" class="btn btn-primary float-right">Thêm</a>
        </div>
    </div>
</div>
    <table class="table table-bordered bg-white">
        <thead>
            <tr>
                <th scope="col" class="width-45">#</th>
                <th scope="col" style="width:200px;">Tên sản phẩm</th>
                <th scope="col">Giá</th>
                <th scope="col" style="width:100px;">Hình</th>
                <th scope="col">Mô tả</th>
                <th scope="col" style="width:300px;">Thông số chi tiết</th>
                <th scope="col" style="width:105px;">Danh mục</th>
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
                foreach ($products as $key => $value) {
                ?>
                    <tr>
                    <th scope="row">{{$index++}}</th>
                    <td>{{$value->ten}}</td>
                    <td>{{number_format($value->gia)}}</td>
                    <td >
                        <img src="{{asset('public/images/') . "/" . $value->hinh}}" alt=""
                        style="width:100px; height:50px;">
                    </td>
                    <td >
                        <p class="row-three">{{$value->mota}}</p>
                    </td>
                    <td >
                        <p class="row-three">{{$value->thongsochitiet}}</td>
                    <td>{{$value->iddanhmuc}}</td>
                    <td>
                        <a href="{{ url('admin/san-pham') . "/" . $value->id }}/sua" class="btn-custom-1">
                            <i class="far fa-edit text-warning fs-24"></i>
                        </a>
                        <a href="{{ url('admin/san-pham') . "/" . $value->id }}/xoa" class="btn-custom-1"
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
            {{$products->links()}}
        </div>
    </div>
</div>
@stop
