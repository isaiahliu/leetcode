package p19xx

import util.expect

fun main() {
    class Solution {
        fun minSwaps(s: String): Int {
            var depth = 0
            var result = 0
            var remainLeft = s.length / 2
            s.forEach {
                when {
                    it == ']' -> {
                        if (depth > 0) {
                            depth--
                        } else {
                            depth++
                            remainLeft--
                            result++
                        }
                    }

                    remainLeft > 0 -> {
                        remainLeft--
                        depth++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minSwaps(
            "][]["
        )
    }
}