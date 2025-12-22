@file:OptIn(ExperimentalJsExport::class)

package dev.ghostflyby

@JsExport
fun fetch(): Array<String> {
    return arrayOf("Hello", "from", "Kotlin", "JS", "Multiplatform!")
}
