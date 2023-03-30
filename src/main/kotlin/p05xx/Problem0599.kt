package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findRestaurant(list1: Array<String>, list2: Array<String>): Array<String> {
            var sum = 0
            while (true) {
                (0..sum).filter {
                    (list1.getOrNull(it) ?: return@filter false) == (list2.getOrNull(sum - it) ?: return@filter false)
                }.map { list1[it] }.takeIf { it.isNotEmpty() }?.also {
                    return it.toTypedArray()
                }

                sum++
            }
        }
    }

    measureTimeMillis {
        Solution().findRestaurant(
            arrayOf(), arrayOf()
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}