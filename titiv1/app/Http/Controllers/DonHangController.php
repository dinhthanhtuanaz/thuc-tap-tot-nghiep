<?php

namespace App\Http\Controllers;
use Session;
use Redirect;
use Illuminate\Http\Request;
//use App\DonHang; // ko cần cũng được?
use Illuminate\Support\Facades\DB;

class DonHangController extends Controller
{
    public function index(){
        // $orders = DB::table('donhang')->get();
        // $orders = DB::select('select * from donhang where id = ?', [1]);
        $orders = DB::table('donhang')
                        ->join('khachhang', 'donhang.idkhachhang', '=', 'khachhang.id')
                        ->select('donhang.*', 'khachhang.hoten')
                        ->orderBy('id', 'DESC')
                        ->paginate(env('ITEMPERPAGE'));
                        // ->orderBy('id', 'DESC')
                        // ->get();
        //print_r(compact('donHang'));
        //$data = json_encode($donHang);
        return view("pages.donhang.donhang", compact('orders'));
    }

    public function show($idDonHang){
        //cho "ID = " . $idDonHang;
        //Các thông tin + Danh sách sản phẩm mua
        // $orders = DB::table('donhang')->get();
        // $orders = DB::select('select * from donhang where id = ?', [1]);
        $order = DB::table('donhang')
                        ->join('khachhang', 'donhang.idkhachhang', '=', 'khachhang.id')
                        ->select('donhang.*', 'khachhang.hoten')
                        ->where('donhang.id', $idDonHang)
                        ->get();
        $products = DB::table('chitietdonhang')->where('iddonhang', $idDonHang)->get();
        $data = array(
            'order' => $order,
            'products' => $products
        );
        //print_r($data);
        return view("pages.donhang.chitietdonhang", $data);
    }
}
