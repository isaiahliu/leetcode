package p26xx

import util.expect

fun main() {
    class Solution {
        fun firstCompleteIndex(arr: IntArray, mat: Array<IntArray>): Int {
            val rowSize = mat.size
            val columnSize = mat[0].size

            val map = hashMapOf<Int, Pair<Int, Int>>()
            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    map[num] = r to c
                }
            }

            val rows = IntArray(rowSize) { columnSize }
            val columns = IntArray(columnSize) { rowSize }

            arr.forEachIndexed { index, num ->
                map[num]?.also { (r, c) ->
                    rows[r]--
                    columns[c]--

                    if (rows[r] == 0 || columns[c] == 0) {
                        return index
                    }
                }
            }

            return 0
        }
    }

    expect {
        Solution().firstCompleteIndex(
            intArrayOf(), arrayOf()
        )
    }
}