package com.tang.musical.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;


import com.alipay.sdk.app.PayTask;
import com.tang.musical.model.AliPayResult;

import java.util.Map;

/**
 * @author TangAnna
 * @description: 支付宝支付
 * @date :${DATA} 13:45
 */
public class AliPayUtils {
    private static final String TAG = "AliPayUtils";
    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_AUTH_FLAG = 2;
    private static final String PAT_SUCUESS = "9000";

    private Activity mActivity;
    private AliPayInterface mAliPayInterface;

    public AliPayUtils(Activity activity, AliPayInterface aliPayInterface) {
        mActivity = activity;
        mAliPayInterface = aliPayInterface;
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(PAT_SUCUESS, resultStatus)) {
                        LogUtils.dNormal(TAG, "alipay sucess");
                        if (mAliPayInterface != null) {
                            mAliPayInterface.onSucessListener();
                        }
                    } else {
                        LogUtils.dNormal(TAG, "alipay fali");
                        if (mAliPayInterface != null) {
                            mAliPayInterface.onFaliListener();
                        }
                    }
                    break;

            }
        }
    };


    public void aliPay(final String orderInfo) {
        if (TextUtils.isEmpty(orderInfo)) {
            ToastUtils.showToast("支付宝OrderId为空");
            return;
        }
        LogUtils.dNormal(TAG, "支付宝订单ID==" + orderInfo);

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public interface AliPayInterface {
        void onSucessListener();

        void onFaliListener();
    }

    /**
     * 获取支付宝 SDK 版本号。
     */
    public void showSdkVersion() {
        PayTask payTask = new PayTask(mActivity);
        String version = payTask.getVersion();
        LogUtils.dNormal(TAG, "支付宝SDK版本号==" + version);
    }
}
