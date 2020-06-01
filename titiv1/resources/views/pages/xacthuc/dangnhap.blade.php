@extends('layouts.master3')
@section('main')
<div class="login-wrapper">

    @if ( Session::has('dangnhapthatbai') )
        <script>
            alert("Đăng nhập không thành công!");
        </script>
    @endif
    <div class="container">
        <div class="row">
            <div class="col-4">
                <div class="" id="login">
                    <form action="{{ url('admin/dang-nhap')}}" method="POST">
                        <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}"/>
                        <div class="form-group">
                          <label for="login_username">Tên đăng nhập</label>
                          <input type="text" class="form-control"
                          id="login_username" aria-describedby="emailHelp"
                          name="login_username" value="{{ Session::get('dangnhapthatbai') }}">
                        </div>
                        <div class="form-group">
                          <label for="login_password">Mật khẩu</label>
                          <input type="password" class="form-control"
                          id="login_password" name="login_password">
                        </div>
                        <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="login_remember_password">
                          <label class="form-check-label" for="login_remember_password">Nhớ mật khẩu</label>
                          <a href="" class="float-right">Quên mật khẩu</a>
                        </div>
                        <button type="submit" class="btn btn-primary w-100 mt-3">Đăng nhập</button>

                      </form>
                    </div>
            </div>
        </div>
    </div>

</div>
@endsection
