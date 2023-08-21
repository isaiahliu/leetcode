package p24xx

import util.expect

fun main() {
    class Solution {
        fun sortPeople(names: Array<String>, heights: IntArray): Array<String> {
            return names.indices.sortedByDescending { heights[it] }.map { names[it] }.toTypedArray()
        }
    }

    expect {
        Solution().sortPeople(
            arrayOf(), intArrayOf()
        )
    }
}