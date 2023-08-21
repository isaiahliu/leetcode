package p05xx

import util.expect

fun main() {
    class Solution {
        fun findWords(words: Array<String>): Array<String> {
            val map = hashMapOf<Char, Int>()
            "qwertyuiop".forEach { map[it] = 1 }
            "asdfghjkl".forEach { map[it] = 2 }
            "zxcvbnm".forEach { map[it] = 3 }

            fun Char.line(): Int {
                return if (this in 'A'..'Z') {
                    map['a' + (this - 'A')]
                } else {
                    map[this]
                } ?: 0
            }

            return words.filter {
                val l = it[0].line()

                for (i in 1 until it.length) {
                    if (it[i].line() != l) {
                        return@filter false
                    }
                }

                true
            }.toTypedArray()
        }
    }

    expect {
        Solution().findWords(
            arrayOf(

            )
        ).toList()
    }
}