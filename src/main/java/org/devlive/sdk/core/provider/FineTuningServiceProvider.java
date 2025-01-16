package org.devlive.sdk.core.provider;

import org.devlive.sdk.openai.entity.FineTuningEntity;
import org.devlive.sdk.openai.response.FineTuningResponse;

/**
 * 模型微调服务提供者接口
 * 负责处理模型微调相关的操作
 */
public interface FineTuningServiceProvider
{
    /**
     * 获取微调任务列表
     * 返回所有微调作业的信息
     *
     * @return 微调任务列表响应结果
     */
    FineTuningResponse listJobs();

    /**
     * 创建微调任务
     * 启动一个新的模型微调作业
     *
     * @param configure 微调配置参数
     * @return 微调任务创建响应结果
     */
    FineTuningResponse createJob(FineTuningEntity configure);

    /**
     * 获取任务事件
     * 获取指定微调任务的事件列表
     *
     * @param jobId 任务ID
     * @return 任务事件响应结果
     */
    FineTuningResponse getJobEvents(String jobId);

    /**
     * 获取任务信息
     * 获取指定微调任务的详细信息
     *
     * @param jobId 任务ID
     * @return 任务信息响应结果
     */
    FineTuningEntity getJob(String jobId);

    /**
     * 取消任务
     * 取消正在进行的微调任务
     *
     * @param jobId 任务ID
     * @return 取消操作响应结果
     */
    FineTuningEntity cancelJob(String jobId);
}
