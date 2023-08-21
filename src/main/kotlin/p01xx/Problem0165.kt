package p01xx

import util.expect

fun main() {
    class Solution {
        fun compareVersion(version1: String, version2: String): Int {
            val v1 = version1.split(".").map { it.toInt() }
            val v2 = version2.split(".").map { it.toInt() }

            for (i in 0 until v1.size.coerceAtLeast(v2.size)) {
                val t1 = v1.getOrElse(i) { 0 }
                val t2 = v2.getOrElse(i) { 0 }

                when {
                    t1 < t2 -> {
                        return -1
                    }

                    t1 > t2 -> {
                        return 1
                    }
                }
            }

            return 0
        }
    }

    expect {
        Solution().compareVersion(
            "", ""
        )
    }
}

