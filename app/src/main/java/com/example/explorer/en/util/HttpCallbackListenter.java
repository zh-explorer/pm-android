package com.example.explorer.en.util;

import android.os.Handler;

/**
 * Created by explorer on 15-11-24.
 *
 */
public interface HttpCallbackListenter {

    public void onFinish(String response);

    public void onError(Exception e);
}
