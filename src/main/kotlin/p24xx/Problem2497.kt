package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxStarSum(vals: IntArray, edges: Array<IntArray>, k: Int): Int {
            val sums = IntArray(vals.size) { vals[it] }

            val adjacent = Array(vals.size) {
                PriorityQueue<Int>()
            }

            fun add(center: Int, value: Int) {
                if (value > 0) {
                    adjacent[center].add(value)
                    sums[center] += value

                    if (adjacent[center].size > k) {
                        sums[center] -= adjacent[center].poll()
                    }
                }
            }
            edges.forEach { (from, to) ->
                add(from, vals[to])
                add(to, vals[from])
            }

            return sums.max()
        }
    }

    expect {
        Solution().maxStarSum(
            intArrayOf(), arrayOf(), 1
        )
    }
}