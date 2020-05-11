package com.example.common.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.*


/**
 *Time:2019/5/30
 *Author:zyh
 *Description:
 */
object FileUtils {
    val TAG: String = "FileUtils"
    /**
     * 创建根缓存目录
     *
     * @return
     */
    fun createRootPath(context: Context): String = if (isSdCardAvailable()) {
        // /sdcard/Android/data/<application package>/cache
        context.externalCacheDir!!.path
    } else {
        // /data/data/<application package>/cache
        context.cacheDir.path
    }

    /**
     * 判断是否有SD卡
     *
     * @return
     */
    private fun isSdCardAvailable(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }

    /**
     * 递归创建文件夹
     *
     * @param dirPath
     * @return 创建失败返回""
     */
    private fun createDir(dirPath: String): String {
        try {
            val file = File(dirPath)
            if (file.parentFile.exists()) {
                Log.i(TAG, "----- 创建文件夹" + file.absolutePath)
                file.mkdir()
                return file.absolutePath
            } else {
                createDir(file.parentFile.absolutePath)
                Log.i(TAG, "----- 创建文件夹" + file.absolutePath)
                file.mkdir()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dirPath
    }

    /**
     * 递归创建文件夹
     *
     * @param file
     * @return 创建失败返回""
     */
    fun createFile(file: File): String {
        try {
            if (file.parentFile.exists()) {
                Log.i(TAG, "----- 创建文件" + file.absolutePath)
                file.createNewFile()
                return file.absolutePath
            } else {
                createDir(file.parentFile.absolutePath)
                file.createNewFile()
                Log.i(TAG, "----- 创建文件" + file.absolutePath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun writeFile(filePathAndName: String, fileContent: String) {
        try {
            val outstream = FileOutputStream(filePathAndName)
            val out = OutputStreamWriter(outstream)
            out.write(fileContent)
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    /**
     * 文件拷贝
     *
     * @param src  源文件
     * @param desc 目的文件
     */
    fun fileChannelCopy(src: File, desc: File) {
        var fi: FileInputStream? = null
        var fo: FileOutputStream? = null
        try {
            fi = FileInputStream(src)
            fo = FileOutputStream(desc)
            val `in` = fi.channel//得到对应的文件通道
            val out = fo.channel//得到对应的文件通道
            `in`.transferTo(0, `in`.size(), out)//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fo?.close()
                fi?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 打开Asset下的文件
     *
     * @param context
     * @param fileName
     * @return
     */
    fun openAssetFile(context: Context, fileName: String): InputStream {
        val am = context.assets
        var input: InputStream? = null
        try {
            input = am.open(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return input!!
    }
}