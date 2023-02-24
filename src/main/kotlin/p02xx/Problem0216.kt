package p02xx

fun main() {
    class Solution {
        fun combinationSum3(k: Int, n: Int): List<List<Int>> {
            fun combination(minNum: Int, k: Int, n: Int): List<List<Int>> {
                if (minNum > n) {
                    return emptyList()
                }

                if (k == 1) {
                    return if (n <= 9) {
                        listOf(listOf(n))
                    } else {
                        emptyList()
                    }
                }

                val result = arrayListOf<List<Int>>()
                for (num in minNum..9.coerceAtMost(n / k)) {
                    result.addAll(combination(num + 1, k - 1, n - num).map { it + num })
                }

                return result
            }

            return combination(1, k, n)
        }
    }

    println(
        Solution().combinationSum3(
            3, 9
        )
    )
}

