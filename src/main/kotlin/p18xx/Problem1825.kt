package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class MKAverage(val m: Int, val k: Int) {
        fun TreeMap<Int, Int>.removeOne(num: Int) {
            this[num]?.also { count ->
                if (count == 1) {
                    remove(num)
                } else {
                    this[num] = count - 1
                }
            }
        }

        fun TreeMap<Int, Int>.pollFirstOne(): Int {
            return firstKey().also {
                removeOne(it)
            }
        }

        fun TreeMap<Int, Int>.pollLastOne(): Int {
            return lastKey().also {
                removeOne(it)
            }
        }

        val leftK = TreeMap<Int, Int>()
        var leftSize = 0
        val middle = TreeMap<Int, Int>()
        var middleSize = 0
        val rightK = TreeMap<Int, Int>()
        var rightSize = 0

        var middleSum = 0

        val nums = LinkedList<Int>()

        fun addElement(num: Int) {
            if (nums.size == m) {
                when {
                    num >= rightK.firstKey() -> {
                        rightK[num] = (rightK[num] ?: 0) + 1
                        rightSize++
                    }

                    num <= leftK.lastKey() -> {
                        leftK[num] = (leftK[num] ?: 0) + 1
                        leftSize++
                    }

                    else -> {
                        middleSum += num
                        middle[num] = (middle[num] ?: 0) + 1
                        middleSize++
                    }

                }

                val removeNum = nums.poll()

                when {
                    removeNum >= rightK.firstKey() -> {
                        rightK.removeOne(removeNum)
                        rightSize--
                    }

                    removeNum >= middle.firstKey() -> {
                        middleSum -= removeNum
                        middle.removeOne(removeNum)
                        middleSize--
                    }

                    else -> {
                        leftK.removeOne(removeNum)
                        leftSize--
                    }
                }
            } else {
                rightK[num] = (rightK[num] ?: 0) + 1
                rightSize++
            }

            nums.add(num)

            if (nums.size == m) {
                while (rightSize > k) {
                    rightK.pollFirstOne().also {
                        middle[it] = (middle[it] ?: 0) + 1
                        middleSum += it

                        rightSize--
                        middleSize++
                    }
                }

                while (leftSize > k) {
                    leftK.pollLastOne().also {
                        middle[it] = (middle[it] ?: 0) + 1
                        middleSum += it

                        leftSize--
                        middleSize++
                    }
                }

                while (rightSize < k) {
                    middle.pollLastOne().also {
                        rightK[it] = (rightK[it] ?: 0) + 1
                        middleSum -= it

                        rightSize++
                        middleSize--
                    }
                }

                while (leftSize < k) {
                    middle.pollFirstOne().also {
                        leftK[it] = (leftK[it] ?: 0) + 1
                        middleSum -= it

                        leftSize++
                        middleSize--
                    }
                }
            }
        }

        fun calculateMKAverage(): Int {
            return middleSum.takeIf { nums.size == m }?.let { middleSum / middleSize } ?: -1
        }
    }

    measureTimeMillis {
        val mk = MKAverage(6, 1)
        mk.addElement(3)
        mk.addElement(1)
        mk.addElement(12)
        mk.addElement(5)
        mk.addElement(3)
        mk.addElement(4)
        mk.calculateMKAverage().also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
