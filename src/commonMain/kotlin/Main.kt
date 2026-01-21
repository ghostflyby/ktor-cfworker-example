@file:OptIn(ExperimentalJsExport::class, ExperimentalJsStatic::class)

package dev.ghostflyby


import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import web.http.Request
import web.http.Response

val server = embeddedServer(CFWorker) {
    serverConfig {
        watchPaths = emptyList()
    }
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
        post("/echo") {
            val receivedText = call.receiveText()
            call.respondText("Echo: $receivedText")
        }
    }
}

@JsExport
suspend fun fetch(request: Request): Response {
    return server.engine.handle(request)
}