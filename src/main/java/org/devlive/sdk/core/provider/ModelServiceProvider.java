package org.devlive.sdk.core.provider;

import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.response.ModelResponse;

/**
 * 模型服务提供者接口
 * 负责处理AI模型相关的操作，包括获取模型列表和模型详情
 */
public interface ModelServiceProvider
{
    /**
     * 获取可用模型列表
     * 返回所有当前可用的AI模型信息
     *
     * @return 包含模型列表的响应对象
     */
    ModelResponse listModels();

    /**
     * 获取指定模型的详细信息
     *
     * @param modelId 模型ID
     * @return 模型的详细信息
     */
    ModelEntity getModel(String modelId);
}
