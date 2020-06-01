<?php

namespace App\Http\Controllers;
use Session;
use Redirect;
use Illuminate\Http\Request;
use App\danhmuc;
use Illuminate\Contracts\Session\Session as SessionSession;
use Illuminate\Support\Facades\DB;

class DanhmucController extends Controller
{
    public function getDanhMuc()
    {
        //$danhsach = DB::table('danhmuc')->get()->paginate(5)->toArray();
        //$categories = DB::table('danhmuc')->get();
        // $danhsach = json_decode(json_encode($danhsach),1);
        // return view('pages.danhmuc',['danhmuc'=>$danhsach]);
        //$data['categories'] = json_decode(json_encode($danhsach),1); // trả về 1 array

        //$data['categories']
        //return view('pages.danhmuc', $categories);

        $categories= DB::table('danhmuc')->orderBy('id', 'DESC')->paginate(env('ITEMPERPAGE'));
        return view('pages.danhmuc.danhmuc',compact('categories'));
    }

    public function moFormDanhMuc(){
        return view('pages.danhmuc.themdanhmuc');
    }

    public function themDanhMuc(Request $request){
        $allRequest  = $request->all();
        $tendanhmuc = $allRequest['tendanhmuc'];
        $hinhnen = "";
        $mota = "";
        if ($request->hasFile('hinhnen')) {
            $file = $request->hinhnen;
            $hinhnen = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        }
        if ($request->hasFile('mota')) {
            $file = $request->mota;
            $mota = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        }
        $data = array(
            'tendanhmuc' => $tendanhmuc,
            'hinhnen' => $hinhnen,
            'mota' => $mota
        );
        $insertData = DB::table('danhmuc')->insert($data);
            //Thực hiện chuyển trang
            return redirect('admin/danh-muc');
    }

    public function xoaDanhMuc($id){
        $deleteData = DB::table('danhmuc')->where('id', '=', $id)->delete();
        //Kiểm tra lệnh delete để trả về một thông báo
        if ($deleteData) {
            Session::flash('success', 'Xóa thành công!');
        }else {
            Session::flash('error', 'Xóa thất bại!');
        }
        return redirect()->back();
        //Thực hiện chuyển trang
        //return redirect('admin/danh-muc');
        //return Redirect::back()->withErrors(['msg', 'Xóa thành công!']);
        //return redirect()->back()->with('success', ['Xóa thành công!']);
    }
    public function moFormSua($id){
        //Lấy dữ liệu từ db
        $getData = DB::table('danhmuc')->where('id', $id)->get();
        // $data = array();
        // array_push($data, $getData);
        return view('pages.danhmuc.suadanhmuc')->with('danhmuc', $getData);
    }
    public function suaDanhMuc(Request $request){
        $allRequest = $request->all();
        $tendanhmuc = $request->tendanhmuc;
        $hinhnen = "";
        $mota = "";
        if ($request->hasFile('hinhnen')) {
            $file = $request->hinhnen;
            $hinhnen = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        } else{
            $hinhnen = $request->hinhnencu;
        }

        if ($request->hasFile('mota')) {
            $file = $request->mota;
            $mota = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        } else{
            $mota = $request->motacu;
        }
        $updateData = DB::table('danhmuc')->where('id', $request->id)->update([
            'tendanhmuc' => $tendanhmuc,
            'hinhnen' => $hinhnen,
            'mota' => $mota
        ]);
        if($updateData){
            Session::flash('success', 'Sửa danh mục thành công!');
        } else{
            Session::flash('success', 'Sửa thất bại!');
        }
        return redirect('admin/danh-muc');
    }
}
