@file:JsModule("cloudflare:workers")

package dev.ghostflyby.cloudflare.workers

import js.promise.Promise

external fun <T> waitUntil(promise: Promise<T>)
