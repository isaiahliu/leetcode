package p22xx

import util.expect

fun main() {
    class Solution {
        fun largestInteger(num: Int): Int {
            val str = num.toString()

            val odd = str.indices.filter { (str[it] - '0') % 2 == 1 }
            val oddIndices = odd.sortedByDescending { str[it] }
            val even = str.indices.filter { (str[it] - '0') % 2 == 0 }
            val evenIndices = even.sortedByDescending { str[it] }

            val result = CharArray(str.length)

            oddIndices.forEachIndexed { index, i ->
                result[odd[index]] = str[i]
            }

            evenIndices.forEachIndexed { index, i ->
                result[even[index]] = str[i]
            }

            return result.concatToString().toInt()
        }
    }

    expect {
        Solution().largestInteger(5)
    }
}