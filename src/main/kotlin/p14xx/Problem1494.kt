package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minNumberOfSemesters(n: Int, relations: Array<IntArray>, k: Int): Int {
            if (k == 1) {
                return n
            }

            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val parents = Array(n) {
                hashSetOf<Int>()
            }

            relations.forEach { (from, to) -> parents[to - 1].add(from - 1) }

            val cache = hashMapOf<Int, Int>()

            fun dfs(status: Int): Int {
                return when (status) {
                    in cache -> {
                        cache[status] ?: 0
                    }

                    0 -> {
                        0
                    }

                    else -> {
                        val valid = arrayListOf<Int>()
                        status.forEachBit {
                            if (parents[it].all { status and (1 shl it) == 0 }) {
                                valid.add(it)
                            }
                        }

                        val size = k.coerceAtMost(valid.size)

                        val pickedIndices = LinkedList<Int>()
                        pickedIndices.addAll(0 until size)

                        var result = Int.MAX_VALUE
                        val best = (Integer.bitCount(status) - size) / k

                        loop@ while (true) {
                            val learned = pickedIndices.map { 1 shl valid[it] }.sum()

                            result = result.coerceAtMost(dfs(status - learned))

                            if (result == best) {
                                break
                            }

                            while (true) {
                                var next = (pickedIndices.pollLast() ?: break@loop)

                                if (next + size - pickedIndices.size == valid.size) {
                                    continue
                                }

                                while (pickedIndices.size < size) {
                                    pickedIndices.add(++next)
                                }

                                break
                            }
                        }

                        cache[status] = result + 1

                        result + 1
                    }
                }
            }

            return dfs((1 shl n) - 1)
        }
    }

    measureTimeMillis {
        Solution().minNumberOfSemesters(
            9, arrayOf(
                intArrayOf(1, 5),
                intArrayOf(2, 5),
                intArrayOf(3, 5),
                intArrayOf(4, 6),
                intArrayOf(4, 7),
                intArrayOf(4, 8),
                intArrayOf(4, 9),
            ), 3
        ).also { println(it) }
    }
}

