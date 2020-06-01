@extends('layouts.master')
@section('main')
    <div >
@php
    //print_r($employee);
    //print_r($employee[0]->tendangnhap);
    if(session("USERNAME") != env("AD_USERNAME")){
    //Chuyển hướng đến danh sách SP
    header("Location: " . URL::to('/admin/san-pham'), true, 302);
    exit();
}
@endphp
<form action="{{ url('admin/nhan-vien/sua') }}" method="POST" enctype="multipart/form-data">
    <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}"/>
    <div class="form-group">
      <label for="tendangnhapnv">Tên đăng nhập</label>
      <input type="hidden" id="id" name="id" value="{{$employee[0]->id}}" />
      <input class="form-control" id="tendangnhapnv" name="tendangnhapnv"
                autocomplete="off" value="{{$employee[0]->tendangnhap}}">
    </div>
    <div class="form-group">
        <label for="matkhaunv">Mật khẩu</label>
        <input class="form-control" id="matkhaunv" name="matkhaunv"
                autocomplete="off" value="{{$employee[0]->matkhau}}">
    </div>
    <div class="form-group">
        <label for="emailnv">Email</label>
        <input type="email" class="form-control" id="emailnv" name="emailnv"
                autocomplete="off" value="{{$employee[0]->email}}">
    </div>
    <div class="form-group">
        <label for="hotennv">Họ tên</label>
        <input class="form-control" id="hotennv" name="hotennv"
                autocomplete="off" value="{{$employee[0]->hoten}}">
    </div>
    <div class="form-group">
        <label for="ngaysinhnv">Ngày sinh</label>
        <input type="date" class="form-control" id="ngaysinhnv"
                name="ngaysinhnv" autocomplete="off" value="{{$employee[0]->ngaysinh}}">
    </div>
    <div class="form-group">
        <label for="diachinv">Địa chỉ</label>
        <input type="text" class="form-control" id="diachinv" name="diachinv"
                autocomplete="off" value="{{$employee[0]->diachi}}">
    </div>
    <div class="form-group">
        <label for="sodienthoainv">Số điện thoại</label>
        <input type="text" class="form-control" id="sodienthoainv"
                name="sodienthoainv" autocomplete="off" value="{{$employee[0]->sodienthoai}}">
    </div>
      <div class="form-group">
        <label for="gioitinhnv">Giới tính</label>
        <select class="custom-select" id="gioitinhnv" name="gioitinhnv">
            <?php
                //echo "giới tính: " . $employee[0]->gioitinh;
                if($employee[0]->gioitinh==1){
                    ?>
                        <option value="1" selected>Nam</option>
                        <option value="0">Nữ</option>
                    <?php
                } else{
                    ?>
                        <option value="1" >Nam</option>
                        <option value="0" selected>Nữ</option>
                    <?php
                }
            ?>

          </select>
      </div>
      <div class="form-group">
        <label for="anhdaidiennv">Hình nhân viên</label> <br />
        <img src="{{asset('public/images/') . "/" . $employee[0]->anhdaidien}}" style="width:100px; height:100px; margin-bottom:15px;"/>
        <input type="file" class="form-control-file" id="anhdaidiennv" name="anhdaidiennv">
        <input type="hidden" id="anhdaidiennvcu" name="anhdaidiennvcu" value="{{$employee[0]->anhdaidien}}" />
      </div>

    <div class="form-group">
        <label for="trangthainv">Trạng thái</label>
        <select class="custom-select" id="trangthainv" name="trangthainv">
            <?php
                if($employee[0]->trangthai==1){
                    ?>
                        <option value="1" selected>Kích hoạt</option>
                        <option value="0">Khóa</option>
                    <?php
                } else{
                    ?>
                        <option value="1" >Kích hoạt</option>
                        <option value="0" selected>Khóa</option>
                    <?php
                }
            ?>
          </select>
      </div>
      <button type="submit" class="btn btn-primary" style="width: 84px;">Lưu</button>
      {{-- <a class="btn btn-danger" href="{{ url('/admin/nhan-vien') }}">Quay lại</a> --}}
      <button class="btn btn-danger" onclick="goBack()">Quay lại</button>
  </form>
</div>
@stop
