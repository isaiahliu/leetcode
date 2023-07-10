package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun parseBoolExpr(expression: String): Boolean {
            abstract class Exp(val pushThis: Boolean) {
                val subs = arrayListOf<Exp>()

                abstract fun evaluate(): Boolean
            }

            class AndExp : Exp(true) {
                override fun evaluate(): Boolean {
                    for (sub in subs) {
                        if (!sub.evaluate()) {
                            return false
                        }
                    }

                    return true
                }
            }

            class OrExp : Exp(true) {
                override fun evaluate(): Boolean {
                    for (sub in subs) {
                        if (sub.evaluate()) {
                            return true
                        }
                    }

                    return false
                }
            }

            class NotExp : Exp(true) {
                override fun evaluate(): Boolean {
                    return !subs[0].evaluate()
                }
            }

            class TrueExp : Exp(false) {
                override fun evaluate(): Boolean {
                    return true
                }
            }

            class FalseExp : Exp(false) {
                override fun evaluate(): Boolean {
                    return false
                }
            }

            val stack = LinkedList<Exp>()

            fun Exp.append() {
                stack.peek()?.subs?.add(this)

                if (pushThis) {
                    stack.push(this)
                }
            }

            AndExp().append()

            expression.forEach {
                when (it) {
                    't' -> {
                        TrueExp().append()
                    }

                    'f' -> {
                        FalseExp().append()
                    }

                    '!' -> {
                        NotExp().append()
                    }

                    '&' -> {
                        AndExp().append()
                    }

                    '|' -> {
                        OrExp().append()
                    }

                    ')' -> {
                        stack.poll()
                    }
                }
            }

            return stack.peekLast().evaluate()
        }
    }

    measureTimeMillis {
        Solution().parseBoolExpr(
            "&(|(f))"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}