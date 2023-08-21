package p07xx

import util.expect

fun main() {
    class Solution {
        fun crackSafe(n: Int, k: Int): String {
            if (n == 1) {
                return (0 until k).joinToString("")
            }

            val total = (0 until n).fold(1) { a, b ->
                a * k
            }

            val init = "0".repeat(n)
            val route = hashSetOf(init)
            fun dfs(last: String): String? {
                if (route.size == total) {
                    return ""
                }

                val pre = last.drop(1)
                repeat(k) { num ->
                    val next = pre + num

                    if (next !in route) {
                        route.add(next)
                        dfs(next)?.also {
                            return "$num$it"
                        } ?: run {
                            route.remove(next)
                        }
                    }
                }

                return null
            }

            return init + dfs(init).orEmpty()
        }
    }

    expect {
        Solution().crackSafe(
            2, 2
        )
    }
}