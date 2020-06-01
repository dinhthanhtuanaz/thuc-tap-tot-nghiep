<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ChiTietDonHang extends Authenticatable
{
    use Notifiable;

    protected $table        = 'chitietdonhang';
    protected $primaryKey   = 'id';
    protected $guard        = 'admin';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'idsanpham','iddonhang','tensanpham','hinhsanpham','soluong','gia'
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password', 'remember_token',
    ];

    /**
     * The attributes that should be cast to native types.
     *
     * @var array
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];
}
