import services.OpenAIAPIService
import kotlin.system.exitProcess

suspend fun main() {
    val apiKey = System.getenv("OPENAI_API_KEY") ?: throw RuntimeException("OPENAI_API_KEY environment variable is not set")
    val openAIAPIService = OpenAIAPIService(apiKey)

    while (true) {
        print("You: ")
        val prompt = readlnOrNull()
        if (prompt == "exit") {
            println("System: Bye-bye")
            exitProcess(1)
        } else {
            prompt?.let {
                val response = openAIAPIService.generateCompletion(prompt).trim()
                println("Chatbot: $response")
            }
        }
    }
}
