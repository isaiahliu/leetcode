package p12xx

import util.expect

fun main() {
    class Solution {
        fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
            var sorted = products.sorted()

            val result = arrayListOf<List<String>>()
            searchWord.forEachIndexed { index, c ->
                sorted = sorted.filter { it.getOrNull(index) == c }
                result.add(sorted.take(3))
            }

            return result
        }
    }

    expect {
        Solution().suggestedProducts(
            arrayOf(), ""
        )
    }
}
