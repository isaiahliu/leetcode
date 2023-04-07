package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countOfAtoms(formula: String): String {
            var index = 0

            val stack = LinkedList<SortedMap<String, Int>>()

            var res: SortedMap<String, Int> = TreeMap()

            val atomName = StringBuilder()
            var count = 0
            var waitingForPop = false

            fun popResult() {
                when {
                    atomName.isNotEmpty() -> {
                        res[atomName.toString()] = (res[atomName.toString()] ?: 0) + count.coerceAtLeast(1)
                        atomName.clear()
                    }

                    waitingForPop -> {
                        val pre = stack.pop()
                        res.forEach { (key, value) ->
                            pre[key] = (pre[key] ?: 0) + value * count.coerceAtLeast(1)
                        }

                        res = pre
                        waitingForPop = false
                    }
                }

                count = 0
            }

            while (index < formula.length) {
                when (val c = formula[index++]) {
                    in 'A'..'Z' -> {
                        popResult()

                        atomName.append(c)
                    }

                    in 'a'..'z' -> {
                        atomName.append(c)
                    }

                    in '0'..'9' -> {
                        count = count * 10 + (c - '0')
                    }

                    '(' -> {
                        popResult()

                        stack.push(res)
                        res = TreeMap()
                    }

                    ')' -> {
                        popResult()
                        waitingForPop = true
                    }
                }
            }

            popResult()

            return res.map { (key, value) ->
                "${key}${if (value > 1) value.toString() else ""}"
            }.joinToString("")
        }
    }

    measureTimeMillis {
        Solution().countOfAtoms(
            "K4(ON(SO3)2)2"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}