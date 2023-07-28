package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class BrowserHistory(homepage: String) {
        val history = arrayListOf(homepage)

        var readIndex = 0
        var writeIndex = 1

        fun visit(url: String) {
            readIndex++

            if (readIndex == history.size) {
                history.add(url)
            } else {
                history[readIndex] = url
            }

            writeIndex = readIndex + 1
        }

        fun back(steps: Int): String {
            readIndex = (readIndex - steps).coerceAtLeast(0)

            return history[readIndex]
        }

        fun forward(steps: Int): String {
            readIndex = (readIndex + steps).coerceAtMost(writeIndex - 1)

            return history[readIndex]
        }
    }

    measureTimeMillis {
        BrowserHistory("").visit(
            ""
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

