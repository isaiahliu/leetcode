package p21xx

import util.expect

fun main() {
    class Solution {
        fun asteroidsDestroyed(mass: Int, asteroids: IntArray): Boolean {
            asteroids.sort()

            var m = mass.toLong()
            asteroids.forEach {
                if (m >= it) {
                    m += it
                } else {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().asteroidsDestroyed(
            1, intArrayOf()
        )
    }
}