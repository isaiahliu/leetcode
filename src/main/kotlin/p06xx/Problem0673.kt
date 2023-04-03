package p06xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findNumberOfLIS(nums: IntArray): Int {
            var maxLength = 1
            var result = nums.size

            val map = TreeMap<Int, IntArray>()

            nums.forEach {
                val list = map.computeIfAbsent(it) { intArrayOf(0, 0) }

                map.lowerEntry(it)?.also { (key, value) ->
                    var size = value[0]
                    var c = value[1]
                    var t = key
                    while (true) {
                        val e = map.lowerEntry(t)?.takeIf { it.value[0] == size } ?: break

                        c += e.value[1]

                        t = e.key
                    }

                    size++

                    if (size == list[0]) {
                        list[1] += c

                        if (size == maxLength) {
                            result += c
                        }
                    } else {
                        list[0] = size
                        list[1] = c

                        if (size > maxLength) {
                            maxLength = size
                            result = 0
                        }

                        if (size == maxLength) {
                            result += c
                        }
                    }

                    while (true) {
                        map.higherEntry(it)?.takeIf { it.value[0] < size }?.also {
                            map.remove(it.key)
                        } ?: break
                    }

                } ?: run {
                    list[0] = 1
                    list[1]++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findNumberOfLIS(
            intArrayOf(
                1, 5, 5, 10, 2, 2, 3, 20
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}