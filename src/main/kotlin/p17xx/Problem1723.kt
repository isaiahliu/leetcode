package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        inline fun Int.forEachBit(consumer: (Int) -> Unit) {
            var t = this

            var index = 0
            while (t > 0) {
                if (t % 2 == 1) {
                    consumer(index)
                }

                t /= 2
                index++
            }
        }

        fun minimumTimeRequired(jobs: IntArray, k: Int): Int {
            jobs.sortDescending()

            val sum = jobs.sum()

            if (k == 1) {
                return sum
            }


            fun binarySearch(start: Int, end: Int): Int? {
                if (start > end) {
                    return null
                }

                val mid = (start + end) / 2

                fun dfs(status: Int, sum: Int, remain: Int): Boolean {
                    return when {
                        status == 0 -> {
                            true
                        }

                        remain == 0 -> {
                            false
                        }

                        sum == 0 -> {
                            var initStatus = status
                            var initSum = 0
                            Integer.lowestOneBit(status).forEachBit {
                                initStatus -= 1 shl it
                                initSum += jobs[it]
                            }

                            dfs(initStatus, initSum, remain)
                        }

                        else -> {
                            status.forEachBit {
                                val jobCost = jobs[it]

                                if (sum + jobCost <= mid) {
                                    if (it == 0 || status and (1 shl (it - 1)) == 0 || jobs[it - 1] != jobCost) {
                                        if (dfs(status - (1 shl it), sum + jobCost, remain)) {
                                            return true
                                        }
                                    }
                                }
                            }

                            dfs(status, 0, remain - 1)
                        }
                    }
                }

                return if (dfs((1 shl (jobs.size)) - 1, 0, k)) {
                    binarySearch(start, mid - 1) ?: mid
                } else {
                    binarySearch(mid + 1, end)
                }
            }

            return binarySearch(jobs.max().coerceAtLeast(sum / k), sum) ?: 0
        }
    }

    measureTimeMillis {
        Solution().minimumTimeRequired(
            intArrayOf(
                3, 2, 3
            ), 3
        ).also { println("${it} should be 3") }

        Solution().minimumTimeRequired(
            intArrayOf(
                5129437,
                1842905,
                2200851,
                597069,
                8618690,
                5823974,
                3199380,
                2416829,
                7138804,
                2445093,
                7207875,
                7133141
            ), 3
        ).also { println("${it} should be 17952682") }
    }.also { println("Time cost: ${it}ms") }
}
