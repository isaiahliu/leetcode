package p19xx

import util.expect

fun main() {
    class Solution {
        fun waysToBuildRooms(prevRoom: IntArray): Int {
            val m = 1000000007
            val mi = m.toBigInteger()

            fun Int.arrangement(start: Int = 1): Int {
                var result = 1L

                for (num in start..this) {
                    result *= num
                    result %= m
                }

                return result.toInt()
            }

            fun combine(cm: Int, cn: Int): Int {
                val cmn = cm - cn

                return ((cm.arrangement(cn.coerceAtLeast(cmn) + 1) * cn.coerceAtMost(cmn).arrangement().toBigInteger()
                    .modInverse(mi).toLong()) % m).toInt()
            }

            val childrenMap = hashMapOf<Int, MutableSet<Int>>()

            prevRoom.forEachIndexed { room, parent ->
                childrenMap.computeIfAbsent(parent) { hashSetOf() }.add(room)
            }

            //possible -- size
            fun dfs(room: Int): Pair<Int, Int> {
                var size = 0
                var possible = 1L
                childrenMap[room]?.map {
                    dfs(it).also {
                        possible *= it.first
                        possible %= m
                        size += it.second
                    }
                }?.also {
                    var tempSize = size
                    it.forEach { (_, s) ->
                        possible *= combine(tempSize, s)
                        possible %= m

                        tempSize -= s
                    }
                }

                return possible.toInt() to size + 1
            }

            return dfs(-1).first
        }
    }

    expect {
        Solution().waysToBuildRooms(
            intArrayOf(-1, 0, 0, 1, 2)
        )
    }
}