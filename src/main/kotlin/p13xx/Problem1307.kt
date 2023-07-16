package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isSolvable(words: Array<String>, result: String): Boolean {
            val noneZeroChars = (words + result).mapNotNull { str ->
                str[0].takeIf { str.length > 1 }
            }.toSet()

            val lines = arrayListOf<CharArray>()

            var pos = 0
            loop@ while (true) {
                val line = arrayListOf<Char>()
                words.forEach {
                    it.getOrNull(it.lastIndex - pos)?.also {
                        line.add(it)
                    }
                }

                val r = result.getOrNull(result.lastIndex - pos)
                when {
                    r != null -> {
                        line.add(r)
                        lines.add(line.toCharArray())
                        pos++
                    }

                    line.isNotEmpty() -> {
                        return false
                    }

                    else -> {
                        break@loop
                    }
                }
            }

            val resultMap = hashMapOf<Char, Int>()
            val used = IntArray(26)

            val addSum = Array(lines.size) {
                //add -- sum
                intArrayOf(0, 0)
            }

            var init = 0

            var depth = 0
            var index = 0

            fun moveBack() {
                var moveMore = false
                if (index == 0) {
                    depth--

                    if (depth < 0) {
                        return
                    }

                    index = lines[depth].lastIndex
                    moveMore = true
                } else {
                    index--
                    resultMap[lines[depth][index]]?.also {
                        init = it + 1
                        addSum[depth][1] -= it
                    }
                }

                if (--used[lines[depth][index] - 'A'] == 0) {
                    resultMap.remove(lines[depth][index])
                } else {
                    moveMore = true
                }

                if (moveMore) {
                    moveBack()
                }
            }
            while (true) {
                if (depth < 0) {
                    return false
                }

                if (depth == lines.size) {
                    return true
                }
                val chars = lines[depth]

                val char = chars[index]

                if (index == chars.lastIndex) {
                    val totalSum = addSum[depth].sum()

                    if (depth == lines.lastIndex && totalSum > 9) {
                        moveBack()
                        continue
                    } else if (char in resultMap) {
                        if (resultMap[char] != totalSum % 10) {
                            moveBack()
                            continue
                        }

                    } else if (char in noneZeroChars && totalSum % 10 == 0) {
                        moveBack()
                        continue
                    } else if (totalSum % 10 !in resultMap.values) {
                        resultMap[char] = totalSum % 10
                    } else {
                        moveBack()
                        continue
                    }
                    depth++
                    index = 0
                    addSum.getOrNull(depth)?.also { it[0] = totalSum / 10 }
                } else {
                    if (char !in resultMap) {
                        if (char in noneZeroChars) {
                            init = init.coerceAtLeast(1)
                        }

                        while (init in resultMap.values) {
                            init++
                        }

                        if (init > 9) {
                            moveBack()
                            continue
                        }

                        resultMap[char] = init
                    }
                    addSum[depth][1] += (resultMap[char] ?: 0)
                    index++
                    init = 0
                }

                used[char - 'A']++
            }
        }
    }

    measureTimeMillis {
        Solution().isSolvable(
            arrayOf("A", "B"), "A"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

