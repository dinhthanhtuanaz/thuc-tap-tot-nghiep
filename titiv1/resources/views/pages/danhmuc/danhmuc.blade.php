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
                <a href="{{ url('admin/danh-muc/them') }}" class="btn btn-primary float-right">Thêm</a>
            </div>
        </div>
    </div>
    <table class="table table-bordered bg-white">
        <thead>
            <tr>
                <th scope="col" class="width-45">#</th>
                <th scope="col">Tên danh mục</th>
                <th scope="col">Hình nền</th>
                <th scope="col">Hình mô tả</th>
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
        foreach ($categories as $key => $value) {
        ?>
            <tr>
                <th scope="row">{{$index++}}</th>
                <td>{{$value->tendanhmuc}}</td>
                <td>
                    <img src="{{asset('public/images/') . "/" . $value->hinhnen}}" alt=""
                        style="width:100px; height:50px;">
                </td>
                <td>
                    <img src="{{asset('public/images/') . "/" . $value->mota}}" alt="" style="width:50px; height:50px;">
                </td>
                <td>
                    <a href="{{ url('admin/danh-muc') . "/" . $value->id }}/sua" class="btn-custom-1">
                        <i class="far fa-edit text-warning fs-24"></i>
                    </a>
                    <a href="{{ url('admin/danh-muc') . "/" . $value->id }}/xoa" class="btn-custom-1"
                        onclick="return confirm('Bạn có chắc chắn xóa không ?')">
                        <i class="far fa-trash-alt fs-24 text-danger"></i>
                    </a>
                </td>
            </tr>
            <?php
        }
    ?>


            </tr>
        </tbody>
    </table>

    <div class="row ">
        <div class="col-12 pagination-center">
            {{$categories->render()}}
        </div>
    </div>
</div>
@stop
