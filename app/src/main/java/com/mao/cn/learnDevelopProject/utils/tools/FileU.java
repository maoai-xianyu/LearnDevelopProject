package com.mao.cn.learnDevelopProject.utils.tools;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileU extends FileUtils {
    /**
     * 获取SD可写目录
     *
     * @return File
     */
    public static File getSdWritePath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory();
        } else {
            File w = new File("/mnt/");
            if (w.exists()) {
                File[] f = w.listFiles();
                for (int i = 0, j = f.length; i < j; ++i) {
                    if (f[i].isDirectory() && f[i].canWrite()) {
                        return f[i];
                    }
                }
            }
        }
        return null;
    }

    public static boolean hasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    //删除文件和目录
    public static boolean clearFiles(String workspaceRootPath) {
        try {
            File file = new File(workspaceRootPath);
            if (file.exists()) {
                deleteFile(file);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static String getSavePath(String filename) {
        String one = filename.substring(0, 2);
        String two = filename.substring(2, 3);
        String filePath = File.separator + one + File.separator + two + File.separator;
        return filePath + filename;
    }


    public static boolean isExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean isExist(String fileDir, String fileName) {
        File file = new File(fileDir, fileName);
        return file.exists();
    }


    /**
     * 文件重命名
     *
     * @param oldPath 原文件
     * @param newPath 重命名后的文件
     * @return
     */
    public static boolean renameFile(String oldPath, String newPath) {
        File file = new File(oldPath);
        return file.renameTo(new File(newPath));
    }

    /**
     * 获取指定文件大小
     *
     * @param fileStr
     * @return
     * @throws Exception
     */
    public static long getFileSize(String fileStr) {
        File file = new File(fileStr);
        long size = 0;
        try {
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = fis.available();
            } else {
                file.createNewFile();
                Log.e("获取文件大小", "文件不存在!");
            }
        } catch (Exception e) {
            return 0;
        }

        return size;
    }


    /**
     * 更新文本
     */
    public static void updateFile(File fileName, String data) {
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ;

        OutputStream os = null;
        try {
            os = new FileOutputStream(fileName);
            os.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @param file
     * @return 是否存在录音文件
     */
    public static boolean isExistAudioFile(File file) {
        return file.exists();
    }

    /** 压缩单个文件*/
    public static void zipFile(String filePath , String zipPath) {
        try {
            File file = new File(filePath);
            File zipFile = new File(zipPath);
            InputStream input = new FileInputStream(file);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOut.setLevel(1);
            zipOut.putNextEntry(new ZipEntry(file.getName()));
            int temp = 0;
            while((temp = input.read()) != -1){
                zipOut.write(temp);
            }
            input.close();
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
