package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun groupAnagrams(strs: Array<String>): List<List<String>> {
            return strs.groupBy { String(it.toCharArray().apply { sort() }) }.values.toList()
        }
    }

    measureTimeMillis {
        println(Solution().groupAnagrams(arrayOf("")))
    }.also { println("Time cost: ${it}ms") }
}


