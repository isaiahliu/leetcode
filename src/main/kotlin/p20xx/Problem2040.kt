package p20xx

import util.expect

fun main() {
    class Solution {
        fun kthSmallestProduct(nums1: IntArray, nums2: IntArray, k: Long): Long {
            fun IntArray.preProcess(): Pair<Pair<LongArray, LongArray>, Long> {
                val pos = arrayListOf<Long>()
                val neg = arrayListOf<Long>()
                var zero = 0L

                forEach {
                    when {
                        it > 0 -> pos.add(it.toLong())
                        it < 0 -> neg.add(it.toLong())
                        else -> zero++
                    }
                }

                pos.sort()
                neg.sort()

                return pos.toLongArray() to neg.toLongArray() to zero
            }

            val (p1, zero1) = nums1.preProcess()
            val (pos1, neg1) = p1
            val (p2, zero2) = nums2.preProcess()
            val (pos2, neg2) = p2

            fun search(matrix: List<Pair<LongArray, LongArray>>, searchK: Long): Long {
                var leftNum = matrix.map { (r, c) -> r[0] * c[0] }.min()
                var rightNum = matrix.map { (r, c) -> r[r.lastIndex] * c[c.lastIndex] }.max()
                var result = 0L
                while (leftNum <= rightNum) {
                    val midNum = leftNum + (rightNum - leftNum) / 2

                    var count = 0L
                    matrix.forEach { (row, column) ->
                        val rightMost = column[column.lastIndex]

                        var matchRowIndex = -1
                        var top = 0
                        var bottom = row.lastIndex

                        while (top <= bottom) {
                            val midR = (top + bottom) / 2

                            if (row[midR] * rightMost <= midNum) {
                                matchRowIndex = midR
                                top = midR + 1
                            } else {
                                bottom = midR - 1
                            }
                        }

                        var matchCount = (matchRowIndex + 1L) * column.size

                        for (rowIndex in matchRowIndex + 1 until row.size) {
                            val rowNum = row[rowIndex]
                            var leftC = 0
                            var rightC = column.lastIndex
                            var matchC = -1
                            while (leftC <= rightC) {
                                val midC = (leftC + rightC) / 2

                                if (rowNum * column[midC] <= midNum) {
                                    matchC = midC
                                    leftC = midC + 1
                                } else {
                                    rightC = midC - 1
                                }
                            }

                            if (matchC < 0) {
                                break
                            }

                            matchCount += matchC + 1
                        }

                        count += matchCount
                    }

                    if (count < searchK) {
                        leftNum = midNum + 1
                    } else {
                        result = midNum
                        rightNum = midNum - 1
                    }
                }

                return result
            }

            val negCount = pos1.size.toLong() * neg2.size + pos2.size.toLong() * neg1.size
            val zeroCount = zero1 * nums2.size + zero2 * nums1.size - zero1 * zero2

            return when {
                negCount >= k -> {
                    val matrix = arrayListOf<Pair<LongArray, LongArray>>()

                    if (pos1.isNotEmpty() && neg2.isNotEmpty()) {
                        pos1.reverse()
                        matrix.add(pos1 to neg2)
                    }

                    if (pos2.isNotEmpty() && neg1.isNotEmpty()) {
                        pos2.reverse()
                        matrix.add(pos2 to neg1)
                    }

                    search(matrix, k)
                }

                negCount + zeroCount >= k -> {
                    0
                }

                else -> {
                    val matrix = arrayListOf<Pair<LongArray, LongArray>>()

                    if (pos1.isNotEmpty() && pos2.isNotEmpty()) {
                        matrix.add(pos1 to pos2)
                    }

                    if (neg1.isNotEmpty() && neg2.isNotEmpty()) {
                        neg1.reverse()
                        neg2.reverse()
                        matrix.add(neg1 to neg2)
                    }
                    search(matrix, k - negCount - zeroCount)
                }
            }
        }
    }

    expect {
        Solution().kthSmallestProduct(
            IntArray(50000), IntArray(50000), 1000000000
        )
    }
}