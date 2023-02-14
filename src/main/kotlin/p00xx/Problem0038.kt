package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countAndSay(n: Int): String {
            fun process(str: String): String {
                val result = StringBuilder()

                var last = str[0]
                var count = 0
                ("$str ").forEach {
                    if (it == last) {
                        count++
                    } else {
                        result.append(count.toString())
                        result.append(last)

                        last = it
                        count = 1
                    }
                }

                return result.toString()
            }

            var t = "1"

            repeat(n - 1) {
                t = process(t)
            }

            return t
        }
    }

    measureTimeMillis {
        println(Solution().countAndSay(1))
    }.also { println("Time cost: ${it}ms") }
}


