package com.shenhua.outer.security.report.core.service;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public class GetuiIntentService extends GTIntentService {

    public GetuiIntentService() {
    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {

    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.d("shenhuaLog -- " + GetuiIntentService.class.getSimpleName(), "onReceiveClientId: >> " + clientid);
        context.getSharedPreferences("push", Context.MODE_PRIVATE).edit().putString("clientId", clientid).apply();
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {
        Log.d("shenhuaLog -- " + GetuiIntentService.class.getSimpleName(), "onReceiveMessageData: >> " + gtTransmitMessage.getMessageId());
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }
}
