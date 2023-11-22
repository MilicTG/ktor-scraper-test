package milic.dev

import it.skrape.fetcher.*

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import milic.dev.plugins.*

import it.skrape.core.htmlDocument
import it.skrape.selects.html5.*
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

suspend fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

    println("ovde")
    runBlocking {
        println("ovde")
        scrape()
    }
}

fun Application.module() {

    configureRouting()
}

private suspend fun scrape() =
    skrape(HttpFetcher) {
        request {
            url = "https://www.samostan-tomislavgrad.info"
        }.also { println("call ${it.preparedRequest.url} at ${LocalDateTime.now()}") }
        response {
            htmlDocument {
                a {
                    findAll {
                        map {
                            println(it.toString())
                        }
                    }
                }
            }
        }
    }
