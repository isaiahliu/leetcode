package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findDuplicate(paths: Array<String>): List<List<String>> {
            val regex = "(.*)\\((.*)\\)".toRegex()

            val map = hashMapOf<String, MutableList<String>>()

            paths.forEach {
                val nodes = it.split(" ")
                val dir = nodes[0]

                nodes.drop(1).mapNotNull { regex.matchEntire(it)?.groupValues?.drop(1) }
                    .forEach { (fileName, content) ->
                        map.computeIfAbsent(content) { arrayListOf() }.add("$dir/$fileName")
                    }
            }

            return map.values.filter { it.size > 1 }
        }
    }

    measureTimeMillis {
        Solution().findDuplicate(
            arrayOf("root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)")
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}