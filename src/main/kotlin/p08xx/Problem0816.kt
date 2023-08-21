package p08xx

import util.expect

fun main() {
    class Solution {
        fun ambiguousCoordinates(s: String): List<String> {
            val cache = hashMapOf<String, List<String>>()

            fun String.validInteger(): Boolean {
                return this.length == 1 || this[0] != '0'
            }

            fun String.validDecimal(): Boolean {
                return this[this.lastIndex] != '0'
            }

            fun process(str: String): List<String> {
                if (str in cache) {
                    return cache[str].orEmpty()
                }

                val result = arrayListOf<String>()

                if (str.validInteger()) {
                    result.add(str)
                }

                for (i in 1 until str.length) {
                    val int = str.substring(0 until i)
                    val dec = str.substring(i until str.length)

                    if (int.validInteger() && dec.validDecimal()) {
                        result.add("${int}.${dec}")
                    }
                }

                return result
            }

            val result = arrayListOf<String>()

            for (i in 1 until s.lastIndex - 1) {
                val left = process(s.substring(1..i))
                val right = process(s.substring(i + 1 until s.lastIndex))

                left.forEach { l ->
                    right.forEach { r ->
                        result += "(${l}, ${r})"
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().ambiguousCoordinates(
            "(123)"
        )
    }
}