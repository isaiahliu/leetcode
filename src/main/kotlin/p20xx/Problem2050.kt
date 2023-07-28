package p20xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumTime(n: Int, relations: Array<IntArray>, time: IntArray): Int {
            val start = IntArray(n)
            val parentCount = IntArray(n)
            val children = hashMapOf<Int, MutableSet<Int>>()

            val roots = (0 until n).toMutableSet()

            relations.forEach { (prev, next) ->
                roots.remove(next - 1)
                children.computeIfAbsent(prev - 1) {
                    hashSetOf()
                }.add(next - 1)
                parentCount[next - 1]++
            }

            val list = LinkedList(roots)
            var result = 0

            while (true) {
                val root = list.pollFirst() ?: break
                val finishTime = start[root] + time[root]

                children[root]?.forEach {
                    start[it] = start[it].coerceAtLeast(finishTime)

                    if (--parentCount[it] == 0) {
                        list.add(it)
                    }
                } ?: run {
                    result = result.coerceAtLeast(finishTime)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minimumTime(
            5, arrayOf(
                intArrayOf(1, 5),
                intArrayOf(2, 5),
                intArrayOf(3, 5),
                intArrayOf(3, 4),
                intArrayOf(4, 5),
            ), intArrayOf(1, 2, 3, 4, 5)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

