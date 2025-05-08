package p33xx

import util.expect
import kotlin.math.max
import kotlin.math.min

fun main() {
    class Solution {
        fun countBalancedPermutations(num: String): Int {
            val m = 1000000007L
            var sum = 0
            val counts = IntArray(10)
            for (ch in num.toCharArray()) {
                val n = ch - '0'
                counts[n]++
                sum += n
            }

            if (sum % 2 != 0) {
                return 0
            }

            val target = sum / 2
            val maxOdd = (num.length + 1) / 2
            val comb = Array(maxOdd + 1) { LongArray(maxOdd + 1) }
            val dp = Array(target + 1) { LongArray(maxOdd + 1) }

            for (i in 0..maxOdd) {
                comb[i][0] = 1
                comb[i][i] = comb[i][0]
                for (j in 1..<i) {
                    comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % m
                }
            }

            dp[0][0] = 1
            var psum = 0
            var totSum = 0
            for (i in 0..9) {
                psum += counts[i]

                totSum += i * counts[i]
                for (oddCnt in min(psum, maxOdd) downTo max(0, psum - (num.length - maxOdd))) {
                    val evenCnt = psum - oddCnt
                    for (curr in min(totSum, target) downTo max(0, totSum - target)) {
                        var result: Long = 0
                        var j = max(0, counts[i] - evenCnt)
                        while (j <= min(counts[i], oddCnt) && i * j <= curr) {
                            val ways = comb[oddCnt][j] * comb[evenCnt][counts[i] - j] % m
                            result = (result + ways * dp[curr - i * j][oddCnt - j] % m) % m
                            j++
                        }
                        dp[curr][oddCnt] = result % m
                    }
                }
            }

            return dp[target][maxOdd].toInt()
        }
    }

    expect {
        Solution().countBalancedPermutations(
            "741135580117173124433125648556051750629099528475166106140687809811527402"
        )
    }
}
