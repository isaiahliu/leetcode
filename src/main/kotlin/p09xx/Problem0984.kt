package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun strWithout3a3b(a: Int, b: Int): String {
            val remainings = arrayOf(a, b)

            var current = 0
            if (remainings[1] > remainings[0]) {
                current++
            }

            var length = 0

            val str = StringBuilder()

            fun switch() {
                current = 1 - current
                length = 0
            }

            fun write() {
                str.append('a' + current)
                remainings[current]--
                length++
            }

            while (remainings[0] > 0 || remainings[1] > 0) {
                when {
                    length == 2 -> {
                        switch()
                        write()
                    }

                    remainings[0] > remainings[1] -> {
                        if (current == 1) {
                            switch()
                        }

                        write()
                    }

                    remainings[1] > remainings[0] -> {
                        if (current == 0) {
                            switch()
                        }

                        write()
                    }

                    else -> {
                        repeat(remainings[0]) {
                            str.append('a' + current)
                            str.append('a' + (1 - current))
                        }

                        remainings[0] = 0
                        remainings[1] = 0
                    }
                }
            }

            return str.toString()
        }
    }

    measureTimeMillis {
        Solution().strWithout3a3b(
            4, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
