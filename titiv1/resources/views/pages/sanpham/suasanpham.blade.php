@extends('layouts.master')
@section('main')
    <div >
        @php
        // echo 'id='. $danhmuc[0]->tendanhmuc;
        $product = json_decode($data)->product;
        $iddanhmuc = $product[0]->iddanhmuc;
        //print_r($product[0]);
        $categories = json_decode($data)->categories;
     @endphp
<form action="{{ url('admin/san-pham/sua') }}" method="POST" enctype="multipart/form-data">
    <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}"/>
    <input type="hidden" id="id" name="id" value="{{$product[0]->id}}" />
    <div class="form-group">
      <label for="tensanpham">Tên sản phẩm</label>

    <input class="form-control" id="tensanpham" name="tensanpham" autocomplete="off" value="{{$product[0]->ten}}">
    </div>
    <div class="form-group">
        <label for="gia">Giá sản phẩm</label>
        <input type="number" class="form-control" id="gia" name="gia" autocomplete="off" value="{{$product[0]->gia}}">
    </div>
    <div class="form-group">
        <label for="hinh">Hình sản phẩm</label> <br/>
        <img src="{{asset('public/images/') . "/" . $product[0]->hinh}}" style="width:200px; height:100px; margin-bottom:15px;"/>
        <input type="file" class="form-control-file d-inline-block" id="hinh" name="hinh" style="width:auto;">
        <input type="hidden" id="hinhcu" name="hinhcu" value="{{$product[0]->hinh}}" />
    </div>
      <div class="form-group">
        <label for="mota">Mô tả</label>
        <textarea class="form-control" name="mota" id="mota" cols="30" rows="10">{{$product[0]->mota}}</textarea>
      </div>
      <div class="form-group">
        <label for="thongsochitiet">Thông số chi tiết</label>
        <textarea class="form-control" name="thongsochitiet" id="thongsochitiet" cols="30" rows="10">{{$product[0]->thongsochitiet}}</textarea>
      </div>
      <div class="form-group">
        <label for="iddanhmuc">Danh mục</label>
        <select class="custom-select" id="iddanhmuc" name="iddanhmuc">

            <?php foreach ($categories as $key => $value) {
                if ($value->id == $iddanhmuc) {
                    ?> <option value="{{$value->id}}" selected>{{$value->tendanhmuc}}</option> <?php
                } else {
                    ?> <option value="{{$value->id}}">{{$value->tendanhmuc}}</option> <?php
                }

            }?>
          </select>
      </div>
      <button type="submit" class="btn btn-primary" style="width: 84px;">Lưu</button>
    <a class="btn btn-danger" href="{{ url('admin/san-pham') }}">Quay lại</a>

  </form>
</div>
@stop
