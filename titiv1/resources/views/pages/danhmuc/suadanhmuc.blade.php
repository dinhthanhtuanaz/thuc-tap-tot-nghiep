@extends('layouts.master')
@section('main')
    <div style="padding:20px;">
@php
   // echo 'id='. $danhmuc[0]->tendanhmuc;
@endphp
<form action="{{ url('admin/danh-muc/sua') }}" method="POST" enctype="multipart/form-data">
    <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}"/>
    <div class="form-group">
      <label for="tendanhmuc">Tên danh mục</label>
      <input type="hidden" id="id" name="id" value="{{$danhmuc[0]->id}}" />
      <input class="form-control" id="tendanhmuc" name="tendanhmuc" autocomplete="off" value="{{$danhmuc[0]->tendanhmuc}}">
    </div>
    <div class="form-group">
        <label for="exampleFormControlFile1">Hình nền danh mục</label> <br />
        <img src="{{asset('public/images/') . "/" . $danhmuc[0]->hinhnen}}" style="width:200px; height:100px; margin-bottom:15px;"/>
        <input type="file" class="form-control-file" id="exampleFormControlFile1" name="hinhnen">
        <input type="hidden" id="hinhnencu" name="hinhnencu" value="{{$danhmuc[0]->hinhnen}}" />
      </div>
      <div class="form-group">
        <label for="exampleFormControlFile2">Mô tả</label> <br />
        <img src="{{asset('public/images/') . "/" . $danhmuc[0]->mota}}" style="width:100px; height:100px; margin-bottom:15px;"/>
        <input type="file" class="form-control-file" id="exampleFormControlFile2" name="mota">
        <input type="hidden" id="motacu" name="motacu" value="{{$danhmuc[0]->mota}}" />
      </div>
      <button type="submit" class="btn btn-primary" style="width:84px;">Lưu</button>
    <a class="btn btn-danger" href="{{ url('admin/danh-muc') }}">Quay lại</a>
    {{-- <button class="btn btn-danger" onclick="goBack()">Quay lại</button> --}}
  </form>
</div>
@stop
