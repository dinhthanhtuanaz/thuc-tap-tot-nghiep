@extends('layouts.master')
@section('main')
@php
    //print_r($order);
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

    <div style="background:#fff; padding:15px" class="w-75">
        <h2>Chi tiết đơn hàng</h2>
        <div>
            <strong>Mã đơn hàng: </strong>
            <strong>{{$order[0]->id}}</strong>
        </div>
        <div>
            <strong>Khách hàng: </strong>
            <span>{{$order[0]->hoten}}</span>
        </div>
        <div>
            <strong>Số điện thoại: </strong>
            <span>{{$order[0]->sodienthoai}}</span>
        </div>
        <div>
            <strong>Địa chỉ giao: </strong>
            <span>{{$order[0]->diachigiao}}</span>
        </div>
        <div>
            <strong>Ngày mua: </strong>
            <span>{{date('d-m-Y', strtotime($order[0]->ngaymua))}}</span>
        </div>
        <div>
            <strong>Ghi chú: </strong>
            <span>{{$order[0]->ghichu}}</span>
        </div>
    </div>
        <table class="table table-bordered w-75 mt-2" >
            <thead>
                <tr>
                    <th scope="col" class="width-45">#</th>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col" class="width-110">Hình</th>
                    <th scope="col" class="width-110">Số lượng</th>
                    <th scope="col" class="width-110">Đơn giá</th>
                    <th scope="col" class="width-110">Thành tiền</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    $index=1;
                    $totalMoney=0;
                    foreach ($products as $key => $value) {
                        $totalMoney += $value->gia;
                    ?>
                        <tr>
                        <th scope="row">{{$index++}}</th>
                        <td>{{$value->tensanpham}}</td>
                        <td>
                            <img src="{{asset('public/images/') . "/" . $value->hinhsanpham}}" alt="" style="width:50px; height:50px;">
                        </td>
                        <td>{{$value->soluong}}</td>
                        <td>{{number_format($value->gia/$value->soluong)}}</td>
                        <td>{{number_format($value->gia)}}</td>
                        </tr>
                    <?php
                    }
                ?>
                <tr>
                    <td colspan="6" class="text-right">
                        <span class="text-dark">Tổng tiền thanh toán:</span>
                    <strong class="text-danger">{{number_format($totalMoney) . env("MONEY_TYPE")}}</strong></td>
                </tr>
            </tbody>
        </table>
    <div class="row ">
        <div class="col-9 text-right">


        <a href="{{ url('admin/don-hang') }}" class="btn btn-primary pull-right">Quay lại</a>
    </div>
    </div>
</div>
@stop
