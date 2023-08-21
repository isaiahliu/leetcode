package p08xx

import java.util.*
import util.expect

fun main() {
    class FreqStack {
        val counts = hashMapOf<Int, Int>()

        val stacks = hashMapOf<Int, LinkedList<Int>>()
        var maxCount = 0
        fun push(`val`: Int) {
            val count = (counts[`val`] ?: 0) + 1

            maxCount = maxCount.coerceAtLeast(count)

            stacks.computeIfAbsent(count) { LinkedList<Int>() }.push(`val`)
            counts[`val`] = count
        }

        fun pop(): Int {
            while (true) {
                val num = stacks[maxCount]?.poll()

                if (num == null) {
                    maxCount--
                } else {
                    counts[num] = (counts[num] ?: 0) - 1
                    return num
                }
            }
        }
    }

    expect {
        val fs = FreqStack()
        fs.push(5)
        fs.push(7)
        fs.push(5)
        fs.push(7)
        fs.push(4)
        fs.push(5)
        fs.pop()
        fs.pop()
        fs.pop()
        fs.pop()
    }
}