package services

import models.CompletionRequest
import models.CompletionResponse

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class OpenAIAPIService(private val apiKey: String) {
    private val apiUrl = "https://api.openai.com/v1/completions"

    init {
        FuelManager.instance.baseHeaders = mapOf("Authorization" to "Bearer $apiKey")
    }

    suspend fun generateCompletion(prompt: String): String {
        val completionRequest = CompletionRequest(
            model = "text-davinci-003",
            prompt = prompt,
            maxTokens = 2048,
            topP = 0.3
        )

        val json = Json { ignoreUnknownKeys = true }
        val requestBody = json.encodeToString(completionRequest)
        val (_, response, result) = apiUrl
            .httpPost()
            .header(Headers.CONTENT_TYPE, "application/json")
            .header("Authorization", "Bearer $apiKey")
            .body(requestBody)
            .awaitStringResponseResult()

        return when (result) {
            is Result.Success -> {
                val responseObject = json.decodeFromString<CompletionResponse>(result.get())
                responseObject.choices.first().text
            }
            is Result.Failure -> {
                println("Error response: ${response.body().asString("application/json")}")
                throw RuntimeException("Failed to generate completion: ${response.responseMessage}")
            }
        }
    }
}
