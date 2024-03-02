package org.devlive.sdk.openai.utils;

import java.io.File;

public class FileUtils
{
    private FileUtils()
    {
    }

    /**
     * Get the file suffix from the provided file
     * 根据提供的文件获取文件后缀
     *
     * @param file Provided file <br />
     * 提供的文件
     * @return extension <br />
     * 后缀名
     */
    public static String getExtension(File file)
    {
        String fileExtension = null;
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(dotIndex + 1);
        }
        return fileExtension;
    }
}
