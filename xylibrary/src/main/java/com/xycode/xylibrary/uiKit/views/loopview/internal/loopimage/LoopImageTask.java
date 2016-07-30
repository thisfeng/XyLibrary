package com.xycode.xylibrary.uiKit.views.loopview.internal.loopimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.kevin.loopview.internal.loopimage.*;

public class LoopImageTask implements Runnable {
    private static final int BITMAP_READY = 0;

    private boolean cancelled = false;
    private OnCompleteHandler onCompleteHandler;
    private com.kevin.loopview.internal.loopimage.LoopImage image;
    private Context context;

    public static class OnCompleteHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap)msg.obj;
            onComplete(bitmap);
        }

        public void onComplete(Bitmap bitmap){};
    }

    public abstract static class OnCompleteListener {
        public abstract void onComplete();
        /***
         *  Convient method to get Bitmap after image is loaded.
         *  Override this method to get handle of bitmap
         *  Added overloaded implementation to make it backward compatible with previous versions
         */
        public void onComplete(Bitmap bitmap){
            onComplete();
        }
    }

    public LoopImageTask(Context context, com.kevin.loopview.internal.loopimage.LoopImage image) {
        this.image = image;
        this.context = context;
    }

    @Override
    public void run() {
        if(image != null) {
            complete(image.getBitmap(context));
            context = null;
        }
    }

    public void setOnCompleteHandler(OnCompleteHandler handler){
        this.onCompleteHandler = handler;
    }

    public void cancel() {
        cancelled = true;
    }

    public void complete(Bitmap bitmap){
        if(onCompleteHandler != null && !cancelled) {
            onCompleteHandler.sendMessage(onCompleteHandler.obtainMessage(BITMAP_READY, bitmap));
        }
    }
}