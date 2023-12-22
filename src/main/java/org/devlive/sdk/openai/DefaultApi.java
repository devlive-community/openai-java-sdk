package org.devlive.sdk.openai;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.devlive.sdk.openai.entity.ChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.EditEntity;
import org.devlive.sdk.openai.entity.EmbeddingEntity;
import org.devlive.sdk.openai.entity.FileEntity;
import org.devlive.sdk.openai.entity.FineTuningEntity;
import org.devlive.sdk.openai.entity.ImageEntity;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.entity.ModerationEntity;
import org.devlive.sdk.openai.entity.UserKeyEntity;
import org.devlive.sdk.openai.response.AudioResponse;
import org.devlive.sdk.openai.response.ChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.response.EditResponse;
import org.devlive.sdk.openai.response.EmbeddingResponse;
import org.devlive.sdk.openai.response.FileResponse;
import org.devlive.sdk.openai.response.FineTuningResponse;
import org.devlive.sdk.openai.response.ImageResponse;
import org.devlive.sdk.openai.response.ModelResponse;
import org.devlive.sdk.openai.response.ModerationResponse;
import org.devlive.sdk.openai.response.UserKeyResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Url;

import java.util.Map;

public interface DefaultApi
{
    /**
     * Lists the currently available models
     */
    @GET
    Single<ModelResponse> fetchModels(@Url String url);

    /**
     * Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
     *
     * @param model The ID of the model to use for this request
     */
    @GET(value = "v1/models/{model}")
    Single<ModelEntity> fetchModel(@Path("model") String model);

    /**
     * Creates a completion for the provided prompt and parameters.
     */
    @POST
    Single<CompleteResponse> fetchCompletions(@Url String url,
            @Body CompletionEntity configure);

    /**
     * Fetches the completions for PaLM.
     *
     * @param url the URL to fetch the completions from
     * @param configure the configuration entity for the completions
     * @return the complete response containing the fetched completions
     */
    @POST
    Single<CompleteResponse> fetchPaLMCompletions(@Url String url,
            @Body org.devlive.sdk.openai.entity.google.CompletionEntity configure);

    /**
     * Fetches the PaLM Chat data from the specified URL.
     *
     * @param url the URL to fetch the data from
     * @param configure the configuration of the chat entity
     * @return a Single object representing the complete response
     */
    @POST
    Single<CompleteResponse> fetchPaLMChat(@Url String url,
            @Body org.devlive.sdk.openai.entity.google.ChatEntity configure);

    /**
     * Creates a model response for the given chat conversation.
     */
    @POST
    Single<ChatResponse> fetchChatCompletions(@Url String url,
            @Body ChatEntity configure);

    /**
     * Get all keys
     */
    @GET(value = "dashboard/user/api_keys")
    Single<UserKeyResponse> fetchUserAPIKeys();

    /**
     * Create a key for the given
     */
    @POST(value = "dashboard/user/api_keys")
    Single<UserKeyResponse> fetchCreateUserAPIKey(@Body UserKeyEntity configure);

    /**
     * Creates an image given a prompt.
     */
    @POST
    Single<ImageResponse> fetchImagesGenerations(@Url String url,
            @Body ImageEntity configure);

    /**
     * Creates an edited or extended image given an original image and a prompt.
     */
    @POST
    @Multipart
    Single<ImageResponse> fetchImagesEdits(@Url String url,
            @Part() MultipartBody.Part image,
            @Part() MultipartBody.Part mask,
            @PartMap Map<String, RequestBody> configure);

    /**
     * Creates a variation of a given image.
     */
    @POST
    @Multipart
    Single<ImageResponse> fetchImagesVariations(@Url String url,
            @Part() MultipartBody.Part image,
            @PartMap Map<String, RequestBody> configure);

    /**
     * Creates an embedding vector representing the input text.
     */
    @POST
    Single<EmbeddingResponse> fetchEmbeddings(@Url String url,
            @Body EmbeddingEntity configure);

    /**
     * Transcribes audio into the input language.
     * 将音频转录为输入语言。
     */
    @POST
    @Multipart
    Single<AudioResponse> fetchAudioTranscriptions(@Url String url,
            @Part() MultipartBody.Part audio,
            @PartMap Map<String, RequestBody> configure);

    /**
     * Classifies if text violates OpenAI's Content Policy
     * 对文本是否违反 OpenAI 的内容政策进行分类
     */
    @POST
    Single<ModerationResponse> fetchModerations(@Url String url,
            @Body ModerationEntity configure);

    /**
     * Creates a new edit for the provided input, instruction, and parameters.
     * 为提供的输入、指令和参数创建新的编辑。
     */
    @POST
    Single<EditResponse> fetchEdits(@Url String url,
            @Body EditEntity configure);

    /**
     * Returns a list of files that belong to the user's organization.
     * 返回属于用户组织的文件列表。
     */
    @GET
    Single<FileResponse> fetchFiles(@Url String url);

    /**
     * Upload a file that contains document(s) to be used across various endpoints/features. Currently, the size of all the files uploaded by one organization can be up to 1 GB. Please contact us if you need to increase the storage limit.
     * 上传包含要在各种端点/功能之间使用的文档的文件。目前，一个组织上传的所有文件大小最大可达1GB。如果您需要增加存储限制，请联系我们。
     */
    @POST
    @Multipart
    Single<FileEntity> fetchUploadFile(@Url String url,
            @Part() MultipartBody.Part file,
            @PartMap Map<String, RequestBody> configure);

    /**
     * Delete a file.
     * 删除文件。
     */
    @DELETE
    Single<FileResponse> fetchDeleteFile(@Url String url);

    /**
     * Returns information about a specific file.
     * 返回有关特定文件的信息。
     */
    @GET
    Single<FileEntity> fetchRetrieveFile(@Url String url);

    /**
     * Returns the contents of the specified file
     * 返回指定文件的内容
     */
    @GET
    Single<Object> fetchRetrieveFileContent(@Url String url);

    /**
     * List your organization's fine-tuning jobs
     * 列出组织的微调作业
     */
    @GET
    Single<FineTuningResponse> fetchFineTuningJobs(@Url String url);

    /**
     * Creates a job that fine-tunes a specified model from a given dataset.
     * 创建一个作业，用于微调给定数据集中的指定模型。
     */
    @POST
    Single<FineTuningResponse> fetchCreateFineTuningJob(@Url String url,
            @Body FineTuningEntity configure);

    /**
     * Get status updates for a fine-tuning job.
     * 获取微调作业的状态更新。
     */
    @GET
    Single<FineTuningResponse> fetchFineTuningJobEvents(@Url String url);
}
