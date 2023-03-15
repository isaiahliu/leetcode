package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun magicalString(n: Int): Int {
            var result = 1
            if (n <= 3) {
                return result
            }

            val array = IntArray(n)

            array[0] = 1
            array[1] = 2
            array[2] = 2
            var index = 2
            var writeIndex = 3
            var writeNum = 1

            while (writeIndex < n) {
                var writeCount = array[index++]

                while (writeCount-- > 0 && writeIndex < n) {
                    array[writeIndex++] = writeNum

                    if (writeNum == 1) {
                        result++
                    }
                }

                writeNum = 3 - writeNum
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().magicalString(
            6
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}