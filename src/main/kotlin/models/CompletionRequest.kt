package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionRequest(
    @SerialName("model") val model: String,
    @SerialName("prompt") val prompt: String,
    @SerialName("max_tokens") val maxTokens: Int,
    @SerialName("top_p") val topP: Double
)
