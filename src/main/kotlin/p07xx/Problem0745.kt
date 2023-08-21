package p07xx

import util.expect

fun main() {
    class WordFilter(words: Array<String>) {
        val map = hashMapOf<String, Int>()

        init {
            words.forEachIndexed { index, str ->
                repeat(7) { p ->
                    repeat(7) { s ->
                        if (p <= str.length && s <= str.length) {
                            map["${str.take(p + 1)}_${str.takeLast(s + 1)}"] = index
                        }
                    }
                }
            }
        }

        fun f(pref: String, suff: String): Int {
            return map["${pref}_$suff"] ?: -1
        }
    }

    expect {
        WordFilter(arrayOf("apple")).f(
            "a", "e"
        )
    }
}