package p02xx

import util.expect

fun main() {
    abstract class VersionControl {
        fun isBadVersion(version: Int): Boolean {
            return true
        }

        abstract fun firstBadVersion(n: Int): Int
    }

    class Solution : VersionControl() {
        override fun firstBadVersion(n: Int): Int {
            fun binarySearch(left: Int, right: Int): Int {
                if (left > right) {
                    return n
                }

                val mid = left + (right - left) / 2
                return if (isBadVersion(mid)) {
                    binarySearch(left, mid - 1).coerceAtMost(mid)
                } else {
                    binarySearch(mid + 1, right)
                }
            }

            return binarySearch(1, n)
        }
    }

    expect {
        Solution().firstBadVersion(
            1
        )
    }
}

