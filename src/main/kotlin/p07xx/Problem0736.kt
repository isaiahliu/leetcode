package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun evaluate(expression: String): Int {
            var index = 0

            fun evaluateInternal(parentVariables: Map<String, Int>): Int {
                val variables = parentVariables.toMutableMap()

                var lastParam: String? = null

                var command = ""

                val str = StringBuilder()

                fun String?.eval(): Int {
                    return this?.toIntOrNull() ?: variables[this] ?: 0
                }

                fun push(value: Int? = null) {
                    val v = value?.toString() ?: str.toString().also { str.clear() }
                    when {
                        v.isEmpty() -> {}
                        command.isEmpty() -> {
                            command = v
                        }

                        lastParam == null -> {
                            lastParam = v
                        }

                        command == "let" -> {
                            variables[lastParam.orEmpty()] = v.eval()
                            lastParam = null
                        }

                        command == "add" -> {
                            lastParam = (lastParam.eval() + v.eval()).toString()
                        }

                        command == "mult" -> {
                            lastParam = (lastParam.eval() * v.eval()).toString()
                        }
                    }
                }

                while (index < expression.length) {
                    when (val c = expression[index++]) {
                        '(' -> {
                            push(evaluateInternal(variables))
                        }

                        ')' -> {
                            push()

                            return lastParam.eval()
                        }

                        ' ' -> {
                            push()
                        }

                        else -> {
                            str.append(c)
                        }
                    }
                }

                push()

                return lastParam?.eval() ?: command.eval()
            }

            return evaluateInternal(emptyMap())
        }
    }

    measureTimeMillis {
        Solution().evaluate(
            "(let x 2 (mult x (let x 3 y 4 (add x y))))"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}