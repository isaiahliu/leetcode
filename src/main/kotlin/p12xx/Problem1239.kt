package p12xx

import util.expect

fun main() {
    class Solution {
        fun maxLength(arr: List<String>): Int {
            val set = hashSetOf(0)

            var result = 0
            arr.forEach { str ->
                val num = str.map {
                    1 shl (it - 'a')
                }.distinct().takeIf { it.size == str.length }?.sum() ?: return@forEach

                set.toSet().forEach {
                    if (num and it == 0) {
                        (num + it).also {
                            set.add(it)
                            result = result.coerceAtLeast(Integer.bitCount(it))
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxLength(
            listOf()
        )
    }
}

