package p07xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        WordFilter(arrayOf("apple")).f(
            "a", "e"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}