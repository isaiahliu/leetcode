package p03xx

import kotlin.random.Random
import util.expect

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

    expect {
        Solution(intArrayOf()).pick(
            7
        )
    }
}


