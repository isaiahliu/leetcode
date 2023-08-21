package p18xx

import java.math.BigInteger
import util.expect

fun main() {
    class Solution {
        fun splitString(s: String): Boolean {
            fun dfs(index: Int, prevNum: BigInteger): Boolean {
                if (index == s.length) {
                    return true
                }

                if (prevNum == BigInteger.ZERO) {
                    return false
                }

                val target = prevNum - BigInteger.ONE

                for (i in index until s.length) {
                    if (s.substring(index..i).toBigInteger() == target && dfs(i + 1, target)) {
                        return true
                    }
                }

                return false
            }

            for (i in 0 until s.lastIndex) {
                if (dfs(i + 1, s.substring(0..i).toBigInteger())) {
                    return true
                }
            }

            return false
        }
    }

    expect {
        Solution().splitString(
            ""
        )

    }
}
