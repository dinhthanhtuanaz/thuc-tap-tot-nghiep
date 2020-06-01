<?php

namespace App\Http\Controllers;
use Session;
use Redirect;
use Illuminate\Http\Request;
use App\SanPham;
use Illuminate\Support\Facades\DB;

class SanPhamController extends Controller
{
    public function index()
    {
        $products= DB::table('sanpham')->orderBy('id', 'DESC')->paginate(env('ITEMPERPAGE'));
        //print_r(compact('products'));
        return view('pages.sanpham.sanpham',compact('products'));
    }

    public function create()
    {
        $categories = DB::table('danhmuc')->get();
        //print_r($categories);
        return view('pages.sanpham.themsanpham',compact('categories'));
    }

    public function store(Request $request)
    {
        $allRequest = $request->all();
        $tenSP = $allRequest['tensanpham'];
        $giaSP = $allRequest['gia'];
        $hinhSP = "";
        if ($request->hasFile('hinh')){
            $file = $request->hinh;
            $hinhSP = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        }
        $moTa = $allRequest['mota'];
        $thongSoCT = $allRequest['thongsochitiet'];
        $idDanhMuc = $allRequest['iddanhmuc'];

        $dataInsert = array(
            'ten' => $tenSP,
            'tenkhongdau' => str_slug($tenSP, '-'),
            'gia' => $giaSP,
            'hinh' => $hinhSP,
            'mota' => $moTa,
            'thongsochitiet' => $thongSoCT,
            'albumhinh' => "",
            'iddanhmuc' => $idDanhMuc
        );

        $insertData = DB::table('sanpham')->insert($dataInsert);
        return redirect('admin/san-pham');
    }

    public function show($id)
    {
        //
    }

    public function edit($id)
    {
        $product = DB::table('sanpham')->where('id', $id)->get();
        $categories = DB::table('danhmuc')->get();
        $data = array(
            'product' => $product,
            'categories' => $categories
        );
        $data = json_encode($data);
        return view('pages.sanpham.suasanpham')->with('data',$data);
    }

    public function update(Request $request)
    {
        $allRequest = $request->all();
        $tenSP = $allRequest['tensanpham'];
        $giaSP = $allRequest['gia'];
        $hinhSP = "";
        if ($request->hasFile('hinh')){
            $file = $request->hinh;
            $hinhSP = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        } else{
            $hinhSP = $request->hinhcu;
        }
        $moTa = $allRequest['mota'];
        $thongSoCT = $allRequest['thongsochitiet'];
        $idDanhMuc = $allRequest['iddanhmuc'];

        $updateData = DB::table('sanpham')->where('id', $request->id)->update([
            'ten' => $tenSP,
            'tenkhongdau' => str_slug($tenSP, '-'),
            'gia' => $giaSP,
            'hinh' => $hinhSP,
            'mota' => $moTa,
            'thongsochitiet' => $thongSoCT,
            'albumhinh' => "",
            'iddanhmuc' => $idDanhMuc
        ]);
        if($updateData){
            Session::flash('success', 'Sửa sản phẩm thành công!');
        } else{
            Session::flash('success', 'Sửa thất bại!');
        }
        return redirect('admin/san-pham');
    }

    public function destroy($id)
    {
        $deleteData = DB::table('sanpham')->where('id', '=', $id)->delete();
        //Kiểm tra lệnh delete để trả về một thông báo
        if ($deleteData) {
            Session::flash('success', 'Xóa thành công!');
        }else {
            Session::flash('error', 'Xóa thất bại!');
        }
        return redirect()->back();
    }
}
