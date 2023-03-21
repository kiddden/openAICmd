package services

import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

class OpenAIAPIService {
    private val apiKey = System.getenv("OPENAI_API_KEY") ?: throw RuntimeException("OPENAI_API_KEY environment variable is not set")
    private val openAI = OpenAI(apiKey)

    suspend fun generateCompletion(promt: String): String {
        val completionRequest = CompletionRequest(
            model = ModelId("text-davinci-003"),
            prompt = promt,
            maxTokens = 2048,
            topP = 0.5,
        )
        return openAI.completion(completionRequest).choices.first().text
    }
}
