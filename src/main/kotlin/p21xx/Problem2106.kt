package p21xx

import util.expect

fun main() {
    class Solution {
        fun maxTotalFruits(fruits: Array<IntArray>, startPos: Int, k: Int): Int {
            var fixed = 0
            var leftSum = 0
            var rightSum = 0
            var leftStartIndex = fruits.size
            var leftEndIndex = -1
            var rightStartIndex = fruits.size
            var rightEndIndex = -1
            fruits.forEachIndexed { index, (pos, count) ->
                when (pos) {
                    startPos -> {
                        fixed += count
                    }

                    in startPos + 1..startPos + k -> {
                        rightStartIndex = rightStartIndex.coerceAtMost(index)
                        rightEndIndex = index
                        rightSum += count
                    }

                    in startPos - k until startPos -> {
                        leftStartIndex = leftStartIndex.coerceAtMost(index)
                        leftEndIndex = index
                        leftSum += count
                    }
                }
            }

            return fixed + when {
                leftEndIndex < 0 -> {
                    rightSum
                }

                rightStartIndex > fruits.lastIndex -> {
                    leftSum
                }

                else -> {
                    var result = leftSum.coerceAtLeast(rightSum)

                    var leftEnd = leftEndIndex
                    var rightEnd = rightEndIndex
                    var remain = rightSum

                    while (leftEnd >= leftStartIndex && (startPos - fruits[leftEnd][0]) * 2 + fruits[rightEnd][0] - startPos <= k) {
                        remain += fruits[leftEnd--][1]
                    }
                    result = result.coerceAtLeast(remain)


                    while (rightEnd >= rightStartIndex && leftEnd >= leftStartIndex) {
                        remain -= fruits[rightEnd--][1]

                        while (leftEnd >= leftStartIndex && (startPos - fruits[leftEnd][0]) * 2 + fruits[rightEnd][0] - startPos <= k) {
                            remain += fruits[leftEnd--][1]
                        }

                        result = result.coerceAtLeast(remain)
                    }

                    var leftStart = leftStartIndex
                    var rightStart = rightStartIndex
                    remain = leftSum

                    while (rightStart <= rightEndIndex && (fruits[rightStart][0] - startPos) * 2 + startPos - fruits[leftStart][0] <= k) {
                        remain += fruits[rightStart++][1]
                    }
                    result = result.coerceAtLeast(remain)

                    while (leftStart <= leftEndIndex && rightStart <= rightEndIndex) {
                        remain -= fruits[leftStart++][1]

                        while (rightStart <= rightEndIndex && (fruits[rightStart][0] - startPos) * 2 + startPos - fruits[leftStart][0] <= k) {
                            remain += fruits[rightStart++][1]
                        }

                        result = result.coerceAtLeast(remain)
                    }

                    result
                }
            }
        }
    }

    expect {
        Solution().maxTotalFruits(
            arrayOf(
                intArrayOf(200000, 10000),
            ), 200000, 200000
        )
    }
}