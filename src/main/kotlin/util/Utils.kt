package util

import java.io.File
import kotlin.system.measureTimeMillis

val input by lazy {
    ClassLoader.getSystemResource("util/sample.txt")?.file?.let { File(it) }?.readLines().orEmpty()

}

fun expect(result: Any? = null, dsl: () -> Any?) {
    measureTimeMillis {
        dsl.also { println("$it should be ${result ?: it}") }
    }.also { println("Time cost: ${it}ms") }
}

