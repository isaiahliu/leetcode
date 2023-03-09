package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun originalDigits(s: String): String {
            val chars = IntArray(26)

            s.forEach {
                chars[it - 'a']++
            }

            val result = IntArray(10)

            fun process(num: Int, baseChar: Char, s: String) {
                val count = chars[baseChar - 'a']
                result[num] = count

                s.forEach {
                    chars[it - 'a'] -= count
                }
            }

            process(0, 'z', "zero")
            process(2, 'w', "two")
            process(4, 'u', "four")
            process(6, 'x', "six")
            process(8, 'g', "eight")
            process(1, 'o', "one")
            process(3, 't', "three")
            process(5, 'f', "five")
            process(7, 's', "seven")
            process(9, 'i', "nine")


            return result.mapIndexed { index, i ->
                ('0' + index).toString().repeat(i)
            }.joinToString("")
        }
    }

    measureTimeMillis {
        Solution().originalDigits(
            "fviefuro"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


