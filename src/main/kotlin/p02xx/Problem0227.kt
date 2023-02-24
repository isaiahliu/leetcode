package p02xx

import java.util.*

fun main() {
    class Solution {
        fun calculate(s: String): Int {
            val priority = hashMapOf(
                '+' to 1, '-' to 1, '*' to 2, '/' to 2
            )

            val queue = LinkedList<Any>()

            var index = 0
            var waitingForOperator = false
            var hasNum = false
            var currentNum = 0

            val operatorStack = LinkedList<Char>()

            fun pushNum() {
                if (hasNum) {
                    queue.add(currentNum)

                    if (operatorStack.firstOrNull() == 'n') {
                        queue.add(operatorStack.pop())
                    }

                    currentNum = 0
                    hasNum = false
                }
                waitingForOperator = true
            }

            fun pushOperator(op: Char) {
                while (operatorStack.isNotEmpty()) {
                    if ((priority[op] ?: break) > (priority[operatorStack.peek()] ?: break)) {
                        break
                    } else {
                        queue.add(operatorStack.pop())
                    }
                }

                operatorStack.push(op)
            }
            while (index < s.length) {
                when (val c = s[index++]) {
                    ' ' -> {
                    }

                    '+', '*', '/' -> {
                        pushNum()
                        pushOperator(c)
                        waitingForOperator = false
                    }

                    '-' -> {
                        if (waitingForOperator) {
                            pushNum()
                            pushOperator(c)
                            waitingForOperator = false
                        } else {
                            if (operatorStack.firstOrNull() == 'n') {
                                operatorStack.pop()
                            } else {
                                operatorStack.push('n')
                            }
                        }
                    }

                    '(' -> {
                        operatorStack.push(c)
                    }

                    ')' -> {
                        pushNum()

                        var op = operatorStack.pop()
                        while (op != '(') {
                            queue.add(op)

                            op = operatorStack.pop()
                        }

                        if (operatorStack.firstOrNull() == 'n') {
                            queue.add(operatorStack.pop())
                        }

                        waitingForOperator = true
                    }

                    in '0'..'9' -> {
                        currentNum = currentNum * 10 + (c - '0')
                        hasNum = true
                        waitingForOperator = true
                    }

                    else -> {
                        throw RuntimeException("Error")
                    }
                }
            }

            if (waitingForOperator) {
                pushNum()
            }

            queue.addAll(operatorStack)

            val calculatorStack = LinkedList<Int>()

            queue.forEach {
                when (it) {
                    is Int -> calculatorStack.push(it)
                    '+' -> {
                        calculatorStack.push(calculatorStack.pop() + calculatorStack.pop())
                    }

                    '-' -> {
                        val num2 = calculatorStack.pop()
                        val num1 = calculatorStack.pop()

                        calculatorStack.push(num1 - num2)
                    }

                    '*' -> {
                        calculatorStack.push(calculatorStack.pop() * calculatorStack.pop())
                    }

                    '/' -> {
                        val num2 = calculatorStack.pop()
                        val num1 = calculatorStack.pop()

                        calculatorStack.push(num1 / num2)
                    }

                    'n' -> {
                        calculatorStack.push(-calculatorStack.pop())
                    }
                }
            }

            return calculatorStack.pop()
        }
    }

    println(
        Solution().calculate(
            "3+2*2"
        )
    )
}

