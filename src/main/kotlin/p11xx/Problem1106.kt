package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun parseBoolExpr(expression: String): Boolean {
            abstract class Exp {
                val subs = arrayListOf<Exp>()

                abstract fun evaluate(): Boolean
            }

            class AndExp : Exp() {
                override fun evaluate(): Boolean {
                    for (sub in subs) {
                        if (!sub.evaluate()) {
                            return false
                        }
                    }

                    return true
                }
            }

            class OrExp : Exp() {
                override fun evaluate(): Boolean {
                    for (sub in subs) {
                        if (sub.evaluate()) {
                            return true
                        }
                    }

                    return false
                }
            }

            class NotExp : Exp() {
                override fun evaluate(): Boolean {
                    return !subs[0].evaluate()
                }
            }

            class TrueExp : Exp() {
                override fun evaluate(): Boolean {
                    return true
                }
            }

            class FalseExp : Exp() {
                override fun evaluate(): Boolean {
                    return false
                }
            }

            val root = AndExp()

            val stack = LinkedList<Exp>()
            stack.push(root)

            expression.forEach {
                when (it) {
                    't' -> {
                        stack.peek().subs.add(TrueExp())
                    }

                    'f' -> {
                        stack.peek().subs.add(FalseExp())
                    }

                    '!' -> {
                        NotExp().also {
                            stack.peek().subs.add(it)
                            stack.push(it)
                        }
                    }

                    '&' -> {
                        AndExp().also {
                            stack.peek().subs.add(it)
                            stack.push(it)
                        }
                    }

                    '|' -> {
                        OrExp().also {
                            stack.peek().subs.add(it)
                            stack.push(it)
                        }
                    }

                    ')' -> {
                        stack.poll()
                    }
                }
            }

            return root.evaluate()
        }
    }

    measureTimeMillis {
        Solution().parseBoolExpr(
            "&(|(f))"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}