package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileEntity
{
    /**
     * The file identifier, which can be referenced in the API endpoints.
     * 文件标识符,可以在 API 接口中引用。
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * The object type, which is always "file".
     * 对象类型，始终为 "file"
     */
    @JsonProperty(value = "object")
    private String object;

    /**
     * The size of the file in bytes.
     * 文件的字节大小
     */
    @JsonProperty(value = "bytes")
    private Integer bytes;

    /**
     * The unix timestamp for when the file was created.
     * 文件创建的 Unix 时间戳，默认转换为 UTC 时间。
     */
    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdTime;

    /**
     * The name of the file.
     * 文件名
     */
    @JsonProperty(value = "filename")
    private String filename;

    /**
     * The intended purpose of the file. Currently, only "fine-tune" is supported.
     * 文件的预期用途。目前只支持 "fine-tune"。
     */
    @JsonProperty(value = "purpose")
    private String purpose;

    /**
     * The current status of the file, which can be either uploaded, processed, pending, error, deleting or deleted.
     * 文件的当前状态，可以是"uploaded"（已上传）、"processed"（处理中）、"pending"（挂起）、"error"（错误）、"deleting"（删除中）或"deleted"（已删除）。
     */
    @JsonProperty(value = "status")
    private String status;

    /**
     * Additional details about the status of the file. If the file is in the error state, this will include a message describing the error.
     * 关于文件状态的额外详细信息。如果文件处于错误状态，这将包括描述错误的消息。
     */
    @JsonProperty(value = "status_details")
    private String statusDetails;
}
