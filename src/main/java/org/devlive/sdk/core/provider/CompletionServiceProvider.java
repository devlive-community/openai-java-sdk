package org.devlive.sdk.core.provider;

import org.devlive.sdk.openai.entity.ChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.response.ChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;

/**
 * 补全服务提供者接口
 * 负责处理文本补全和聊天补全相关的功能
 */
public interface CompletionServiceProvider
{
    /**
     * 创建文本补全请求
     * 根据给定的提示生成文本补全
     *
     * @param configure 补全配置参数
     * @return 补全响应结果
     */
    CompleteResponse createCompletion(CompletionEntity configure);

    /**
     * 创建聊天补全请求
     * 进行多轮对话形式的文本生成
     *
     * @param configure 聊天配置参数
     * @return 聊天响应结果
     */
    ChatResponse createChatCompletion(ChatEntity configure);
}
