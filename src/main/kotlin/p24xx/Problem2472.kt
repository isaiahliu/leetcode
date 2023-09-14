package p24xx

import util.expect

fun main() {
    class Solution {
        fun maxPalindromes(s: String, k: Int): Int {
            val newStr = "#${s.toList().joinToString("#")}#"

            val lengths = IntArray(newStr.length)

            var center = 0
            var right = 0

            newStr.indices.forEach { index ->
                val op = center * 2 - index

                if (op >= 0 && index + lengths[op] < right) {
                    lengths[index] = lengths[op]
                } else {
                    var size = 0
                    while (index + size + 1 < newStr.length && newStr[index + size + 1] == newStr.getOrNull(index - size - 1)) {
                        size++
                    }

                    lengths[index] = size
                    center = index
                    right = index + size
                }
            }

            val limits = intArrayOf(k, k)
            limits[1 - (k % 2)]++

            var result = 0
            var lastIndex = -s.length
            lengths.forEachIndexed { index, length ->
                if (length >= limits[index % 2]) {
                    val left = index - limits[index % 2] + 1
                    if (left > lastIndex) {
                        result++
                        lastIndex = index + limits[index % 2] - 1
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxPalindromes(
            "fttfjofpnpfydwdwdnns", 2
        )
    }
}