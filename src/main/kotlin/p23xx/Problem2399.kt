package p23xx

import util.expect

fun main() {
    class Solution {
        fun checkDistances(s: String, distance: IntArray): Boolean {
            val visited = hashSetOf<Int>()

            s.forEachIndexed { index, c ->
                val num = c - 'a'
                if (visited.add(num)) {
                    distance[num] += index + 1
                } else if (distance[num] != index) {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().checkDistances(
            "abaccb", intArrayOf(1, 3, 0)
        )
    }
}