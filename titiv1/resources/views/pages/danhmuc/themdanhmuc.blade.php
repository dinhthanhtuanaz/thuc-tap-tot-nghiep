@extends('layouts.master')
@section('main')
    <div>

<form action="{{ url('admin/danh-muc/them') }}" method="POST" enctype="multipart/form-data">
    <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}"/>
    <div class="form-group">
      <label for="tendanhmuc">Tên danh mục</label>
      <input class="form-control" id="tendanhmuc" name="tendanhmuc" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="exampleFormControlFile1">Hình nền danh mục</label>
        <input type="file" class="form-control-file" id="exampleFormControlFile1" name="hinhnen">
      </div>
      <div class="form-group">
        <label for="exampleFormControlFile2">Mô tả</label>
        <input type="file" class="form-control-file" id="exampleFormControlFile2" name="mota">
      </div>
      <button type="submit" class="btn btn-primary">Lưu</button>
    <a class="btn btn-danger" href="{{ url('admin/danh-muc') }}">Quay lại</a>

  </form>
</div>
@stop
