package p12xx

fun main() {
    class Solution {
        fun circularPermutation(n: Int, start: Int): List<Int> {
            val firstHalf = arrayListOf<Int>()
            val secondHalf = arrayListOf<Int>()

            var current = if (start == 0) secondHalf else firstHalf
            fun process(bitPos: Int, baseNum: Int): Int {
                val t = 1 shl bitPos

                var newNum = baseNum xor t

                if (newNum == start) {
                    current = secondHalf
                }
                current.add(newNum)

                repeat(bitPos) {
                    newNum = process(it, newNum)
                }
                return newNum
            }

            current.add(0)
            var t = 0
            repeat(n) {
                t = process(it, t)
            }
            return secondHalf + firstHalf
        }
    }

    println(
        Solution().circularPermutation(
            1, 2
        )
    )
}

