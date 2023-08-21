package p00xx

import util.expect

fun main() {
    class Solution {
        fun permuteUnique(nums: IntArray): List<List<Int>> {
            val all = (1 shl nums.size) - 1

            val result = arrayListOf<List<Int>>()

            fun walk(used: Int, route: List<Int>) {
                if (used == all) {
                    result.add(route)
                    return
                }

                val visited = hashSetOf<Int>()
                repeat(nums.size) {
                    if (used and (1 shl it) == 0 && visited.add(nums[it])) {
                        walk(used + (1 shl it), route + nums[it])
                    }
                }
            }

            walk(0, emptyList())

            return result
        }
    }

    expect {
        Solution().permuteUnique(intArrayOf(1, 2, 3))
    }
}


