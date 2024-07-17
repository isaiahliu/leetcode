package p29xx

import util.expect

fun main() {
    class Solution {
        fun numberOfSets(n: Int, maxDistance: Int, roads: Array<IntArray>): Int {
            var result = 0

            for (mask in 0 until (1 shl n)) {
                val opened = IntArray(n) {
                    mask and (1 shl it)
                }
                val d = Array(n) { IntArray(n) { 1000000 } }
                for ((i, j, r) in roads) {
                    if (opened[i] > 0 && opened[j] > 0) {
                        d[j][i] = minOf(d[i][j], r)
                        d[i][j] = d[j][i]
                    }
                }

                for (k in 0 until n) {
                    if (opened[k] > 0) {
                        for (i in 0 until n) {
                            if (opened[i] > 0) {
                                for (j in i + 1 until n) {
                                    if (opened[j] > 0) {
                                        d[j][i] = minOf(d[i][j], (d[i][k] + d[k][j]))
                                        d[i][j] = d[j][i]
                                    }
                                }
                            }
                        }
                    }
                }

                // Validate
                var good = 1
                for (i in 0 until n) {
                    if (opened[i] > 0) {
                        for (j in i + 1 until n) {
                            if (opened[j] > 0) {
                                if (d[i][j] > maxDistance) {
                                    good = 0
                                    break
                                }
                            }
                        }
                        if (good == 0) {
                            break
                        }
                    }
                }
                result += good
            }
            return result
        }
    }
    expect {
        Solution().numberOfSets(
            0, 0, arrayOf()
        )
    }
}
