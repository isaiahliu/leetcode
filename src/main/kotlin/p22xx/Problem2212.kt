package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumBobPoints(numArrows: Int, aliceArrows: IntArray): IntArray {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
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

            val bobScoreArrows = IntArray(aliceArrows.size) { aliceArrows[it] + 1 }

            var max = 0
            var maxResult = 0
            repeat(1 shl bobScoreArrows.size) { status ->
                var usedArrows = 0
                var score = 0

                status.forEachBit {
                    score += it
                    usedArrows += bobScoreArrows[it]
                }

                if (score > max && usedArrows <= numArrows) {
                    max = score
                    maxResult = status
                }
            }

            var used = 0
            val result = IntArray(bobScoreArrows.size) {
                if (maxResult and (1 shl it) > 0) {
                    bobScoreArrows[it].also { used += it }
                } else {
                    0
                }
            }

            bobScoreArrows[0] += numArrows - used

            return result
        }
    }

    expect {
        Solution().maximumBobPoints(
            9, intArrayOf(1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0)
        )
    }
}