package com.example.listener;

public interface DownloadListener {
    void onSuccess();
    void onFail();
    void onProgress(int progress);
    void onCancel();
    void onPause();
}
