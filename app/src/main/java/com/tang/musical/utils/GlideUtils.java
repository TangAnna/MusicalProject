package com.tang.musical.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.tang.musical.R;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author TangAnna
 * @description: Glide工具类
 * @date :${DATA} 10:06
 */
public class GlideUtils {

    private static final String TAG = "GlideUtils";
    private static final int placeholderDefault = R.color.white;//普通的占位图
    private static final int errorDefault = R.color.white;//普通错误的占位图
    private static final int morentouxiang = R.color.white;//头像的占位图
    private static final int bannerDefault = R.color.white;//banner的占位图

    /**
     * 加载图片(默认)
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadDefaultImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault) //占位图
                .error(errorDefault) //错误图
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * 加载图片(默认)
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadSizeImage(Context context, String url, ImageView imageView, int x, int y) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault) //占位图
                .error(errorDefault) //错误图
                .override(x, y)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault) //占位图
                .error(errorDefault) //错误图
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载头像使用
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadAvatar(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(morentouxiang) //占位图
                .error(morentouxiang) //错误图
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载头像使用
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadAvatar(Context context, String url, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .circleCrop()//设置圆形
                .placeholder(morentouxiang)
                .error(morentouxiang)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }


    /**
     * 指定图片大小;使用override()方法指定了一个图片的尺寸。
     * Glide现在只会将图片加载成width*height像素的尺寸，而不会管你的ImageView的大小是多少了。
     * 如果你想加载一张图片的原始尺寸的话，可以使用Target.SIZE_ORIGINAL关键字----override(Target.SIZE_ORIGINAL)
     *
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadImageSize(Context context, String url, ImageView imageView, int width, int height) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault) //占位图
                .error(R.color.white) //错误图
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * 禁用内存缓存功能
     * diskCacheStrategy()方法基本上就是Glide硬盘缓存功能的一切，它可以接收五种参数：
     * <p>
     * DiskCacheStrategy.NONE： 表示不缓存任何内容。
     * DiskCacheStrategy.DATA： 表示只缓存原始图片。
     * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
     * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
     * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImageSizekipMemoryCache(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault) //占位图
                .error(R.color.white) //错误图S
                .skipMemoryCache(true);//禁用掉Glide的内存缓存功能
        glideApi(options, context, url, imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .circleCrop()//设置圆形
                .placeholder(placeholderDefault)
                .error(errorDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param placeholder 站位图
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int placeholder) {
        RequestOptions options = new RequestOptions()
                .circleCrop()//设置圆形
                .placeholder(placeholder)
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }


    /**
     * 预先加载图片
     * 在使用图片之前，预先把图片加载到缓存，调用了预加载之后，我们以后想再去加载这张图片就会非常快了，
     * 因为Glide会直接从缓存当中去读取图片并显示出来
     *
     * @param context
     * @param url
     */
    public static void preloadImage(Context context, String url) {
        Glide.with(context).load(url).preload();

    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .circleCrop()//设置圆形
                .placeholder(placeholderDefault)
                .error(errorDefault)
                .bitmapTransform(new RoundedCornersTransformation(45, 0, RoundedCornersTransformation.CornerType.ALL))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * 加载圆角图片-指定任意部分圆角（图片上、下、左、右四个角度任意定义）
     *
     * @param context
     * @param url
     * @param imageView
     * @param type
     */
    public static void loadCustRoundCircleImage(Context context, String url, ImageView imageView, RoundedCornersTransformation.CornerType type) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault)
                .error(errorDefault)
                .bitmapTransform(new RoundedCornersTransformation(45, 0, type))
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        glideApi(options, context, url, imageView);
    }

    /**
     * 加载模糊图片（自定义透明度）
     *
     * @param context
     * @param url
     * @param imageView
     * @param blur      模糊度，一般1-100够了，越大越模糊
     */
    public static void loadBlurImage(Context context, String url, ImageView imageView, int blur) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault)
                .error(errorDefault)
                .bitmapTransform(new BlurTransformation(blur))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * 加载灰度(黑白)图片（自定义透明度）
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadBlackImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault)
                .error(errorDefault)
                .bitmapTransform(new GrayscaleTransformation())
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * Glide.with(this).asGif()    //强制指定加载动态图片
     * 如果加载的图片不是gif，则asGif()会报错， 当然，asGif()不写也是可以正常加载的。
     * 加入了一个asBitmap()方法，这个方法的意思就是说这里只允许加载静态图片，不需要Glide去帮我们自动进行图片格式的判断了。
     * 如果你传入的还是一张GIF图的话，Glide会展示这张GIF图的第一帧，而不会去播放它。
     *
     * @param context
     * @param url       例如：https://image.niwoxuexi.com/blog/content/5c0d4b1972-loading.gif
     * @param imageView
     */
    public static void loadGif(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderDefault)
                .error(errorDefault);
        Glide.with(context).load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(imageView);

    }

    /**
     * 下载图片
     *
     * @param context
     * @param url
     */
    public static void downloadImage(final Context context, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FutureTarget<File> target = Glide.with(context).asFile().load(url).submit();
                    final File imageFile = target.get();
                    LogUtils.dNormal(TAG, "下载好的图片文件路径=" + imageFile.getPath());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
//                        }
//                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 加载banner图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadBannerImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(bannerDefault) //占位图
                .error(bannerDefault) //错误图
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }

    /**
     * 没有占位图加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadNoDefaultImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        glideApi(options, context, url, imageView);
    }


    /**
     * GlideApi
     *
     * @param options
     * @param context
     * @param url
     * @param imageView
     */
    public static void glideApi(RequestOptions options, Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
