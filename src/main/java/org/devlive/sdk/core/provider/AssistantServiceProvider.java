package org.devlive.sdk.core.provider;

import org.devlive.sdk.openai.entity.beta.AssistantsEntity;
import org.devlive.sdk.openai.entity.beta.QueryEntity;
import org.devlive.sdk.openai.response.beta.AssistantsResponse;

/**
 * Assistant服务提供者接口
 * 负责处理AI助手相关的操作
 */
public interface AssistantServiceProvider
{
    /**
     * 创建AI助手
     * 创建一个新的Assistant实例
     *
     * @param configure Assistant配置参数
     * @return Assistant创建响应结果
     */
    AssistantsEntity createAssistant(AssistantsEntity configure);

    /**
     * 获取AI助手列表
     * 返回所有可用的Assistant列表
     *
     * @param configure 查询配置参数
     * @return Assistant列表响应结果
     */
    AssistantsResponse listAssistants(QueryEntity configure);

    /**
     * 获取AI助手信息
     * 获取指定Assistant的详细信息
     *
     * @param assistantId Assistant ID
     * @return Assistant信息响应结果
     */
    AssistantsEntity getAssistant(String assistantId);

    /**
     * 更新AI助手
     * 更新指定Assistant的配置信息
     *
     * @param assistantId Assistant ID
     * @param configure 更新的配置参数
     * @return 更新操作响应结果
     */
    AssistantsEntity updateAssistant(String assistantId, AssistantsEntity configure);

    /**
     * 删除AI助手
     * 删除指定的Assistant实例
     *
     * @param assistantId Assistant ID
     * @return 删除操作响应结果
     */
    AssistantsResponse deleteAssistant(String assistantId);
}
