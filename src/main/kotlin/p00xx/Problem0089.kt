package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun grayCode(n: Int): List<Int> {
            val result = arrayListOf(0)

            fun process(bitPos: Int, baseNum: Int): Int {
                val t = 1 shl bitPos

                var newNum = baseNum xor t

                result.add(newNum)

                repeat(bitPos) {
                    newNum = process(it, newNum)
                }
                return newNum
            }

            var t = 0
            repeat(n) {
                t = process(it, t)
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().grayCode(
            4
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

