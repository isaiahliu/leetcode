package p19xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canSeePersonsCount(heights: IntArray): IntArray {
            val result = IntArray(heights.size)

            val list = LinkedList<Int>()

            list.push(heights[heights.lastIndex])

            for (index in heights.lastIndex - 1 downTo 0) {
                val h = heights[index]

                var r = 0
                while (list.isNotEmpty() && h > list.peek()) {
                    list.poll()
                    r++
                }

                if (list.isNotEmpty()) {
                    r++
                }

                list.push(h)
                result[index] = r
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().canSeePersonsCount(
            intArrayOf(10, 6, 8, 5, 11, 9)
        ).toList().also { println("${it} should be 3,1,2,1,1,0") }
    }.also { println("Time cost: ${it}ms") }
}