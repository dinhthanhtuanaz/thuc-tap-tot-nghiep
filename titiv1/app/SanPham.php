<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class SanPham extends Authenticatable
{
    use Notifiable;

    protected $table        = 'sanpham';
    protected $primaryKey   = 'id';
    protected $guard        = 'admin';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'ten','tenkhongdau','gia','hinh','mota', 'thongsochitiet', 'albumhinh', 'iddanhmuc'
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
