package p14xx

import util.expect

fun main() {
    class Solution {
        fun numberOfArrays(s: String, k: Int): Int {
            val m = 1000000007

            val cache = hashMapOf<Int, Long>()

            fun dfs(index: Int): Long {
                if (index == s.length) {
                    return 1L
                }

                if (index in cache) {
                    return cache[index] ?: 0L
                }

                var result = 0L

                var num = (s[index] - '0').toLong()
                var length = 1
                while (num in 1..k) {
                    result += dfs(index + length)
                    result %= m

                    num *= 10
                    num += (s.getOrNull(index + length++) ?: break) - '0'
                }

                cache[index] = result

                return result
            }

            return dfs(0).toInt()
        }
    }

    expect {
        Solution().numberOfArrays(
            "407780786171321121429620765476840275495357129574174233567552131453038760763182952432348422252546559691171161181440370120987634895458140447952079749439961325982629462531738374032416182281868731817661954890417245087359968833257550123324827240537957646002494771036413572415",
            823924906
        )
    }
}

