package p19xx

import util.expect

fun main() {
    class Solution {
        fun findFarmland(land: Array<IntArray>): Array<IntArray> {
            val sums = Array(land.size) { r ->
                Array(land[r].size) { c ->
                    arrayOf(land[r][c], land[r][c])
                }
            }

            val result = hashMapOf<Pair<Int, Int>, Pair<Int, Int>>()
            sums.forEachIndexed { r, row ->
                row.forEachIndexed { c, sum ->
                    if (land[r][c] == 1) {
                        sum[0] += sums.getOrNull(r - 1)?.get(c)?.get(0) ?: 0
                        sum[1] += sums[r].getOrNull(c - 1)?.get(1) ?: 0

                        result[r - sum[0] + 1 to c - sum[1] + 1] = r to c
                    }
                }
            }

            return result.map { (key, value) ->
                intArrayOf(key.first, key.second, value.first, value.second)
            }.toTypedArray()
        }
    }

    expect {
        Solution().findFarmland(
            arrayOf()
        )
    }
}