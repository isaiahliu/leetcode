package p36xx

import util.expect

fun main() {
    class Solution {
        fun countTrapezoids(points: Array<IntArray>): Int {
            val pointNum = hashMapOf<Int, Int>()
            val mod = 1000000007
            var result: Long = 0
            var sum: Long = 0
            for (point in points) {
                pointNum[point[1]] = pointNum.getOrDefault(point[1], 0) + 1
            }
            for (pNum in pointNum.values) {
                val edge = pNum!!.toLong() * (pNum - 1) / 2
                result = (result + edge * sum) % mod
                sum = (sum + edge) % mod
            }
            return result.toInt()
        }
    }


    expect {
        Solution().countTrapezoids(
            arrayOf()
        )
    }
}
