package p02xx

import util.expect

fun main() {
    class Solution {
        val digit = arrayOf(null, "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
        val tens = arrayOf(null, "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

        val thousands = arrayOf(
            null, "Thousand", "Million", "Billion", "Trillion"
        )

        val special = hashMapOf(
            11 to "Eleven",
            12 to "Twelve",
            13 to "Thirteen",
            14 to "Fourteen",
            15 to "Fifteen",
            16 to "Sixteen",
            17 to "Seventeen",
            18 to "Eighteen",
            19 to "Nineteen",
        )

        fun numberToWords(num: Int): String {
            if (num == 0) {
                return "Zero"
            }

            val results = arrayListOf<String>()

            var t = num
            while (t > 0) {
                var current = t % 1000
                t /= 1000

                val strs = arrayListOf<String>()

                val hundred = current / 100
                current %= 100

                digit[hundred]?.also {
                    strs.add(it)
                    strs.add("Hundred")
                }

                special[current]?.also {
                    strs.add(it)
                } ?: run {
                    tens[current / 10]?.also {
                        strs.add(it)
                    }

                    digit[current % 10]?.also {
                        strs.add(it)
                    }
                }

                results.add(strs.joinToString(" "))
            }

            return results.mapIndexedNotNull { index, s ->
                s.takeIf { it.isNotBlank() }?.let {
                    s + (thousands[index]?.let { " ${it}" } ?: "")
                }
            }.reversed().joinToString(" ")
        }
    }

    expect {
        Solution().numberToWords(1234)
    }
}

