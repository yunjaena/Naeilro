package com.koreatech.naeilro.ui.myplan;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CustomGLRender implements GLSurfaceView.Renderer {
    public CustomGLRender() {
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
    }

    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16384);
        GLES20.glClearColor(1.0F, 0F, 1.0F, 0.0F);
    }
}