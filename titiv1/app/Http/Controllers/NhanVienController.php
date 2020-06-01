<?php

namespace App\Http\Controllers;
use Session;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
class NhanVienController extends Controller
{
    public function index(){
        $employees = DB::table('nhanvien')->orderByDesc('id')->paginate(env('ITEMPERPAGE'));

        return view("pages.nhanvien.nhanvien",compact('employees'));
    }

    public function create(){
        return view("pages.nhanvien.themnhanvien");
    }

    public function store(Request $request){
        $tenDangNhap = $request->tendangnhapnv;
        $matKhau = $request->matkhaunv;
        $email = $request->emailnv;
        $hoTen = $request->hotennv;
        $ngaySinh = $request->ngaysinhnv;
        $diaChi = $request->diachinv;
        $soDienThoai = $request->sodienthoainv;
        $gioiTinh = $request->gioitinhnv;
        $anhDaiDien="";
        if ($request->hasFile('anhdaidiennv')){
            $file = $request->anhdaidiennv;
            $anhDaiDien = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        }
        $trangThai = $request->trangthainv;
        //field quyen : để sau.
        $dataInsert = array(
            'tendangnhap' => $tenDangNhap,
            'matkhau' => $matKhau,
            'email' => $email,
            'hoten' => $hoTen,
            'ngaysinh' => $ngaySinh,
            'diachi' => $diaChi,
            'sodienthoai' => $soDienThoai,
            'gioitinh' => $gioiTinh,
            'anhdaidien' => $anhDaiDien,
            'trangthai' => $trangThai
        );
        //Insert data
        $insertData = DB::table('nhanvien')->insert($dataInsert);
        return redirect("admin/nhan-vien");
    }

    public function edit($id){
        $employee = DB::table('nhanvien')->where('id', $id)->get();
        return view("pages.nhanvien.suanhanvien", compact('employee'));
    }

    public function update(Request $request){
        $tenDangNhap = $request->tendangnhapnv;
        $matKhau = $request->matkhaunv;
        $email = $request->emailnv;
        $hoTen = $request->hotennv;
        $ngaySinh = $request->ngaysinhnv;
        $diaChi = $request->diachinv;
        $soDienThoai = $request->sodienthoainv;
        $gioiTinh = $request->gioitinhnv;
        $anhDaiDien="";
        if ($request->hasFile('anhdaidiennv')){
            $file = $request->anhdaidiennv;
            $anhDaiDien = $file->getClientOriginalName();
            $file->move('public/images', $file->getClientOriginalName());
        } else{
            $anhDaiDien = $request->anhdaidiennvcu;
        }
        $trangThai = $request->trangthainv;
        $updateData = DB::table('nhanvien')->where('id', $request->id)->update([
            'tendangnhap' => $tenDangNhap,
            'matkhau' => $matKhau,
            'email' => $email,
            'hoten' => $hoTen,
            'ngaysinh' => $ngaySinh,
            'diachi' => $diaChi,
            'sodienthoai' => $soDienThoai,
            'gioitinh' => $gioiTinh,
            'anhdaidien' => $anhDaiDien,
            'trangthai' => $trangThai
        ]);
        if($updateData){
            Session::flash('success', 'Sửa nhân viên thành công!');
        } else{
            Session::flash('success', 'Sửa nhân viên không thành công!');
        }
        return redirect('admin/nhan-vien');
    }

    public function destroy($id){
        $deleteData = DB::table('nhanvien')->where('id', '=', $id)->delete();
        //Kiểm tra lệnh delete để trả về một thông báo
        if ($deleteData) {
            Session::flash('success', 'Xóa thành công!');
        }else {
            Session::flash('error', 'Xóa thất bại!');
        }
        return redirect()->back();
    }

    //Nhan vien tu cap nhat TT cua minh
    //Chuc nang nay de sau.
    public function editMySelf(){

    }
    public function updateMySelf(){

    }
}
