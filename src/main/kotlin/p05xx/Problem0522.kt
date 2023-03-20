package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findLUSlength(strs: Array<String>): Int {
            fun subContains(str1: String, str2: String): Boolean {
                var index1 = 0
                var index2 = 0

                while (index1 < str1.length && index2 < str2.length) {
                    if (str1[index1++] == str2[index2]) {
                        index2++
                    }
                }

                return index2 == str2.length
            }

            strs.sortByDescending { it.length }

            var index = 0
            while (index < strs.size) {
                if (strs[index].isEmpty()) {
                    index++
                    continue
                }

                val s = strs[index]

                var failed = false

                for (i in index + 1 until strs.size) {
                    if (strs[i].isEmpty()) {
                        continue
                    }

                    if (s == strs[i]) {
                        failed = true
                        strs[i] = ""
                    } else if (subContains(s, strs[i])) {
                        strs[i] = ""
                    }
                }

                if (!failed) {
                    return s.length
                }

                index++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().findLUSlength(arrayOf("aaa", "aaa", "ab")).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}