package com.mao.cn.learnDevelopProject.contants;

import com.mao.cn.learnDevelopProject.utils.tools.PathU;

import java.io.File;

/**
 * author:  zhangkun .
 * date:    on 2018/11/13.
 */
public class ImagePathU {


    // asset 在SD上的路径
    public static boolean hasSdStaticResFile(String filename) {
        File file;
        try {
            file = new File(PathU.getInstance().getFilesPath() + filename);
        } catch (Exception e) {
            return false;
        }
        return file.exists();
    }

    /**
     * asset 图片路径
     *
     * @param fileName
     * @return
     */
    private static String staticSDImagesPath(String fileName) {
        return "files" + File.separator + "images" + File.separator + fileName + ".png";
    }


    private static String staticSDImagesIconPath(String fileName) {
        return "files" + File.separator + "image_icons" + File.separator + fileName + ".png";
    }

    public static String showImages(String fileName) {
        String path = staticSDImagesPath(fileName);
        if (hasSdStaticResFile(File.separator + path)) {
            return ValueMaps.ImagePath.IMAGE_RES_SD_PATCH + PathU.getInstance().getFilesPath() + File.separator + path;
        } else {
            return ValueMaps.ImagePath.IMAGE_RES_ASSETS + path;
        }
    }

    public static String showImageIcon(String fileName) {
        String path = staticSDImagesIconPath(fileName);
        if (hasSdStaticResFile(File.separator + path)) {
            return ValueMaps.ImagePath.IMAGE_RES_SD_PATCH + PathU.getInstance().getFilesPath() + File.separator + path;
        } else {
            return ValueMaps.ImagePath.IMAGE_RES_ASSETS + path;
        }
    }

    // 下载图片路劲
    public static String downloadImagePath(String fileName) {
        return PathU.getInstance().getFilesPath() + File.separator + "files" + File.separator + "image_icons" + File.separator + fileName + ".png";
    }
}
