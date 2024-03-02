---
title: Fine Tuning
---

!!! Note

    Please build the client before calling, the build code is as follows:

    ```java
    OpenAiClient client = OpenAiClient.builder()
            .apiHost("https://api.openai.com")
            .apiKey(System.getProperty("openai.token"))
            .build();
    ```

    `System.getProperty("openai.token")` is the key to access the API authorization.

### Create fine-tuning job

---

Creates a job that fine-tunes a specified model from a given dataset.

```java
FineTuningEntity entity = FineTuningEntity.builder()
        .file("file-Rcxv6RrQXzTuJ2i2LY9kuChL")
        .build();
client.createFineTuningJob(entity);
```

Returns:

```json
{
  "object": "fine_tuning.job",
  "id": "ftjob-abc123",
  "model": "gpt-3.5-turbo-0613",
  "created_at": 1614807352,
  "fine_tuned_model": null,
  "organization_id": "org-123",
  "result_files": [],
  "status": "queued",
  "validation_file": null,
  "training_file": "file-abc123",
}
```

### List fine-tuning jobs

---

List your organization's fine-tuning jobs

```java
client.fineTuningJobs();
```

Returns:

```json
{
  "object": "list",
  "data": [
    {
      "object": "fine_tuning.job.event",
      "id": "ft-event-TjX0lMfOniCZX64t9PUQT5hn",
      "created_at": 1689813489,
      "level": "warn",
      "message": "Fine tuning process stopping due to job cancellation",
      "data": null,
      "type": "message"
    },
    { ... },
    { ... }
  ], "has_more": true
}
```

### List fine-tuning events

---

Get status updates for a fine-tuning job.

```java
client.fineTuningJobEvents("ftjob-abc123");
```

Returns:

```json
{
  "object": "list",
  "data": [
    {
      "object": "fine_tuning.job.event",
      "id": "ft-event-ddTJfwuMVpfLXseO0Am0Gqjm",
      "created_at": 1692407401,
      "level": "info",
      "message": "Fine tuning job successfully completed",
      "data": null,
      "type": "message"
    },
    {
      "object": "fine_tuning.job.event",
      "id": "ft-event-tyiGuB72evQncpH87xe505Sv",
      "created_at": 1692407400,
      "level": "info",
      "message": "New fine-tuned model created: ft:gpt-3.5-turbo:openai::7p4lURel",
      "data": null,
      "type": "message"
    }
  ],
  "has_more": true
}
```

### Retrieve fine-tuning job

---

Get info about a fine-tuning job.

```java
client.retrieveFineTuningJob("ftjob-abc123");
```

Returns:

```json
{
  "object": "fine_tuning.job",
  "id": "ftjob-abc123",
  "model": "davinci-002",
  "created_at": 1692661014,
  "finished_at": 1692661190,
  "fine_tuned_model": "ft:davinci-002:my-org:custom_suffix:7q8mpxmy",
  "organization_id": "org-123",
  "result_files": [
    "file-abc123"
  ],
  "status": "succeeded",
  "validation_file": null,
  "training_file": "file-abc123",
  "hyperparameters": {
    "n_epochs": 4,
  },
  "trained_tokens": 5768
}
```

### Cancel fine-tuning

---

Immediately cancel a fine-tune job.

```java
client.cancelFineTuningJob("ftjob-abc123");
```

Returns:

```json
{
  "object": "fine_tuning.job",
  "id": "ftjob-abc123",
  "model": "gpt-3.5-turbo-0613",
  "created_at": 1689376978,
  "fine_tuned_model": null,
  "organization_id": "org-123",
  "result_files": [],
  "hyperparameters": {
    "n_epochs":  "auto"
  },
  "status": "cancelled",
  "validation_file": "file-abc123",
  "training_file": "file-abc123"
}
```
