package p12xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPossibleDivide(nums: IntArray, k: Int): Boolean {
            if (k == 1) {
                return true
            }

            nums.sort()

            val map = hashMapOf<Int, LinkedList<Int>>()

            nums.forEach { num ->
                map[num - 1]?.also {
                    val length = it.poll() + 1

                    if (it.isEmpty()) {
                        map.remove(num - 1)
                    }

                    if (length < k) {
                        map.computeIfAbsent(num) { LinkedList() }.add(length)
                    }
                } ?: run {
                    map.computeIfAbsent(num) { LinkedList() }.add(1)
                }
            }

            return map.isEmpty()
        }
    }

    measureTimeMillis {
        Solution().isPossibleDivide(
            intArrayOf(1, 2, 2, 3, 3, 4, 4, 5), 4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
