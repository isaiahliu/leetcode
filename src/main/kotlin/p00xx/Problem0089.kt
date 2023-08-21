package p00xx

import util.expect

fun main() {
    class Solution {
        fun grayCode(n: Int): List<Int> {
            val result = arrayListOf(0)

            fun process(bitPos: Int, baseNum: Int): Int {
                val t = 1 shl bitPos

                var newNum = baseNum xor t

                result.add(newNum)

                repeat(bitPos) {
                    newNum = process(it, newNum)
                }
                return newNum
            }

            var t = 0
            repeat(n) {
                t = process(it, t)
            }
            return result
        }
    }

    expect {
        Solution().grayCode(
            4
        )
    }
}

