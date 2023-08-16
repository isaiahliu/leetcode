package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperationsToFlip(expression: String): Int {
            abstract class AbstractExp {
                abstract val value: Int

                abstract val reverseCost: Int
            }

            class Or(val left: AbstractExp, val right: AbstractExp) : AbstractExp() {
                override val value: Int = left.value or right.value

                override val reverseCost: Int
                    get() {
                        return when (left.value xor right.value) {
                            1 -> 1
                            else -> {
                                left.reverseCost.coerceAtMost(right.reverseCost) + (left.value and right.value)
                            }
                        }
                    }
            }

            class And(val left: AbstractExp, val right: AbstractExp) : AbstractExp() {
                override val value: Int = left.value and right.value
                override val reverseCost: Int
                    get() {
                        return when (left.value xor right.value) {
                            1 -> 1

                            else -> {
                                left.reverseCost.coerceAtMost(right.reverseCost) + ((left.value and right.value) xor 1)
                            }
                        }
                    }
            }

            class One : AbstractExp() {
                override val value: Int = 1
                override val reverseCost: Int = 1
            }

            class Zero : AbstractExp() {
                override val value: Int = 0
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

                while (operators.peek()?.takeIf { it != '(' } != null) {
                    when (operators.poll()) {
                        '&' -> {
                            stack.push(And(stack.poll(), stack.poll()))
                        }

                        '|' -> {
                            stack.push(Or(stack.poll(), stack.poll()))
                        }
                    }
                }
            }
            return stack.peek().reverseCost
        }
    }

    measureTimeMillis {
        Solution().minOperationsToFlip(
            "0|(1)|(0|(1)|0|0&1|(1))"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
