package com.mao.cn.learnDevelopProject.utils.tools;

import java.io.File;
import java.io.IOException;

/**
 * author:  zhangkun .
 * date:    on 2017/8/1.
 */

public class FileUtils {

    public static void deleteFile(File file) {
        if(file.exists()) {
            if(file.isDirectory()) {
                File[] to = file.listFiles();
                if(to == null) {
                    return;
                }

                for(int i = 0; i < to.length; ++i) {
                    deleteFile(to[i]);
                }

                file.delete();
            } else {
                File var3 = new File(file.getAbsolutePath() + System.nanoTime());
                file.renameTo(var3);
                var3.delete();
            }

        }
    }

    public static String getSavePath(String filename) {
        String one = filename.substring(0, 2);
        String two = filename.substring(2, 3);
        String filePath = File.separator + one + File.separator + two + File.separator;
        return filePath + filename;
    }

    public static String convertFileSize(long size) {
        long kb = 1024L;
        long mb = kb * 1024L;
        long gb = mb * 1024L;
        if(size >= gb) {
            return String.format("%.1f GB", new Object[]{Float.valueOf((float)size / (float)gb)});
        } else {
            float f;
            if(size >= mb) {
                f = (float)size / (float)mb;
                return String.format(f > 100.0F?"%.0f MB":"%.1f MB", new Object[]{Float.valueOf(f)});
            } else if(size >= kb) {
                f = (float)size / (float)kb;
                return String.format(f > 100.0F?"%.0f KB":"%.1f KB", new Object[]{Float.valueOf(f)});
            } else {
                return String.format("%d B", new Object[]{Long.valueOf(size)});
            }
        }
    }

    public static void createFile(File file) {
        if(!file.exists()) {
            try {
                if(!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                file.createNewFile();
            } catch (IOException var2) {
                ;
            }
        }

    }

    public static boolean isExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean isExist(String fileDir, String fileName) {
        File file = new File(fileDir, fileName);
        return file.exists();
    }

    public static boolean ifNotExistCreateFile(String fileDir, String fileName) {
        File file = new File(fileDir, fileName);
        if(!file.exists()) {
            try {
                if(file.getParent() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                return file.createNewFile();
            } catch (IOException var4) {
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean ifNotExistCreateFile(String filePath) {
        File file = new File(filePath);
        if(!file.exists()) {
            try {
                if(file.getParent() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                return file.createNewFile();
            } catch (IOException var3) {
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean ifNotExistCreateFile(File file) {
        if(!file.exists()) {
            try {
                if(file.getParent() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                return file.createNewFile();
            } catch (IOException var2) {
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean ifNotExistCreateDir(String fileDir) {
        File file = new File(fileDir);
        return !file.exists()?file.mkdirs():true;
    }
}
