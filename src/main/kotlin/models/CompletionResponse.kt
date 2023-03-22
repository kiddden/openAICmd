package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionResponse(
    @SerialName("choices") val choices: List<Choice>
)

@Serializable
data class Choice(val text: String)
