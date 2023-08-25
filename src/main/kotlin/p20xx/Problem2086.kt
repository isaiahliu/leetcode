package p20xx

import util.expect

fun main() {
    class Solution {
        fun minimumBuckets(hamsters: String): Int {
            var result = 0

            var index = 0
            var lastHamster = -2
            while (index < hamsters.length) {
                if (hamsters[index] == 'H' && index > lastHamster + 1) {
                    when {
                        hamsters.getOrNull(index + 1) == '.' -> {
                            result++
                            lastHamster = index + 1
                            index += 2
                        }

                        hamsters.getOrNull(index - 1) == '.' -> {
                            result++
                            index++
                        }

                        else -> {
                            return -1
                        }
                    }
                } else {
                    index++
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumBuckets(
            "H..H"
        )
    }
}