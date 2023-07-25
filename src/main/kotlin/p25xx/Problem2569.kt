package p25xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun handleQuery(nums1: IntArray, nums2: IntArray, queries: Array<IntArray>): LongArray {
            val result = arrayListOf<Long>()

            var sum = nums2.fold(0L) { a, b -> a + b }
            var count = nums1.sum().toLong()

            val map = TreeMap<Int, Int>()

//            println(nums1.joinToString(" "))
            var start = 0
            var end = -1
            nums1.forEachIndexed { index, i ->
                if (i == 0) {
                    if (start <= end) {
                        map[start] = end
                    }

                    start = index + 1
                } else {
                    end = index
                }
            }

            if (start <= end) {
                map[start] = end
            }

            queries.forEach { (op, param1, param2) ->
                when (op) {
                    1 -> {
                        start = param1
                        map.lowerEntry(param1)?.also {
                            if (it.value >= param1) {
                                map[it.key] = param1 - 1
                                start = it.value + 1
                                count -= it.value - param1 + 1

                                if (it.value > param2) {
                                    map[param2 + 1] = it.value
                                    count += it.value - param2
                                }
                            }
                        }

                        while (true) {
                            val (s, e) = map.ceilingEntry(start)?.takeIf { it.key <= param2 } ?: break

                            if (s > start) {
                                map.lowerEntry(start)?.takeIf { it.value == start - 1 }?.also {
                                    map[it.key] = s - 1
                                } ?: run {
                                    map[start] = s - 1
                                }
                                count += s - start
                            }

                            map.remove(s)
                            if (e <= param2) {
                                start = e + 1
                                count -= e - s + 1
                            } else {
                                map[param2 + 1] = e
                                start = param2 + 1
                                count -= param2 - s + 1
                            }
                        }

                        if (start <= param2) {
                            map.lowerEntry(start)?.takeIf { it.value == start - 1 }?.also {
                                map[it.key] = param2
                            } ?: run { map[start] = param2 }
                            count += param2 - start + 1
                        }

                        map[param2 + 1]?.also { e ->
                            map.lowerEntry(param2 + 1)?.takeIf { it.value == param2 }?.also {
                                map.remove(param2 + 1)
                                map[it.key] = e
                            }
                        }

//                        val debug = IntArray(nums1.size)
//                        map.forEach { (t, u) -> (t..u).forEach { debug[it]++ } }
//                        println("$param1, $param2")
//                        println(debug.joinToString(" "))
//                        println(count)
//                        println()
                    }

                    2 -> {
                        sum += count * param1
                    }

                    3 -> {
                        result.add(sum)
                    }
                }
            }

            return result.toLongArray()
        }
    }

    measureTimeMillis {
        Solution().handleQuery(
            intArrayOf(1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0),
            intArrayOf(
                4,
                33,
                4,
                8,
                19,
                48,
                21,
                9,
                23,
                33,
                36,
                43,
                47,
                48,
                18,
                30,
                38,
                1,
                47,
                19,
                21,
                31,
                19,
                24,
                3,
                41
            ),
            arrayOf(
                intArrayOf(1, 9, 19),
                intArrayOf(1, 1, 16),
                intArrayOf(2, 5, 0),
                intArrayOf(2, 29, 0),
                intArrayOf(3, 0, 0)
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

