<?php

namespace App\Http\Controllers;
use App\ChiTietDonHang;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
class ThongKeController extends Controller
{
    public function index(){
        //counter
        $orderCount = DB::table('donhang')->count();
        $customerCount = DB::table('khachhang')->count();
        $productCount = DB::table('sanpham')->count();
        $categoryCount = DB::table('danhmuc')->count();

        //top 10 products
        $topProduct = DB::table("chitietdonhang")
        ->select(DB::raw("idsanpham"),
                DB::raw('tensanpham'),
                DB::raw('hinhsanpham'),
                DB::raw("SUM(soluong) as SoLuongSP"))
        ->groupBy(DB::raw("idsanpham"),
                DB::raw("tensanpham"),
                DB::raw("hinhsanpham"))
        ->orderByDesc('SoLuongSP')
        ->take(env('TOP_PRODUCT'))
	    ->get();

        //thống kê số lượng sản phẩm đã bán từng doanh mục
        $productSolded = DB::table("chitietdonhang")
                    ->select("danhmuc.tendanhmuc", DB::raw("SUM(soluong) as SoLuongSP"))
                    ->join('sanpham', 'chitietdonhang.idsanpham', '=', 'sanpham.id')
                    ->join('danhmuc', 'sanpham.iddanhmuc', '=', 'danhmuc.id')
                    ->groupBy("danhmuc.tendanhmuc")
                    ->orderByDesc('SoLuongSP')
                    ->get();
        //print_r($productSolded);

        $data = array(
            'counts' => array(
                'orderCount' => $orderCount,
                'customerCount' => $customerCount,
                'productCount' => $productCount,
                'categoryCount' => $categoryCount,
            ),
            'topProduct' => $topProduct,
            'productSolded' => $productSolded
        );
        return view("pages.thongke.thongke", $data);
    }
}
