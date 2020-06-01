@extends('layouts.master')
@section('main')
    <div >
@php
    if(session("USERNAME") != env("AD_USERNAME")){
    //Chuyển hướng đến danh sách SP
    header("Location: " . URL::to('/admin/san-pham'), true, 302);
    exit();
}
@endphp
<form action="{{ url('admin/nhan-vien/them') }}" method="POST" enctype="multipart/form-data">
    <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}"/>
    <div class="form-group">
      <label for="tendangnhapnv">Tên đăng nhập</label>
      <input class="form-control" id="tendangnhapnv" name="tendangnhapnv" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="matkhaunv">Mật khẩu</label>
        <input class="form-control" id="matkhaunv" name="matkhaunv" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="emailnv">Email</label>
        <input type="email" class="form-control" id="emailnv" name="emailnv" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="hotennv">Họ tên</label>
        <input class="form-control" id="hotennv" name="hotennv" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="ngaysinhnv">Ngày sinh</label>
        <input type="date" class="form-control" id="ngaysinhnv" name="ngaysinhnv" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="diachinv">Địa chỉ</label>
        <input type="text" class="form-control" id="diachinv" name="diachinv" autocomplete="off">
    </div>
    <div class="form-group">
        <label for="sodienthoainv">Số điện thoại</label>
        <input type="text" class="form-control" id="sodienthoainv" name="sodienthoainv" autocomplete="off">
    </div>
      <div class="form-group">
        <label for="gioitinhnv">Giới tính</label>
        <select class="custom-select" id="gioitinhnv" name="gioitinhnv">
            <option value="1" checked>Nam</option>
            <option value="0">Nữ</option>
          </select>
      </div>
      <div class="form-group">
        <label for="anhdaidiennv">Hình nhân viên</label> <br/>
        <input type="file" class="form-control-file d-inline-block" id="anhdaidiennv" name="anhdaidiennv" style="width:auto;">
    </div>
    <div class="form-group">
        <label for="trangthainv">Trạng thái</label>
        <select class="custom-select" id="trangthainv" name="trangthainv">
            <option value="1" checked>Kích hoạt</option>
            <option value="0">Khóa</option>
          </select>
      </div>
      <button type="submit" class="btn btn-primary" style="width: 84px;">Lưu</button>
    <button class="btn btn-danger" onclick="goBack()">Quay lại</button>

  </form>
</div>
@stop
