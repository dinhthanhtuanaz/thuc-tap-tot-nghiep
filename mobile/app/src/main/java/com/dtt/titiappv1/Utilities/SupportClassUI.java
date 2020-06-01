package com.dtt.titiappv1.Utilities;

import android.content.Context;
import android.widget.Toast;

public class SupportClassUI {
    public static void showMessShort(Context context, String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
    public static void showMessLong(Context context, String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
}
