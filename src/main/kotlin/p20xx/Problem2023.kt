package p20xx

import util.expect

fun main() {
    class Solution {
        fun numOfPairs(nums: Array<String>, target: String): Int {
            val startsWiths = hashMapOf<Int, Int>()
            val endsWiths = hashMapOf<Int, Int>()

            var result = 0
            nums.forEach {
                if (it.length < target.length) {
                    val start = target.startsWith(it)
                    val end = target.endsWith(it)

                    if (start) {
                        endsWiths[target.length - it.length]?.also {
                            result += it
                        }
                    }

                    if (end) {
                        startsWiths[target.length - it.length]?.also {
                            result += it
                        }
                    }

                    if (start) {
                        startsWiths[it.length] = (startsWiths[it.length] ?: 0) + 1
                    }

                    if (end) {
                        endsWiths[it.length] = (endsWiths[it.length] ?: 0) + 1
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().numOfPairs(
            arrayOf(), ""
        )
    }
}