package milic.dev

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import milic.dev.plugins.*

suspend fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

    val url = "https://www.samostan-tomislavgrad.info/"

    val client = HttpClient(CIO)
    val response: HttpResponse = client.get(url)
    client.close()

    val html = response.bodyAsText()

    println("ovde")
    println(response.status)
    println(html)
}

fun Application.module() {
    configureHTTP()
    configureSerialization()

    configureRouting()
}
