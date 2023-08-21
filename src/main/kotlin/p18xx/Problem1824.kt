package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minSideJumps(obstacles: IntArray): Int {
            val stones = Array(3) {
                TreeSet<Int>()
            }

            obstacles.forEachIndexed { index, i ->
                if (i > 0) {
                    stones[i - 1].add(index)
                }
            }
            var result = 0
            var runway = 1
            var pos = 0

            while (true) {
                stones[runway].higher(pos)?.also { stone ->
                    result++

                    var bestRunway = 0
                    var distance = 1
                    stones.forEachIndexed { index, s ->
                        s.ceiling(stone - 1)?.also {
                            if (it > distance) {
                                distance = it
                                bestRunway = index
                            }
                        } ?: return result

                        runway = bestRunway
                        pos = stone - 1
                    }

                } ?: return result
            }
        }
    }

    expect {
        Solution().minSideJumps(
            intArrayOf()
        )
    }
}
