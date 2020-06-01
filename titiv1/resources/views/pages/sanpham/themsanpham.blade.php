@extends('layouts.master')
@section('main')
    <div >

<form action="{{ url('admin/san-pham/them') }}" method="POST" enctype="multipart/form-data">
    <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}"/>
    <div class="form-group">
      <label for="tensanpham">Tên sản phẩm</label>
      <input class="form-control" id="tensanpham" name="tensanpham" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="gia">Giá sản phẩm</label>
        <input type="number" class="form-control" id="gia" name="gia" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="hinh">Hình sản phẩm</label> <br/>
        <input type="file" class="form-control-file d-inline-block" id="hinh" name="hinh" style="width:auto;">
    </div>
      <div class="form-group">
        <label for="mota">Mô tả</label>
        <textarea class="form-control" name="mota" id="mota" cols="30" rows="10"></textarea>
      </div>
      <div class="form-group">
        <label for="thongsochitiet">Thông số chi tiết</label>
        <textarea class="form-control" name="thongsochitiet" id="thongsochitiet" cols="30" rows="10"></textarea>
      </div>
      <div class="form-group">
        <label for="iddanhmuc">Danh mục</label>
        <select class="custom-select" id="iddanhmuc" name="iddanhmuc">
            <?php
                foreach ($categories as $key => $category) {
                ?>
                    <option value="{{$category->id}}">{{$category->tendanhmuc}}</option>
                <?php
                }
            ?>
          </select>
      </div>
      <button type="submit" class="btn btn-primary" style="width: 84px;">Lưu</button>
    <a class="btn btn-danger" href="{{ url('admin/san-pham') }}">Quay lại</a>

  </form>
</div>
@stop
