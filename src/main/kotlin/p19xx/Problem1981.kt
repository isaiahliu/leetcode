package p19xx

import util.expect

fun main() {
    class Solution {
        fun minimizeTheDifference(mat: Array<IntArray>, target: Int): Int {
            val nums = mat[0].toMutableSet()

            for (i in 1 until mat.size) {
                var minExceedNum = Int.MAX_VALUE
                nums.toSet().also { nums.clear() }.forEach { n1 ->
                    mat[i].forEach { n2 ->
                        (n1 + n2).also {
                            if (it > target) {
                                minExceedNum = minExceedNum.coerceAtMost(it)
                            } else {
                                nums.add(it)
                            }
                        }
                    }
                }

                if (minExceedNum < Int.MAX_VALUE) {
                    nums.add(minExceedNum)
                }
            }

            return nums.map {
                if (it > target) {
                    it - target
                } else {
                    target - it
                }
            }.min()
        }
    }

    expect {
        Solution().minimizeTheDifference(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 2, 3),
                intArrayOf(1, 2, 3),
            ), 1
        )
    }
}