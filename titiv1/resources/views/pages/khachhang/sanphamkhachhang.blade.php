@extends('layouts.master')
@section('main')
@php
    //print_r($customerInfor);
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
        <h2>Danh sách sản phẩm đã mua</h2>
        <div>
            <strong>Mã khách hàng: </strong>
            <span>{{$customerInfor[0]->id}}</span>
        </div>
        <div>
            <strong>Họ tên: </strong>
            <span>{{$customerInfor[0]->hoten}}</span>
        </div>
        <div>
            <strong>Số điện thoại: </strong>
            <span>{{$customerInfor[0]->sodienthoai}}</span>
        </div>
        <div>
            <strong>Giới tính: </strong>
        <span>{{$customerInfor[0]->gioitinh == env('GENDER_MEN') ? "Nam" : "Nữ"}}</span>
        </div>
        <div>
            <strong>Ngày tham gia: </strong>
            <span>{{date('d-m-Y', strtotime($customerInfor[0]->ngaytao))}}</span>
        </div>

    </div>
        <table class="table table-bordered w-75 mt-2" >
            <thead>
                <tr>
                    <th scope="col" class="width-45">#</th>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col" class="width-110">Hình</th>
                    <th scope="col" class="width-110">Ngày mua</th>
                    <th scope="col" class="width-110">Số lượng</th>
                    <th scope="col" class="width-110">Đơn giá</th>
                    <th scope="col" class="width-110">Thành tiền</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    $index=1;
                    $totalMoney=0;
                    foreach ($soldedProducts as $key => $value) {
                        $totalMoney += $value->gia;
                    ?>
                        <tr>
                        <th scope="row">{{$index++}}</th>
                        <td>{{$value->tensanpham}}</td>
                        <td>
                            <img src="{{asset('public/images/') . "/" . $value->hinhsanpham}}" alt="" style="width:50px; height:50px;">
                        </td>
                        <td>{{date('d-m-Y', strtotime($value->ngaymua))}}</td>
                        <td>{{$value->soluong}}</td>
                        <td>{{number_format($value->gia/$value->soluong)}}</td>
                        <td>{{number_format($value->gia)}}</td>
                        </tr>
                    <?php
                    }
                ?>
                <tr>
                    <td colspan="7" class="text-right">
                    <span class="text-dark">Tổng tiền:</span>
                    <strong class="text-danger">{{number_format($totalMoney) . env("MONEY_TYPE")}}</strong></td>
                </tr>
            </tbody>
        </table>
    <div class="row ">
        <div class="col-9 text-right">


        {{-- <a href="{{ url('admin/don-hang') }}" class="btn btn-primary pull-right">Quay lại</a> --}}
        <button onclick="goBack()" class="btn btn-primary pull-right">Quay lại</button>
    </div>
    </div>
</div>

@stop
