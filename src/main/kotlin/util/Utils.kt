package util

import java.io.File
import kotlin.system.measureTimeMillis

val input by lazy {
    ClassLoader.getSystemResource("util/sample.txt")?.file?.let { File(it) }?.readLines().orEmpty()

}

fun expect(result: Any? = null, dsl: () -> Any?) {
    measureTimeMillis {
        dsl().also {
            if (result == null) {
                println("$it")
            } else {
                println("$it should be $result")
            }
        }
    }.also { println("Time cost: ${it}ms") }
}

