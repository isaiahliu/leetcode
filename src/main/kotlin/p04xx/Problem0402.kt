package p04xx

import util.expect

fun main() {
    class Solution {
        fun removeKdigits(num: String, k: Int): String {
            val list = num.toMutableList()

            var t = k

            var index = 0
            loop@ while (t > 0) {
                while (index < list.lastIndex) {
                    if (index >= 0 && list[index] > list[index + 1]) {
                        t--
                        list.removeAt(index)
                        index--
                        continue@loop
                    }
                    index++
                }
                break@loop
            }

            return list.dropLast(t).joinToString("").trimStart('0').ifEmpty { "0" }
        }
    }

    expect {
        Solution().removeKdigits(
            "10200", 1
        )
    }
}


