package p13xx

import util.expect

fun main() {
    class Solution {
        fun kWeakestRows(mat: Array<IntArray>, k: Int): IntArray {
            fun IntArray.countSoldiers(): Int {
                var result = 0
                for (n in this) {
                    if (n == 0) {
                        break
                    }
                    result++
                }

                return result
            }

            return mat.mapIndexed { index, arr -> arr.countSoldiers() to index }
                .sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }).take(k).map { it.second }
                .toIntArray()
        }
    }

    expect {
        Solution().kWeakestRows(
            arrayOf(), 1
        )
    }
}

