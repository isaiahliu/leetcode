package p04xx

import util.expect

fun main() {
    class Solution {
        fun readBinaryWatch(turnedOn: Int): List<String> {
            return (0 until 12).map { h ->
                (0 until 12).filter { m ->
                    Integer.bitCount(h) + Integer.bitCount(m) == turnedOn
                }.map { m -> "$h:${m.toString().padStart(2, '0')}" }
            }.flatten()
        }
    }

    expect {
        Solution().readBinaryWatch(
            1
        )
    }
}


