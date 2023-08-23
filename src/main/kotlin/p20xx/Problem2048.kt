package p20xx

import util.expect

fun main() {
    class Solution {
        fun nextBeautifulNumber(n: Int): Int {
            val p = arrayOf(
                arrayOf(),
                arrayOf(intArrayOf(1)),
                arrayOf(intArrayOf(2)),
                arrayOf(intArrayOf(1, 2), intArrayOf(3)),
                arrayOf(intArrayOf(1, 3), intArrayOf(4)),
                arrayOf(intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(5)),
                arrayOf(intArrayOf(1, 5), intArrayOf(1, 2, 3), intArrayOf(2, 4), intArrayOf(6)),
                arrayOf(intArrayOf(1, 2, 4), intArrayOf(1, 6), intArrayOf(2, 5), intArrayOf(3, 4), intArrayOf(7))
            )

            var str = n.toString()

            fun CharArray.reverse(fromIndex: Int, toIndex: Int) {
                var l = fromIndex
                var r = toIndex
                while (l < r) {
                    val t = this[l]
                    this[l] = this[r]
                    this[r] = t
                    l++
                    r--
                }
            }

            while (true) {
                var result = "9".repeat(str.length)
                p[str.length].forEach { num ->
                    val min = num.joinToString("") {
                        it.toString().repeat(it)
                    }.toCharArray()

                    while (String(min) <= str) {
                        var i = min.lastIndex
                        while ((min.getOrNull(i - 1) ?: return@forEach) >= min[i]) {
                            i--
                        }

                        val char1 = min[i - 1]
                        var j = min.lastIndex
                        while (min[j] <= char1) {
                            j--
                        }

                        min[i - 1] = min[j]
                        min[j] = char1

                        min.reverse(i, min.lastIndex)
                    }

                    result = result.coerceAtMost(String(min))
                }

                if (result[0] != '9') {
                    return result.toInt()
                }
                str = "0$str"
            }
        }
    }

    expect {
        Solution().nextBeautifulNumber(
            1
        )
    }
}