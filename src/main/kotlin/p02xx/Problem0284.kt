package p02xx

import util.expect

fun main() {
    class PeekingIterator(private val iterator: Iterator<Int>) : Iterator<Int> {
        var tempNum: Int? = null

        fun peek(): Int {
            return tempNum ?: run {
                iterator.next().also { tempNum = it }
            }
        }

        override fun next(): Int {
            return tempNum?.also { tempNum = null } ?: iterator.next()
        }

        override fun hasNext(): Boolean {
            return tempNum != null || iterator.hasNext()
        }
    }

    expect {
        PeekingIterator(intArrayOf().iterator())
    }
}

