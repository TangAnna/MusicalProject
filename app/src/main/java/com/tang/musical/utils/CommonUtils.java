/*
    ShengDao Android Client, CommonUtils
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.tang.musical.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * [公共工具类，与Android API相关的辅助类]
 *
 * @author devin.hu
 * @version 1.0
 * @date 2013-9-30
 **/
public class CommonUtils {

    @SuppressWarnings("unused")
    private static final String tag = CommonUtils.class.getSimpleName();

    /**
     * 网络类型
     **/
    public static final int NETTYPE_WIFI = 0x01;

    public static final int NETTYPE_CMWAP = 0x02;

    public static final int NETTYPE_CMNET = 0x03;

    public static Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
            .excludeFieldsWithoutExposeAnnotation().create();

    /**
     * 通过Gson将json转换成Bean
     */
    public static <T> T GsonFromJsonToBean(String json, Class<T> classOfT) {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .create();
        return gson.fromJson(json, classOfT);
    }

    /**
     * 将 Gson解析null替换为空字符串解决方案
     */
    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringNullAdapter();
        }
    }

    /**
     * 将 Gson解析null替换为空字符串解决方案
     */
    public static class StringNullAdapter extends TypeAdapter<String> {
        @Override
        public String read(JsonReader reader) throws IOException {
            // TODO Auto-generated method stub
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            // TODO Auto-generated method stub
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }

    }

    /**
     * 根据key获取config.properties里面的值
     *
     * @param context
     * @param key
     * @return
     */
    public static String getProperty(Context context, String key) {
        try {
            Properties props = new Properties();
            InputStream input = context.getAssets().open("config.properties");
            if (input != null) {
                props.load(input);
                return props.getProperty(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 判断SDCard是否存在,并可写
     *
     * @return
     */
    public static boolean checkSDCard() {
        String flag = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(flag)) {
            return true;
        }
        return false;
    }

    public static String getResString(Context mContext, int mStringResId) {
        return mContext.getResources().getString(mStringResId);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取屏幕显示信息对象
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    /**
     * dp转pixel
     */
    public static float dpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * pixel转dp
     */
    public static float pixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }


    /**
     * 短信分享
     *
     * @param mContext
     * @param phoneNumber 电话
     * @return
     */
    public static void sendSms(Context mContext, String phoneNumber) {
        Uri smsToUri = Uri.parse("smsto:" + phoneNumber);
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", "");
        mContext.startActivity(mIntent);
    }

    /**
     * 调用手机拨号盘
     *
     * @param mContext
     * @param phoneNumber
     */
    public static void dialPhoneNumber(Context mContext, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    /**
     * 保存联系人到本地通讯录
     *
     * @param context
     * @param name
     * @param email
     * @param mobile
     * @param phone
     */
    public static void saveTel(Context context, String name, String email, String mobile, String phone) {

        Intent it = new Intent(Intent.ACTION_INSERT,
                Uri.withAppendedPath(Uri.parse("content://com.android.contacts"), "contacts"));
        it.setType("vnd.android.cursor.dir/person");
        // it.setType("vnd.android.cursor.dir/contact");
        // it.setType("vnd.android.cursor.dir/raw_contact");
        // 联系人姓名
        it.putExtra(ContactsContract.Intents.Insert.NAME, name);
        // email
        it.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        // 手机号码
        it.putExtra(ContactsContract.Intents.Insert.PHONE, mobile);
        // 住宅电话
        it.putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE, phone);
        it.putExtra(ContactsContract.Intents.Insert.COMPANY, phone);

        context.startActivity(it);

    }

    /**
     * 保存联系人到本地通讯录
     *
     * @param context
     * @param name
     * @param email
     * @param mobile
     * @param phone
     * @param company
     */
    public static void saveTel(Context context, String name, String email, String mobile, String phone, String company) {

        Intent it = new Intent(Intent.ACTION_INSERT,
                Uri.withAppendedPath(Uri.parse("content://com.android.contacts"), "contacts"));
        it.setType("vnd.android.cursor.dir/person");
        // it.setType("vnd.android.cursor.dir/contact");
        // it.setType("vnd.android.cursor.dir/raw_contact");
        // 联系人姓名
        it.putExtra(ContactsContract.Intents.Insert.NAME, name);
        // email
        it.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        // 手机号码
        it.putExtra(ContactsContract.Intents.Insert.PHONE, mobile);
        // 住宅电话
        it.putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE, phone);
        //公司
        it.putExtra(ContactsContract.Intents.Insert.COMPANY, company);

        context.startActivity(it);

    }

    /**
     * 邮件分享
     *
     * @param mContext
     * @param title    邮件的标题
     * @param text     邮件的内容
     * @return
     */
    public static void sendMail(Context mContext, String title, String text) {
        // 调用系统发邮件
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // 设置文本格式
        emailIntent.setType("text/plain");
        // 设置对方邮件地址
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "");
        // 设置标题内容
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        // 设置邮件文本内容
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        mContext.startActivity(Intent.createChooser(emailIntent, "Choose Email Client"));
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//
                // 这个据说很好使
            }
        }
    }

    /**
     * 显示软键盘
     *
     * @param activity
     */
    public static void showKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (!imm.isActive()) {
                imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 是否横屏
     *
     * @param context
     * @return true为横屏，false为竖屏
     */
    public static boolean isLandscape(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是平板 这个方法是从 Google I/O App for Android 的源码里找来的，非常准确。
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    static public final int REQUEST_CODE_ASK_PERMISSIONS = 101;


    /**
     * 过滤emoji表情，设定最大输入长度
     *
     * @param editText
     * @param length
     */
    public static void EmojiFilter(EditText editText, int length) {
        editText.setFilters(new InputFilter[]{emojiFilter, new InputFilter.LengthFilter(length)});
    }

    /**
     * 过滤 emoji表情
     *
     * @param editText
     */
    public static void EmojiFilter(EditText editText) {
        editText.setFilters(new InputFilter[]{emojiFilter});
    }

    /**
     * 过滤emoj表情
     */
    static InputFilter emojiFilter = new InputFilter() {

        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {

                return "";
            }
            return null;
        }
    };

    /**
     * 过滤 特殊字符 / < > ^
     *
     * @param editText
     */
    public static void stringFilter(EditText editText) {
        editText.setFilters(new InputFilter[]{stringFilter});
    }

    /**
     * 过滤特殊字符 / < > ^
     */
    static InputFilter stringFilter = new InputFilter() {

        Pattern string = Pattern.compile(
                "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|[/<>^]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher stringMatcher = string.matcher(source);
            if (stringMatcher.find()) {

                return "";
            }
            return null;
        }
    };

    /**
     * 获取版本号
     */
    public static String getVersion(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(mContext.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1";
    }


    /**
     * 读取Assets文件夹中的图片资源
     *
     * @param context
     * @param fileName 图片名称
     * @return
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 保存bitmap到本地
     *
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Bitmap mBitmap) {
        File file;
        File filePic = new File(Environment.getExternalStorageDirectory(), "share_icon");
        if (!filePic.exists()) {
            filePic.mkdir();
        }
        String fileName = "share_icon" + ".png";
        file = new File(filePic, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    /**
     * 将byte数组写入文件
     */
    public static File bytesToImageFile(byte[] bytes) {
//        String filePath = Environment.getExternalStorageDirectory() + "/wuzhenMegLive";
        String filePath = Environment.getExternalStorageDirectory() + "";
        File pngFile = new File(filePath + "/face_img.jpeg");
        if (pngFile.exists()) {
            pngFile.delete();
        }

        //不存在，直接写入
        InputStream inputStream;
        try {
            pngFile.createNewFile();
            inputStream = new ByteArrayInputStream(bytes);
//                File file = new File(filePath);
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
            String pathName = filePath + "/face_img.jpeg";
            FileOutputStream fileOutputStream = new FileOutputStream(pathName);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pngFile;
    }


    /**
     * Android 6.0 原生
     *
     * @param activity
     * @param dark
     */
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        // 状态栏字体图标颜色
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // 浅色状态栏(字体图标白色)
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // contentView
                        // 全屏(置于statusbar之下)
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    /**
     * 复制内容到剪切板
     *
     * @param mContext
     * @param content
     */
    public static void copyToClipboard(Context mContext, String content) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

    /**
     * 跳转到微信
     */
    public static void getWechatApi(Context mContext) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // TODO: handle exception
//            CustomToast.showToast(mContext, mContext.getString(R.string.open_wechat_tips));
        }
    }

}
