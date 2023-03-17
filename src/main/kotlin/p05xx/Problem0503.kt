package p05xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun nextGreaterElements(nums: IntArray): IntArray {
            val map = TreeMap<Int, MutableSet<Int>>()

            val result = IntArray(nums.size) { -1 }

            repeat(2) {
                nums.forEachIndexed { index, i ->
                    var t = map.lowerEntry(i)
                    while (t != null) {
                        t.value.forEach {
                            result[it] = i
                        }

                        map.remove(t.key)

                        t = map.lowerEntry(i)
                    }

                    if (result[index] == -1) {
                        map.computeIfAbsent(i) { hashSetOf() }.add(index)
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().nextGreaterElements(
            intArrayOf(1, 2, 1)
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}