//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mao.cn.learnDevelopProject.utils.download;

import android.text.TextUtils;

import com.mao.cn.learnDevelopProject.utils.tools.FileU;
import com.mao.cn.learnDevelopProject.utils.tools.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class DLTask implements Serializable, Runnable {
    private String url;
    private String contentType;
    private String baseUrl;
    private String saveDir;
    private String resourceCode;
    private boolean success;
    private boolean isCancel;
    private ProgressListener listener;
    private static OkHttpClient client;
    private static Builder builder;
    private Response response;

    public DLTask() {
    }

    public boolean isCancel() {
        return this.isCancel;
    }

    public void setCancel(boolean cancel) {
        this.isCancel = cancel;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ProgressListener getListener() {
        return this.listener;
    }

    public void setListener(ProgressListener listener) {
        this.listener = listener;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getResourceCode() {
        return this.resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String toString() {
        return "DLTask{url='" + this.url + '\'' + ", contentType='" + this.contentType + '\'' + ", baseUrl='" + this.baseUrl + '\'' + ", saveDir='" + this.saveDir + '\'' + ", resourceCode='" + this.resourceCode + '\'' + ", success=" + this.success + ", isCancel=" + this.isCancel + ", listener=" + this.listener + '}';
    }

    public void run() {
        if (!TextUtils.isEmpty(this.url) && !TextUtils.isEmpty(this.saveDir)) {
            client = DownLoadManager.getInstance().getClient();
            CacheControl cacheControl = CacheControl.FORCE_NETWORK;
            builder = new Builder();
            if (cacheControl != null) {
                builder.cacheControl(cacheControl);
            }

            File file = new File(this.saveDir);

            try {
                this.response = client.newCall(builder.url(this.url).build()).execute();
                if (this.response.code() == 200) {
                    InputStream is = null;
                    FileOutputStream outputStream = null;

                    try {
                        outputStream = new FileOutputStream(file);
                        is = this.response.body().byteStream();
                        byte[] buffer = new byte[4096];

                        int len;
                        while(-1 != (len = is.read(buffer)) && !this.isCancel) {
                            outputStream.write(buffer, 0, len);
                            if (this.listener != null) {
                                this.listener.update((long)len, this.response.body().contentLength());
                            }
                        }

                        if (this.isCancel) {
                            FileU.deleteFile(file);
                            this.setSuccess(false);
                            if (this.listener != null) {
                                this.listener.fail(this);
                            }
                        } else {
                            this.setSuccess(true);
                            if (this.listener != null) {
                                this.listener.success(this);
                            }
                        }

                        this.setContentType(this.response.header("Content-Type", "object"));
                    } catch (IOException var11) {
                        var11.printStackTrace();
                        FileU.deleteFile(file);
                        this.setSuccess(false);
                        if (this.listener != null) {
                            this.listener.fail(this);
                        }
                    } finally {
                        IOUtils.closeQuietly(is);
                        IOUtils.closeQuietly(outputStream);
                    }
                } else {
                    this.setSuccess(false);
                    if (this.listener != null) {
                        this.listener.fail(this);
                    }
                }
            } catch (IOException var13) {
                FileU.deleteFile(file);
                this.setSuccess(false);
                if (this.listener != null) {
                    this.listener.fail(this);
                }
            }

        } else {
            throw new IllegalArgumentException("url and saveDir mast not be null!");
        }
    }
}
