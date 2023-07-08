package p10xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun duplicateZeros(arr: IntArray): Unit {
            val nums = LinkedList<Int>()

            arr.forEachIndexed { index, i ->
                nums.add(i)

                if (i == 0) {
                    nums.add(i)
                }

                arr[index] = nums.pop()
            }
        }
    }

    measureTimeMillis {
        Solution().duplicateZeros(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
