package p21xx

import util.expect

fun main() {
    class Solution {
        fun smallestNumber(num: Long): Long {
            val str = num.toString()

            return when {
                num == 0L -> {
                    0L
                }

                str[0] == '-' -> {
                    "-${str.drop(1).toCharArray().also { it.sortDescending() }.concatToString()}".toLong()
                }

                else -> {
                    str.toCharArray().also { it.sort() }.dropWhile { it == '0' }.let {
                        "${it[0]}${"0".repeat(str.length - it.size)}${it.drop(1).joinToString("")}"
                    }.toLong()
                }
            }
        }
    }

    expect {
        Solution().smallestNumber(
            0L
        )
    }
}