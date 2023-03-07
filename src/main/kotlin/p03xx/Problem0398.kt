package p03xx

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    class Solution(nums: IntArray) {
        val indices = hashMapOf<Int, MutableList<Int>>()

        init {
            nums.forEachIndexed { index, i ->
                indices.computeIfAbsent(i) { arrayListOf() }.add(index)
            }
        }

        fun pick(target: Int): Int {
            return indices[target]?.let {
                it[Random.nextInt(it.size)]
            } ?: 0
        }
    }

    measureTimeMillis {
        Solution(intArrayOf()).pick(
            7
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


