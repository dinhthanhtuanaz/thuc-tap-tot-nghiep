<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

use App\Http\Controllers\DanhmucController;


Route::get('/', function () {
    return view('welcome');
});

Route::get('admin/dang-nhap', 'XacThucController@openLogin');
Route::get('admin/dang-xuat', 'XacThucController@logout');
Route::post('admin/dang-nhap', 'XacThucController@login');

Route::prefix('admin')->group(function () {
    //URL Danh Muc
    Route::prefix('danh-muc')->group(function() {
        Route::get('/','DanhmucController@getDanhMuc');
        Route::get('them', 'DanhmucController@moFormDanhMuc');
        Route::post('them', 'DanhmucController@themDanhMuc');
        Route::get('{id}/xoa', 'DanhmucController@xoaDanhMuc');
        Route::post('sua', 'DanhmucController@suaDanhMuc');
        Route::get('{id}/sua', 'DanhmucController@moFormSua');
    });

    //URL San Pham
    Route::prefix('san-pham')->group(function() {
        Route::get('/','SanPhamController@index');
        Route::get('them', 'SanPhamController@create');
        Route::post('them', 'SanPhamController@store');
        Route::get('{id}/sua', 'SanPhamController@edit');
        Route::post('sua', 'SanPhamController@update');
        Route::get('{id}/xoa', 'SanPhamController@destroy');
    });

    //URL Don Hang
    Route::prefix('don-hang')->group(function(){
        Route::get('/', 'DonHangController@index');
        Route::get('{id}/chi-tiet', 'DonHangController@show');
    });

    //URL Thong Ke
    Route::get('thong-ke', 'ThongKeController@index');

    //URL Khach Hang
    Route::get('khach-hang', 'KhachHangController@index');
    Route::get('khach-hang/{id}/san-pham-da-mua', 'KhachHangController@viewSoldedProduct');

    //URL Nhan Vien
    Route::prefix('nhan-vien')->group(function() {
        Route::get('/','NhanVienController@index');
        Route::get('them', 'NhanVienController@create');
        Route::post('them', 'NhanVienController@store');
        Route::get('{id}/sua', 'NhanVienController@edit');
        Route::post('sua', 'NhanVienController@update');
        Route::get('{id}/xoa', 'NhanVienController@destroy');
        //Nhan vien tu cap nhat thong tin cua minh, chuc nang nay de sau.
        Route::get('{id}/cap-nhat', 'NhanVienController@editMySelf');
        Route::post('cap-nhat', 'NhanVienController@updateMySelf');
    });
    //Route::get('nhan-vien', 'NhanVienController@index');
});
