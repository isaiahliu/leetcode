package plcr

import util.expect

fun main() {
    class Solution {
        fun combine(n: Int, k: Int): List<List<Int>> {
            val results = arrayListOf<List<Int>>()

            fun walk(startNum: Int, route: List<Int>) {
                if (route.size == k) {
                    results.add(route)
                    return
                }

                for (nextN in startNum..n - (k - route.size) + 1) {
                    walk(nextN + 1, route + nextN)
                }
            }

            walk(1, emptyList())

            return results
        }
    }

    expect {
        Solution().combine(4, 2)
    }
}

