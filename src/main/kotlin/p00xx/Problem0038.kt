package p00xx

import util.expect

fun main() {
    class Solution {
        fun countAndSay(n: Int): String {
            fun process(str: String): String {
                val result = StringBuilder()

                var last = str[0]
                var count = 0
                ("$str ").forEach {
                    if (it == last) {
                        count++
                    } else {
                        result.append(count.toString())
                        result.append(last)

                        last = it
                        count = 1
                    }
                }

                return result.toString()
            }

            var t = "1"

            repeat(n - 1) {
                t = process(t)
            }

            return t
        }
    }

    expect {
        Solution().countAndSay(1)
    }
}


