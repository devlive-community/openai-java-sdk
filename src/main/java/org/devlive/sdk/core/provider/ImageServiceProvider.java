package org.devlive.sdk.core.provider;

import org.devlive.sdk.openai.entity.ImageEntity;
import org.devlive.sdk.openai.response.ImageResponse;

/**
 * 图像服务提供者接口
 * 负责处理图像生成、编辑和变体创建相关的功能
 */
public interface ImageServiceProvider
{
    /**
     * 创建图像
     * 根据文本描述生成新的图像
     *
     * @param configure 图像生成配置参数
     * @return 图像生成响应结果
     */
    ImageResponse createImage(ImageEntity configure);

    /**
     * 编辑图像
     * 对已有图像进行编辑修改
     *
     * @param configure 图像编辑配置参数
     * @return 图像编辑响应结果
     */
    ImageResponse editImage(ImageEntity configure);

    /**
     * 创建图像变体
     * 基于输入图像生成相似但不同的新图像
     *
     * @param configure 变体生成配置参数
     * @return 图像变体响应结果
     */
    ImageResponse createImageVariations(ImageEntity configure);
}
