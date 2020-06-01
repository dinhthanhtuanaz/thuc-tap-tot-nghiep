<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
class KhachHangController extends Controller
{
    public function index(){
        $customers = DB::table('khachhang')->orderByDesc('id')->paginate(env('ITEMPERPAGE'));
        return view("pages.khachhang.khachhang", compact('customers'));
    }

    public function viewSoldedProduct($id){
        //echo "ID=".$id;
        //Liên kết các bảng ở đây
        $soldedProducts = DB::table("donhang")
        ->select("*")
        ->join('chitietdonhang', 'donhang.id', '=', 'chitietdonhang.iddonhang')
        ->where('donhang.idkhachhang', $id)
        ->get();
        //print_r($soldedProducts);
        $customerInfor = DB::table('khachhang')->where('id', $id)->get();
        $data = array(
            'soldedProducts' => $soldedProducts,
            'customerInfor' => $customerInfor
        );
        //print_r($customerInfor);
        return view("pages.khachhang.sanphamkhachhang", $data);
    }
}
