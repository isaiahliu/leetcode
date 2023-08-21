package p04xx

import util.expect

fun main() {
    class Solution {
        fun canCross(stones: IntArray): Boolean {
            val stoneSet = stones.map { it.toLong() }.toSet()

            if (1 !in stoneSet) {
                return false
            }

            val target = stones[stones.lastIndex].toLong()

            val visited = hashSetOf<Pair<Long, Int>>()

            var success = false
            fun jump(from: Long, step: Int) {
                if (success) {
                    return
                }

                if (from == target) {
                    success = true
                    return
                }

                if (from > target) {
                    return
                }

                if (!visited.add(from to step)) {
                    return
                }

                (from + step + 1).takeIf { it in stoneSet }?.let {
                    jump(from + step + 1, step + 1)
                }

                (from + step).takeIf { it in stoneSet }?.let {
                    jump(from + step, step)
                }

                if (step > 1) {
                    (from + step - 1).takeIf { it in stoneSet }?.let {
                        jump(from + step - 1, step - 1)
                    }
                }
            }

            jump(1, 1)

            return success
        }
    }

    expect {
        Solution().canCross(
            intArrayOf(0, 1, 3, 5, 6, 8, 12, 17)
        )
    }
}


