@file:OptIn(ExperimentalJsExport::class, ExperimentalJsStatic::class)

package dev.ghostflyby


import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import js.coroutines.asPromise
import js.promise.Promise
import web.http.Request
import web.http.Response

@JsModule("node:os")
external val os: dynamic

@JsModule("node:fs")
external val fs: dynamic

@JsModule("node:path")
external val path: dynamic

fun fakeRequire(module: String): dynamic {
    when (module) {
        "os" -> return os
        "fs" -> return fs
        "path" -> return path
    }
    throw Error("Cannot require module: $module")
}

fun fakeEval(s: String): dynamic {
    if (s == "require") return ::fakeRequire
    throw Error("Cannot eval: $s")
}

val a = run {
    js("globalThis").eval = ::fakeEval
}

val server = embeddedServer(CFWorker) {
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
fun fetch(request: Request): Promise<Response> {
    return server.engine.handle(request).asPromise()
}