@extends('layouts.master')
@section('main')

@php
    //print_r($topProduct);
    //print_r($productSolded);
    if(session("USERNAME") != env("AD_USERNAME")){
    //Chuyển hướng đến danh sách SP
    header("Location: " . URL::to('/admin/don-hang'), true, 302);
    exit();
}
@endphp
<div id="wrap-content" style="background-color: #eee">
    <h2 class="text-center mb-3">Thống kê nhanh</h2>

    <div class="count-wrapper">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <div class="count-item" >
                    <div class="w-25 count-item-icon" style="background-color:#00C0EF">
                        <i class="fas fa-cart-arrow-down"></i>
                    </div>
                    <div class="w-75 count-item-text">
                        <h6>TỔNG SỐ ĐƠN HÀNG</h6>
                    <strong class="counter">{{$counts['orderCount']}}</strong> <a href="{{ url('admin/don-hang')}}">(Chi tiết)</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <div class="count-item" >
                    <div class="w-25 count-item-icon" style="background-color:#DD4A38">
                        <i class="fas fa-users"></i>
                    </div>
                    <div class="w-75 count-item-text">
                        <h6>KHÁCH HÀNG</h6>
                        <strong class="counter">{{$counts['customerCount']}}</strong> <a href="{{ url('admin/khach-hang')}}">(Chi tiết)</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <div class="count-item" >
                    <div class="w-25 count-item-icon" style="background-color:#01A65A">
                        <i class="fas fa-database"></i>
                    </div>
                    <div class="w-75 count-item-text">
                        <h6>SẢN PHẨM</h6>
                        <strong class="counter">{{$counts['productCount']}}</strong> <a href="{{ url('admin/san-pham')}}">(Chi tiết)</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <div class="count-item" >
                    <div class="w-25 count-item-icon" style="background-color:#F29A12">
                        <i class="fas fa-folder-open"></i>
                    </div>
                    <div class="w-75 count-item-text">
                        <h6>DANH MỤC</h6>
                        <strong class="counter">{{$counts['categoryCount']}}</strong> <a href="{{ url('admin/danh-muc')}}">(Chi tiết)</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="table-wrapper">
        <div class="row">
            <div class="col-7">
                {{-- Vẽ biểu đồ hình quạt --}}
                <h5>Biểu đồ tỉ lệ doanh số sản phẩm</h5>
                <div class="d-flex" style="background: #fff; padding: 15px;">
                    <canvas id="myCanvas2" width="300" height="300"></canvas>
                    <div style="padding-left:20px;">
                        <?php
                            $colorArr = array('#4CAF50', '#00BCD4', '#E91E63', '#FFC107', '#8e44ad',
                                                '#f39c12', '#34495e', '#2D7D9A', '#8764B8', '#D13438',
                                                '#FF8C00', '#008a00', '#76608a', '#ff0084', '#007ee5');
                            for($i=0; $i < count($productSolded); $i++){
                                // echo $value->SoLuongSP .",";
                                ?>
                                    <div class="d-flex" style="align-items: baseline;">
                                        <span style="display:inline-block; width:50px; height:10px;
                                                        margin-right:5px;
                                    background-color:{{$colorArr[$i]}}"></span>
                                        <span>{{$productSolded[$i]->tendanhmuc}} ({{$productSolded[$i]->SoLuongSP}})</span>
                                    </div>
                                <?php
                            }
                        ?>

                    </div>
                </div>
            </div>
            <div class="col-5">
                <h5>Sản phẩm được mua nhiều nhất</h5>
                <table class="table">
                    <thead class="thead">
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">ID</th>
                        <th scope="col">Tên</th>
                        <th scope="col" style="width: 70px;">Hình</th>
                        <th scope="col" style="width: 95px;">Số lượng</th>
                      </tr>
                    </thead>
                    <tbody>
                        <?php
                            $index=1;
                            foreach ($topProduct as $key => $value) {
                        ?>
                            <tr>
                            <th scope="row">{{$index++}}</th>
                            <td>{{$value->idsanpham}}</td>
                            <td>{{$value->tensanpham}}</td>
                            <td>
                                <img src="{{asset('public/images/') . "/" . $value->hinhsanpham}}"
                                alt="" style="width:50px; height:50px;">
                            </td>
                            <td>{{$value->SoLuongSP}}</td>
                            </tr>
                        <?php
                            }
                        ?>
                    </tbody>
                  </table>
            </div>
        </div>
    </div>
</div>
@stop

@section('block_chartjs')
<script>
    var obj2 = {
    values: [
        <?php
            foreach($productSolded as $key => $value){
                echo $value->SoLuongSP .",";
            }
        ?>
    ],
    colors: ['#4CAF50', '#00BCD4', '#E91E63', '#FFC107', '#8e44ad',
            '#f39c12', '#34495e', '#2D7D9A', '#8764B8', '#D13438',
            '#FF8C00', '#008a00', '#76608a', '#ff0084', '#007ee5'],
    animation: true,
    animationSpeed: 0,
    fillTextData: true,
    fillTextColor: '#fff',
    fillTextAlign: 1.50,
    fillTextPosition: 'inner',
    doughnutHoleSize: null,
    doughnutHoleColor: '#000',
    offset: 1
};
    generatePieGraph('myCanvas2', obj2);
</script>
@stop
