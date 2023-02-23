package p01xx

fun main() {
    class Solution {
        fun reverseBits(n: Int): Int {
            var result = 0u

            val max = 1u shl 31

            var t = n
            while (t != 0) {
                val b = Integer.highestOneBit(t).toUInt()

                result += max / b

                t -= b.toInt()
            }

            return result.toInt()
        }
    }

    println(
        Solution().reverseBits(
            -3
        )
    )
}

