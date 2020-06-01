<?php

namespace App\Http\Controllers;
use Session;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class XacThucController extends Controller
{
    public function openLogin(Request $request){
        //Nếu đã đang đăng nhập rồi thì ko cho đi vào trang Login này nữa
        if ($request->session()->has('USERNAME')) {

            if($request->session()->get('USERNAME') == env("AD_USERNAME")){
                //Nếu là Admin
                return redirect('admin/thong-ke');
            } else{
                //Nếu là Nhân viên
                return redirect('admin/don-hang');
            }

        }
        return view("pages.xacthuc.dangnhap");
    }

    public function login(Request $request){
        $username = $request->login_username;
        $pass = $request->login_password;

        //Nếu là ADMIN
        if($username == env("AD_USERNAME") && $pass = env("AD_PASSWORD")){
            $request->session()->put("USERNAME", $username);
            return redirect('admin/thong-ke');
        }

        //Nếu là Nhân viên
        $emps = DB::table('nhanvien')
                ->where('tendangnhap', $username)
                ->where('matkhau', $pass)
                ->get();
        if(count($emps)>0){
            //print_r($emps);

            $request->session()->put("USERNAME", $emps[0]->tendangnhap);

            return redirect('admin/don-hang');
        } else{
            //echo "Đăng nhập thất bại";
            Session::flash('dangnhapthatbai', $username);
            return redirect('admin/dang-nhap');
        }
    }

    public function logout(Request $request){
        if ($request->session()->has('USERNAME')) {
            $request->session()->forget('USERNAME');
        }
        return redirect('admin/dang-nhap');
    }
}
