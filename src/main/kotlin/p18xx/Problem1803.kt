package p18xx

import util.expect

fun main() {
    class Solution {
        fun countPairs(nums: IntArray, low: Int, high: Int): Int {
            fun Int.binStr(): String {
                return toString(2).padStart(16, '0')
            }

            val lowStr = low.binStr()
            val highStr = high.binStr()

            val BOTH = 0
            val LOW = 1
            val HIGH = 2
            val FREE = 3

            class DicNode {
                var count = 0

                val nodes = arrayOfNulls<DicNode>(2)

                fun add(binStr: String, index: Int, dicNodeInit: () -> DicNode) {
                    count++

                    if (index < binStr.length) {
                        if (nodes[binStr[index] - '0'] == null) {
                            nodes[binStr[index] - '0'] = dicNodeInit()
                        }
                        nodes[binStr[index] - '0']?.add(binStr, index + 1, dicNodeInit)
                    }
                }

                fun query(binStr: String, index: Int, status: Int): Int {
                    if (index == binStr.length || status == FREE) {
                        return count
                    }

                    val digit = binStr[index] - '0'

                    val lowDigit = lowStr[index] - '0'
                    val highDigit = highStr[index] - '0'

                    var result = 0
                    when (status) {
                        BOTH -> {
                            if (lowDigit == highDigit) {
                                nodes[lowDigit xor digit]?.query(binStr, index + 1, BOTH)?.also {
                                    result += it
                                }
                            } else {
                                nodes[lowDigit xor digit]?.query(binStr, index + 1, LOW)?.also {
                                    result += it
                                }

                                nodes[highDigit xor digit]?.query(binStr, index + 1, HIGH)?.also {
                                    result += it
                                }
                            }
                        }

                        LOW -> {
                            nodes[lowDigit xor digit]?.query(binStr, index + 1, LOW)?.also {
                                result += it
                            }

                            if (lowDigit == 0) {
                                nodes[1 xor digit]?.query(binStr, index + 1, FREE)?.also {
                                    result += it
                                }
                            }
                        }

                        HIGH -> {
                            nodes[highDigit xor digit]?.query(binStr, index + 1, HIGH)?.also {
                                result += it
                            }

                            if (highDigit > 0) {
                                nodes[digit]?.query(binStr, index + 1, FREE)?.also {
                                    result += it
                                }
                            }
                        }
                    }

                    return result
                }
            }

            val root = DicNode()

            var result = 0
            nums.map { it.binStr() }.forEach {
                result += root.query(it, 0, BOTH)
                root.add(it, 0, ::DicNode)
            }

            return result
        }
    }

    expect {
        Solution().countPairs(
            intArrayOf(
                348,
                6140,
            ), 5793, 7046
        )

//        Solution().countPairs(
//            intArrayOf(1, 4, 2, 7), 2, 6
//        )
//
//        Solution().countPairs(
//            intArrayOf(9, 8, 4, 2, 1), 5, 14
//        )
    }
}
