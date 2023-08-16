package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperationsToFlip(expression: String): Int {
            abstract class AbstractExp {
                abstract val value: Boolean

                abstract val reverseCost: Int
            }

            class Or(val left: AbstractExp, val right: AbstractExp) : AbstractExp() {
                override val value: Boolean = left.value || right.value

                override val reverseCost: Int
                    get() {
                        return when {
                            left.value && right.value -> {
                                left.reverseCost.coerceAtMost(right.reverseCost) + 1
                            }

                            !left.value && !right.value -> {
                                left.reverseCost.coerceAtMost(right.reverseCost)
                            }

                            else -> 1
                        }
                    }
            }

            class And(val left: AbstractExp, val right: AbstractExp) : AbstractExp() {
                override val value: Boolean = left.value && right.value
                override val reverseCost: Int
                    get() {
                        return when {
                            left.value && right.value -> {
                                left.reverseCost.coerceAtMost(right.reverseCost)
                            }

                            !left.value && !right.value -> {
                                left.reverseCost.coerceAtMost(right.reverseCost) + 1
                            }

                            else -> 1
                        }
                    }
            }

            class One : AbstractExp() {
                override val value: Boolean = true
                override val reverseCost: Int = 1
            }

            class Zero : AbstractExp() {
                override val value: Boolean = false
                override val reverseCost: Int = 1
            }

            val stack = LinkedList<AbstractExp>()

            val operators = LinkedList<Char>()

            expression.forEach {
                when (it) {
                    '0' -> {
                        stack.push(Zero())
                    }

                    '1' -> {
                        stack.push(One())
                    }

                    ')' -> {
                        operators.poll()
                    }

                    else -> {
                        operators.push(it)
                        return@forEach
                    }
                }

                loop@ while (true) {
                    when (operators.peek()) {
                        '&' -> {
                            operators.poll()
                            stack.push(And(stack.poll(), stack.poll()))
                        }

                        '|' -> {
                            operators.poll()
                            stack.push(Or(stack.poll(), stack.poll()))
                        }

                        else -> break@loop
                    }
                }
            }
            return stack.peekLast().reverseCost
        }
    }

    measureTimeMillis {
        Solution().minOperationsToFlip(
            "(0&0)&(0&0&0)"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
