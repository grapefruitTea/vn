package com.vn.shop.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    public static String generateThumbnail(InputStream thumbnailInputStream, String targetAddr,String fileName) {
        String realFileName = PathUtil.getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
//            Thumbnails.of(thumbnail.getInputStream()).size(200, 200).
////                    outputQuality(0.25f).toFile(dest);
            Thumbnails.of(thumbnailInputStream).size(500, 500)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
                    .outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

//    public static String generateNormalImg(CommonsMultipartFile thumbnail, String targetAddr) {
//        String realFileName = PathUtil.getRandomFileName();
//        String extension = getFileExtension(thumbnail);
//        makeDirPath(targetAddr);
//        String relativeAddr = targetAddr + realFileName + extension;
//        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
//        try {
//            Thumbnails.of(thumbnail.getInputStream()).size(337, 640).outputQuality(0.5f).toFile(dest);
//        } catch (IOException e) {
//            throw new RuntimeException("创建缩略图失败：" + e.toString());
//        }
//        return relativeAddr;
//    }

//    public static List<String> generateNormalImgs(List<CommonsMultipartFile> imgs, String targetAddr) {
//        int count = 0;
//        List<String> relativeAddrList = new ArrayList<String>();
//        if (imgs != null && imgs.size() > 0) {
//            makeDirPath(targetAddr);
//            for (CommonsMultipartFile img : imgs) {
//                String realFileName = PathUtil.getRandomFileName();
//                String extension = getFileExtension(img);
//                String relativeAddr = targetAddr + realFileName + count + extension;
//                File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
//                count++;
//                try {
//                    Thumbnails.of(img.getInputStream()).size(600, 300).outputQuality(0.5f).toFile(dest);
//                } catch (IOException e) {
//                    throw new RuntimeException("创建图片失败：" + e.toString());
//                }
//                relativeAddrList.add(relativeAddr);
//            }
//        }
//        return relativeAddrList;
//    }

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 删除文件或目录
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                for (File file : files) {
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }
}
