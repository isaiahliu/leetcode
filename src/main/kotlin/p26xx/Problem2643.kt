package p26xx

import util.expect

fun main() {
    class Solution {
        fun rowAndMaximumOnes(mat: Array<IntArray>): IntArray {
            return mat.indices.maxBy { mat[it].sum() }.let { intArrayOf(it, mat[it].sum()) }
        }
    }

    expect {
        Solution().rowAndMaximumOnes(
            arrayOf(intArrayOf(1, 0))
        )
    }
}