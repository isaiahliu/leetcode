package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val subExpOps = hashMapOf(
            '+' to (charArrayOf('-', '*') to charArrayOf('+', '-', '*')),
            '-' to (charArrayOf('-', '*') to charArrayOf('*')),
            '*' to (charArrayOf() to charArrayOf('*'))
        )

        val cache = hashMapOf<Pair<String, Char>, Map<Long, Set<String>>>()

        fun addOperators(num: String, target: Int): List<String> {
            fun process(subNum: String, allowedOps: CharArray): Map<Long, Set<String>> {
                val result = hashMapOf<Long, MutableSet<String>>()

                if (subNum.length == 1 || subNum[0] != '0') {
                    result[subNum.toLong()] = hashSetOf(subNum)
                }

                for (op in allowedOps) {
                    if (subNum to op !in cache) {
                        val subResult = hashMapOf<Long, MutableSet<String>>()

                        for (i in 1 until subNum.length) {
                            val lNum = subNum.substring(0, i)
                            val rNum = subNum.substring(i)

                            val (leftOps, rightOps) = subExpOps[op] ?: continue

                            val leftResults = process(lNum, leftOps)
                            val rightResults = process(rNum, rightOps)

                            leftResults.forEach { (l, lexps) ->
                                rightResults.forEach { (r, rexps) ->
                                    lexps.forEach { lexp ->
                                        rexps.forEach { rexp ->
                                            when (op) {
                                                '+' -> {
                                                    subResult.computeIfAbsent(l + r) { hashSetOf() }.add(
                                                        "${lexp}+${rexp}"
                                                    )
                                                }

                                                '-' -> {
                                                    subResult.computeIfAbsent(l - r) { hashSetOf() }.add(
                                                        "${lexp}-${rexp}"
                                                    )
                                                }

                                                '*' -> {
                                                    subResult.computeIfAbsent(l * r) { hashSetOf() }.add(
                                                        "${lexp}*${rexp}"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        cache[subNum to op] = subResult
                    }

                    cache[subNum to op]?.forEach { (key, value) ->
                        result.computeIfAbsent(key) { hashSetOf() }.addAll(value)
                    }
                }

                return result
            }

            return process(num, charArrayOf('+', '-', '*'))[target.toLong()]?.toList().orEmpty()
        }
    }

    measureTimeMillis {
        Solution().addOperators(
            "000", 0
        ).also { println(it) }
    }
}

