package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isRationalEqual(s: String, t: String): Boolean {
            class Num(str: String) {
                var integer: Int = 0
                var nonRepeating: String = ""
                var repeating: String = ""

                init {
                    var step = 0

                    str.forEach {
                        when (it) {
                            '.' -> step = 1
                            '(', ')' -> step = 2
                            else -> {
                                when (step) {
                                    0 -> {
                                        integer *= 10
                                        integer += it - '0'
                                    }

                                    1 -> {
                                        nonRepeating += it
                                    }

                                    2 -> {
                                        repeating += it
                                    }
                                }
                            }
                        }
                    }

                    while (nonRepeating.isNotEmpty() && repeating.isNotEmpty() && nonRepeating[nonRepeating.lastIndex] == repeating[repeating.lastIndex]) {
                        nonRepeating = nonRepeating.dropLast(1)

                        repeating = repeating.takeLast(1) + repeating.dropLast(1)
                    }

                    for (repeatLength in 1..repeating.length / 2) {
                        if (repeating.length % repeatLength == 0 && repeating.take(repeatLength)
                                .repeat(repeating.length / repeatLength) == repeating
                        ) {
                            repeating = repeating.take(repeatLength)

                            break
                        }
                    }

                    if (repeating == "9") {
                        if (nonRepeating.isEmpty()) {
                            integer++
                        } else {
                            nonRepeating = (nonRepeating.toInt() + 1).toString().padStart(nonRepeating.length, '0')
                        }

                        repeating = ""
                    }

                    if (repeating == "0") {
                        repeating = ""
                    }

                    if (repeating.isEmpty() && nonRepeating.toIntOrNull() == 0) {
                        nonRepeating = ""
                    }
                }

                override fun toString(): String {
                    return "${integer}.${nonRepeating}(${repeating})"
                }

                fun sameAs(target: Num): Boolean {
                    return integer == target.integer && nonRepeating == target.nonRepeating && repeating == target.repeating
                }
            }

            return Num(s).sameAs(Num(t))
        }
    }

    measureTimeMillis {
        Solution().isRationalEqual(
            "1.0", "1"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
