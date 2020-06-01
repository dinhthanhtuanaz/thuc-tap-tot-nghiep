<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản trị Website - TiTi</title>
  <link href="{{ asset('public') }}/libs/fonts/fonts.css" rel="stylesheet">
  <link rel="stylesheet" href="{{ asset('public') }}/libs/fontawesome/css/all.css">
  <link rel="stylesheet" href="{{ asset('public') }}/libs/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="{{ asset('public') }}/style.css">
  <link rel="stylesheet" href="{{ asset('public') }}/tuan-style.css">
</head>
<div id="wrapper">
    <?php
        $checkAuth = false;
        $role = "";
        if(session("USERNAME")==""){
            //echo "Không tt";
            //return redirect("admin/dang-nhap");
            //header("Location: " . URL::to('/admin/dang-nhap'), true, 302);
            //exit();
            $checkAuth = false;
        }   else{
            //echo session("USERNAME");
            $checkAuth = true;
            if(session("USERNAME") == env("AD_USERNAME")) $role = "admin";
            else $role = "notadmin";
        }
    ?>
  <header class="header" id="header">
    <div class="header-brand"><img src="{{ asset('public') }}/images/logo-haui-size.png" alt="logo">
      <h1>Quản Trị Hệ Thống
      </h1>
    </div>
    <div class="dropdown show header-account">
      <div class="header-account-name" id="dropdownMenuLink" data-toggle="dropdown">
        <h6><i class="fas fa-user"></i><span>
            <?php
                if($checkAuth == false){
                    header("Location: " . URL::to('/admin/dang-nhap'), true, 302);
                    exit();
                }   else{
                    echo session("USERNAME");
                }
            ?>
        </span><i class="fas fa-caret-down"></i></h6>
      </div>
      <div class="dropdown-menu header-account-control" aria-labelledby="dropdownMenuLink">
          <a class="dropdown-item" href="#">
              <i class="fas fa-user-cog"></i><span>Cập nhật</span></a>
          <a class="dropdown-item" href="{{url('/admin/dang-xuat')}}">
                <i class="fas fa-sign-out-alt"></i><span>Đăng xuất</span></a></div>
    </div>
  </header>
  <!-- end HEADER-->
  <main class="main" id="main">
    <aside class="sidebar" id="sidebar">
      <nav>
        <div class="sidebar-header">
          <div class="sidebar-avt"><img src="{{ asset('public') }}/images/avt.jpg" alt="avatar"></div>
        <h6>Xin chào {{session("USERNAME")}}</h6>
        </div>
        <ul class="sidebar-tree list-inline">
            <?php
                if($role == "admin"){
                    ?>
                        <li class="treeview"><a href="{{ asset('admin/thong-ke') }}">
                            <i class="fas fa-chart-line"></i><span>Thống kê dữ liệu</span></a>
                      </li>
                    <?php
                }
            ?>
          <li class="treeview"><a href="" class="treeview-not-link"><i class="fas fa-folder-open"></i><span>Danh mục</span><i class="fas fa-chevron-right toggle-rotate"></i></a>
            <ul class="treeview-menu list-inline">
              <li><a href="{{ asset('admin/danh-muc') }}">Danh sách</a></li>
              <li><a href="{{ asset('admin/danh-muc/them') }}">Thêm</a></li>
            </ul>
          </li>
          <li class="treeview"><a href="" class="treeview-not-link"><i class="fas fa-database"></i><span>Sản phẩm</span><i class="fas fa-chevron-right toggle-rotate"></i></a>
            <ul class="treeview-menu list-inline">
              <li><a href="{{ asset('admin/san-pham') }}">Danh sách</a></li>
              <li><a href="{{ asset('admin/san-pham/them') }}">Thêm</a></li>
            </ul>
          </li>
          <li class="treeview"><a href="{{ asset('admin/don-hang') }}"><i class="fas fa-cart-arrow-down"></i><span>Đơn hàng</span></a>
          <li class="treeview"><a href="{{ asset('admin/khach-hang') }}"><i class="fas fa-users"></i><span>Khách hàng</span></a>
          </li>

          <?php
            if($role == "admin"){
                ?>
                    <li class="treeview"><a href="" class="treeview-not-link"><i class="fas fa-user-lock"></i><span>Nhân viên</span><i class="fas fa-chevron-right toggle-rotate"></i></a>
                        <ul class="treeview-menu list-inline">
                          <li><a href="{{ asset('admin/nhan-vien') }}">Danh sách</a></li>
                          <li><a href="{{ asset('admin/nhan-vien/them') }}">Thêm</a></li>
                        </ul>
                      </li>
                <?php
            }
        ?>




        </ul>
      </nav>
    </aside>
    <!-- end SIDEBAR-->
    <section class="block-center" id="block-center">
      <div class="inner-center" id="inner-center">
            @yield('main')
      </div>
    </section>
    <!-- end CONTENT-->
  </main>
  <footer></footer>
</div>
<script src="{{ asset('public') }}/libs/jquery/jquery-2.2.4.js"></script>
<script src="{{ asset('public') }}/libs/jquery/popper.min.js"></script>
<script src="{{ asset('public') }}/libs/bootstrap/bootstrap.min.js"></script>

{{-- counter jquery --}}
<script src="{{ asset('public') }}/libs/counter/waypoint.js"></script>
<script src="{{ asset('public') }}/libs/counter/jquery.counterup.min.js"></script>
{{-- piechart js --}}
<script src="{{ asset('public') }}/libs/piechart-mater/rpie.js"></script>

<script src="{{ asset('public') }}/script.js"></script>
<script>
    //back pre page
    function goBack() {
        window.history.back();
    }
</script>
@yield('block_chartjs')
