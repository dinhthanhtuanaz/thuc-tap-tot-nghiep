package com.dtt.titiappv1.Utilities;

public class Server {

    public final static String SERVER_NAME = "http://192.168.1.2:8080";
    public final static String PROJECT_NAME = "/titiv1server/";
    public final static String URL_NAME = SERVER_NAME + PROJECT_NAME;
    //public final static String IMAGES_URL = SERVER_NAME + PROJECT_NAME + "images/";

    //public final static String IMAGES_URL = SERVER_NAME + "/titiv1/public/" + "images/";
    public final static String IMAGES_URL = "http://192.168.1.2:8080/titiv1/public/images/";

    //public final static String API_GETKHACHHANG = URL_NAME + "getKhachHang.php";
    public final static String API_GET_KHACH_HANG = URL_NAME + "getKhachHang.php";
    //public final static String API_CHECKKHACHHANG = URL_NAME + "checkKhachHang.php";
    public final static String API_CHECK_KHACH_HANG = URL_NAME + "checkKhachHang.php";
    //public final static String API_INSERTKHACHHANG = URL_NAME + "insertKhachHang.php";
    public final static String API_INSERT_KHACH_HANG = URL_NAME + "insertKhachHang.php";
    //public final static String API_GETDANHMUC = URL_NAME + "getDanhMuc.php";
    public final static String API_GET_DANH_MUC = URL_NAME + "getDanhMuc.php";
    //public final static String API_GET_DANH_MUC = SERVER_NAME + "/titiv1/" + "getDanhMuc.php";
    public final static String API_GET_TAT_CA_SAN_PHAM_THEO_DANH_MUC = URL_NAME
                                            + "getTatCaSanPhamTheoDanhMuc.php";
    //public final static String API_GETTATCASANPHAMMOINHATTHEODANHMUC = URL_NAME
    //        + "getTatCaSanPhamMoiNhatTheoDanhMuc.php";
    public final static String API_GET_TAT_CA_SAN_PHAM_MOI_NHAT_THEO_DANH_MUC = URL_NAME
            + "getTatCaSanPhamMoiNhatTheoDanhMuc.php";
    public final static String API_GET_DANH_MUC_THEO_ID = URL_NAME + "getDanhMucTheoId.php";
    public final static String API_GET_SAN_PHAM_THEO_ID = URL_NAME + "getSanPhamTheoId.php";
    public final static String API_INSERT_DON_HANG = URL_NAME + "insertDonHang.php";
    public final static String API_INSERT_CHI_TIET_DON_HANG = URL_NAME + "insertChiTietDonHang.php";

    public final static String API_GET_DANH_MUC_VA_SAN_PHAM = URL_NAME + "getDanhMucVaSanPham.php";
    public final static String API_GET_SAN_PHAM_DA_MUA = URL_NAME + "getSanPhamDaMua.php";
    public final static String API_GET_SAN_PHAM_TIM_KIEM = URL_NAME + "getSanPhamTheoTuKhoa.php";
}
