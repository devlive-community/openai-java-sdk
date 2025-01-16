package org.devlive.sdk.core.provider;

import org.devlive.sdk.openai.entity.FileEntity;
import org.devlive.sdk.openai.response.FileResponse;

/**
 * 文件服务提供者接口
 * 负责处理文件上传、下载、删除等操作
 */
public interface FileServiceProvider
{
    /**
     * 获取文件列表
     * 返回所有已上传的文件信息
     *
     * @return 文件列表响应结果
     */
    FileResponse listFiles();

    /**
     * 上传文件
     * 将文件上传到服务器
     *
     * @param configure 文件上传配置参数
     * @return 文件上传响应结果
     */
    FileEntity uploadFile(FileEntity configure);

    /**
     * 删除文件
     * 从服务器删除指定文件
     *
     * @param fileId 要删除的文件ID
     * @return 删除操作响应结果
     */
    FileResponse deleteFile(String fileId);

    /**
     * 获取文件信息
     * 获取指定文件的元数据信息
     *
     * @param fileId 文件ID
     * @return 文件信息响应结果
     */
    FileEntity getFile(String fileId);

    /**
     * 获取文件内容
     * 下载指定文件的实际内容
     *
     * @param fileId 文件ID
     * @return 文件内容
     */
    Object getFileContent(String fileId);
}
