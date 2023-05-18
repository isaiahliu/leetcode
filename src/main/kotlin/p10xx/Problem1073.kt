package p10xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun addNegabinary(arr1: IntArray, arr2: IntArray): IntArray {
            arr1.reverse()
            arr2.reverse()

            val result = LinkedList<Int>()

            var digitPos = 0
            var num = 0

            while (digitPos < arr1.size || digitPos < arr2.size || num != 0) {
                arr1.getOrNull(digitPos)?.also { num += it }
                arr2.getOrNull(digitPos)?.also { num += it }

                val t = num and 1
                result.push(t)

                num = (num - t) / -2
                digitPos++
            }

            while (result.size > 1 && result.peek() == 0) {
                result.pop()
            }

            return result.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().addNegabinary(
            intArrayOf(1), intArrayOf(1)
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
