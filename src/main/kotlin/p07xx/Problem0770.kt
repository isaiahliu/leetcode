package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun basicCalculatorIV(expression: String, evalvars: Array<String>, evalints: IntArray): List<String> {
            val priorities = hashMapOf('+' to 1, '-' to 1, '*' to 2, '(' to 0)

            val variables = hashMapOf<String, Int>()

            evalvars.forEachIndexed { index, s ->
                variables[s] = evalints[index]
            }

            val str = StringBuilder()
            val queue = LinkedList<String>()
            val operators = LinkedList<Char>()

            fun pushNum() {
                str.takeIf { it.isNotEmpty() }?.toString()?.let {
                    queue.add(variables[it]?.toString() ?: it)
                    str.clear()
                }
            }

            "$expression ".forEach {
                when (it) {
                    ' ' -> {
                        pushNum()
                    }

                    '(' -> {
                        pushNum()

                        operators.push(it)
                    }

                    ')' -> {
                        pushNum()

                        while (operators.peek() != '(') {
                            queue.add(operators.poll().toString())
                        }

                        operators.poll()
                    }

                    '+', '-', '*' -> {
                        pushNum()

                        val priority = priorities[it] ?: 0
                        while (operators.peek()?.takeIf { (priorities[it] ?: 0) >= priority } != null) {
                            queue.add(operators.poll().toString())
                        }

                        operators.push(it)
                    }

                    else -> {
                        str.append(it)
                    }
                }
            }

            queue.addAll(operators.map { it.toString() })

            class Multinomial(exp: String) {
                val exps = hashMapOf<String, Int>()

                init {
                    exp.toIntOrNull()?.also {
                        if (it != 0) {
                            exps[""] = it
                        }
                    } ?: run {
                        exps["*$exp"] = 1
                    }
                }

                operator fun plusAssign(target: Multinomial) {
                    target.exps.forEach { (key, value) ->
                        val current = exps[key] ?: 0

                        val count = current + value

                        if (count == 0) {
                            exps.remove(key)
                        } else {
                            exps[key] = count
                        }
                    }
                }

                operator fun minusAssign(target: Multinomial) {
                    target.exps.forEach { (key, value) ->
                        val current = exps[key] ?: 0

                        val count = current - value

                        if (count == 0) {
                            exps.remove(key)
                        } else {
                            exps[key] = count
                        }
                    }
                }

                operator fun timesAssign(target: Multinomial) {
                    val left = exps.toMap()
                    exps.clear()

                    target.exps.forEach { (right, rightCount) ->
                        left.forEach { (left, leftCount) ->
                            val key = (left.split("*").filter { it.isNotEmpty() } + right.split("*")
                                .filter { it.isNotEmpty() }).sorted().joinToString("*").takeIf { it.isNotEmpty() }
                                ?.let { "*$it" }.orEmpty()

                            val count = (exps[key] ?: 0) + leftCount * rightCount

                            if (count == 0) {
                                exps.remove(key)
                            } else {
                                exps[key] = count
                            }
                        }
                    }
                }

                fun toList(): List<String> {
                    return exps.entries.sortedWith(compareByDescending<MutableMap.MutableEntry<String, Int>> { it.key.count { it == '*' } }.thenBy { it.key })
                        .map { (key, value) ->
                            "${value}${key}"
                        }
                }
            }

            val calculator = LinkedList<Multinomial>()

            queue.forEach {
                when (it) {
                    "+" -> {
                        val right = calculator.poll()
                        calculator.peek() += right
                    }

                    "-" -> {
                        val right = calculator.poll()
                        calculator.peek() -= right
                    }

                    "*" -> {
                        val right = calculator.poll()
                        calculator.peek() *= right
                    }

                    else -> {
                        calculator.push(Multinomial(it))
                    }
                }
            }

            return calculator.pop().toList()
        }
    }

    measureTimeMillis {
        Solution().basicCalculatorIV(
            "(e + 8)*(e-8)", arrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}