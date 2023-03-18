import services.OpenAIAPIService
import kotlin.system.exitProcess

suspend fun main() {
    val openAIAPIService = OpenAIAPIService()

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
