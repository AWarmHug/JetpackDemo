package com.bingo.jetpackdemo.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.bingo.jetpackdemo.BuildConfig;

import java.io.File;

import static com.bingo.jetpackdemo.utils.ContextContentProvider.appContext;

/**
 * 作者：warm
 * 时间：2018-01-06 15:05
 * 描述：
 */

public class FileUtils {

    //采用自己的格式去设置文件，防止文件被系统文件查询到
    public static final String SUFFIX_NB = ".nb";
    public static final String SUFFIX_TXT = ".txt";
    public static final String SUFFIX_EPUB = ".epub";
    public static final String SUFFIX_PDF = ".pdf";


    public static final String CACHE_HTTP = "cache_http";

    //网络缓存文件夹名称
    public static final String CACHE_IMAGE = "cache_image";

    //拍照文件夹
    public static final String CAMERA_IMAGE = "camera_image";

    //图片裁剪后的文件夹名称
    public static final String CROP_IMAGE = "crop_image";

    //压缩后的图片文件夹名称
    public static final String ZIP_IMAGE = "zip_image";


    //Apk下载文件夹
    public static final String APK = "apk";

    //crash收集文件夹
    public static final String CRASH = "crash";

    public static final String BOOK = "book";


    public static File createFileDir(String dirName) {
        return createFileDir(dirName, true);
    }

    public static File createFileDir(String dirName, boolean inData) {
        return createFileDir(getExternalCacheDir(appContext, inData), dirName);
    }

    public static File createFileDir(String parentPath, String dirName) {
        if (!TextUtils.isEmpty(parentPath)) {
            File parentFile = new File(parentPath, dirName);
            if (!parentFile.exists()) {
                boolean suc = parentFile.mkdirs();
                if (suc) {
                    return parentFile;
                } else {
                    return null;
                }
            } else {
                return parentFile;
            }
        } else {
            return null;
        }
    }


    /**
     * 获取拓展存储Cache的绝对路径
     *
     * @param context
     */
    private static String getExternalCacheDir(Context context, boolean inData) {
        StringBuilder sb = new StringBuilder();
        if (checkSdcard()) {
            if (inData) {
                File file = context.getExternalCacheDir();
                if (file != null) {
                    sb.append(file.getAbsolutePath());
                } else {
                    sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Android/data/").append(context.getPackageName())
                            .append("/cache/");
                }
            } else {
                sb.append(Environment.getExternalStorageDirectory().getAbsolutePath()).append(File.separator).append(BuildConfig.APPLICATION_ID);

            }


        } else {
            sb.append(context.getCacheDir().getAbsolutePath());
        }
        return sb.toString();
    }


    private static boolean checkSdcard() {
        //判断sd卡情况
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !Environment.isExternalStorageRemovable();
    }



    public static String getFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;

        final String scheme = uri.getScheme();
        String data = null;

        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Files.FileColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
            if (data == null) {
                String path = uri.getEncodedPath();
                final int splitIndex = path.indexOf('/', 1);
                final String tag = Uri.decode(path.substring(1, splitIndex));
                path = Uri.decode(path.substring(splitIndex + 1));
                data = path;
            }
        }
        return data;
    }


}
