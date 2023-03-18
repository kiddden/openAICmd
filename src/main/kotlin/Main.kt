import services.OpenAIAPIService

suspend fun main() {
    val openAIAPIService = OpenAIAPIService()

    while (true) {
        print("You: ")
        val prompt = readlnOrNull()
        if (prompt == "exit") {
            break
        } else {
            prompt?.let {
                val response = openAIAPIService.generateCompletion(prompt).trim()
                println("Chatbot: $response")
            }
        }
    }
}
