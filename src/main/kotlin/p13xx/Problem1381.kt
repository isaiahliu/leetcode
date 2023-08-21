package p13xx

import java.util.*
import util.expect

fun main() {
    class CustomStack(private val maxSize: Int) {
        val increment = TreeMap<Int, Int>()
        val stack = LinkedList<Int>()

        fun push(x: Int) {
            if (stack.size < maxSize) {
                stack.push(x)
            }
        }

        fun pop(): Int {
            var result = stack.poll() ?: return -1
            increment.lastEntry()?.takeIf { it.key == stack.size + 1 }?.also { (key, value) ->
                increment.pollLastEntry()

                result += value

                if (key > 1) {
                    increment[key - 1] = (increment[key - 1] ?: 0) + value
                }
            }

            return result
        }

        fun increment(k: Int, `val`: Int) {
            k.coerceAtMost(stack.size).also {
                increment[it] = (increment[it] ?: 0) + `val`
            }
        }
    }

    expect {
        CustomStack(5).push(1)
    }
}

