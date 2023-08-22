package p20xx

import util.expect

fun main() {
    class Solution {
        fun maxConsecutiveAnswers(answerKey: String, k: Int): Int {
            var result = 0

            charArrayOf('T', 'F').forEach { target ->
                var left = 0
                var right = 0

                var changed = 0
                while (right < answerKey.length) {
                    when {
                        answerKey[right] != target && changed == k -> {
                            if (answerKey[left] != target) {
                                changed--
                            }

                            left++
                        }

                        else -> {
                            result = result.coerceAtLeast(right - left + 1)

                            if (answerKey[right] != target) {
                                changed++
                            }

                            right++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxConsecutiveAnswers(
            "", 1
        )
    }
}