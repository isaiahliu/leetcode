package p01xx

fun main() {
    class Solution {
        fun findRepeatedDnaSequences(s: String): List<String> {
            val map = hashMapOf<String, Int>()
            for (i in 0 until s.length - 9) {
                val subStr = s.substring(i, i + 10)

                map[subStr] = (map[subStr] ?: 0) + 1
            }

            return map.filterValues { it > 1 }.keys.toList()
        }
    }

    println(
        Solution().findRepeatedDnaSequences(
            "12345678901"
        )
    )
}

