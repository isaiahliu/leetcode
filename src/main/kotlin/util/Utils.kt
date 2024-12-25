package util

import java.io.File
import kotlin.system.measureTimeMillis

val input by lazy {
    ClassLoader.getSystemResource("util/sample.txt")?.file?.let { File(it) }?.readLines().orEmpty()

}

private var testcase: Int = 1

fun expect(result: Any? = null, dsl: () -> Any?) {
    println("Testcase ${testcase++}: ")
    measureTimeMillis {
        dsl().also {
            val output = StringBuilder()

            output.append(
                when (it) {
                    is IntArray -> {
                        "${it.toList()}"
                    }

                    is LongArray -> {
                        "${it.toList()}"
                    }

                    is CharArray -> {
                        "${it.toList()}"
                    }

                    is DoubleArray -> {
                        "${it.toList()}"
                    }

                    is BooleanArray -> {
                        "${it.toList()}"
                    }

                    is Array<*> -> {
                        "${it.toList()}"
                    }

                    else -> {
                        "$it"
                    }
                }
            )

            if (result != null) {
                output.append(" should be $result")
            }

            println(output.toString())
        }
    }.also {
        println("Time cost: ${it}ms")
        println()
    }

    Thread.sleep(500L)
}

