package p10xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeDuplicates(s: String): String {
            val list = LinkedList<Char>()

            s.forEach {
                if (list.peekLast() == it) {
                    list.pollLast()
                } else {
                    list.add(it)
                }
            }

            return String(list.toCharArray())
        }
    }

    measureTimeMillis {
        Solution().removeDuplicates(
            "abbaca"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}