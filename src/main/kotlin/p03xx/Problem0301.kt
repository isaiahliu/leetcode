package p03xx

import java.util.*

fun main() {
    class Solution {
        val result = hashSetOf<String>()

        var str = ""

        fun IntArray.serialize(): String {
            return mapIndexed { index, i ->
                when {
                    i == Int.MAX_VALUE -> {
                        str[index].toString()
                    }

                    i > 0 -> {
                        "(".repeat(i)
                    }

                    i < 0 -> {
                        ")".repeat(-i)
                    }

                    else -> {
                        ""
                    }
                }
            }.joinToString("")
        }

        fun removeInvalidParentheses(s: String): List<String> {
            if (s.isEmpty()) {
                return emptyList()
            }

            str = s
            result.clear()

            var lastIndex = 0
            var last = ' '
            val counts = IntArray(s.length)

            s.forEachIndexed { index, c ->
                when (c) {
                    '(' -> {
                        if (c != last) {
                            lastIndex = index
                        }
                        counts[lastIndex]++
                    }

                    ')' -> {
                        if (c != last) {
                            lastIndex = index
                        }
                        counts[lastIndex]--
                    }

                    else -> {
                        counts[index] = Int.MAX_VALUE
                    }
                }

                last = c
            }

            loop@ for (i in counts.indices) {
                when (val count = counts[i]) {
                    0, Int.MAX_VALUE -> {}
                    else -> {
                        if (count > 0) {
                            break@loop
                        } else {
                            counts[i] = 0
                        }
                    }
                }
            }

            loop@ for (i in counts.lastIndex downTo 1) {
                when (val count = counts[i]) {
                    0, Int.MAX_VALUE -> {}
                    else -> {
                        if (count < 0) {
                            break@loop
                        } else {
                            counts[i] = 0
                        }
                    }
                }
            }

            process(counts.clone())

            return result.toList()
        }

        fun process(counts: IntArray) {
            val sums = IntArray(counts.size)
            var lastSum = 0

            val leftIndices = arrayListOf<Int>()
            val rightIndices = arrayListOf<Int>()

            var extraRightCount = 0
            var firstHighestNegativeIndex = 0
            counts.forEachIndexed { index, i ->
                when (i) {
                    0, Int.MAX_VALUE -> {}
                    else -> {
                        lastSum += i

                        if (lastSum < extraRightCount) {
                            extraRightCount = lastSum
                            firstHighestNegativeIndex = index
                        }

                        sums[index] = lastSum

                        if (i > 0) {
                            leftIndices.add(index)
                        } else {
                            rightIndices.add(index)
                        }
                    }
                }
            }

            if (extraRightCount == 0 && lastSum == 0) {
                result.add(counts.serialize())
                return
            }

            fun processLeft() {
                val removableLefts = arrayListOf<IntArray>()
                var extraLeftCount = 0
                var tempSum = 0
                counts.forEachIndexed { index, i ->
                    if (i != Int.MAX_VALUE && i != 0) {
                        tempSum += i
                        extraLeftCount = tempSum
                        if (index in leftIndices) {
                            removableLefts += intArrayOf(index, extraLeftCount)
                        } else if (index in rightIndices) {
                            if (tempSum == 0) {
                                removableLefts.clear()
                            } else {
                                removableLefts.forEach { arr ->
                                    arr[1] = arr[1].coerceAtMost(tempSum)
                                }
                            }
                        }
                    }
                }

                if (extraLeftCount == 0) {
                    result.add(counts.serialize())
                    return
                }

                val stack = LinkedList<Pair<Int, Int>>()

                var t = extraLeftCount
                while (true) {
                    var done = false
                    while (stack.isNotEmpty()) {
                        done = true
                        val (leftIndex, dropCount) = stack.pop()

                        if (dropCount > 0) {
                            stack.push(leftIndex to dropCount - 1)
                            counts[leftIndex]++
                            t += 1
                            done = false


                            break
                        } else {
                            counts[leftIndex] += dropCount
                            t -= dropCount
                        }
                    }

                    if (done) {
                        break
                    }
                    for (i in stack.size until removableLefts.size) {
                        val (leftIndex, maxExtraCount) = removableLefts[i]

                        if (t == 0) {
                            break
                        }

                        val accumed = extraLeftCount - t
                        val need = (maxExtraCount - accumed).coerceAtMost(t)
                        val have = counts[leftIndex]
                        val n = need.coerceAtMost(have)

                        counts[leftIndex] -= n
                        stack.push(leftIndex to n)

                        t -= n
                    }

                    if (t == 0) {
                        result.add(counts.serialize().also {
                            println(it)
                        })
                    }
                }
            }

            if (extraRightCount < 0) {
                val removableRights = arrayListOf<IntArray>()
                sums.forEachIndexed { index, i ->
                    if (index in rightIndices) {
                        removableRights += intArrayOf(index, i)
                    }
                }

                val rightStack = LinkedList<Pair<Int, Int>>()
                var t = extraRightCount
                while (true) {
                    var done = false
                    while (rightStack.isNotEmpty()) {
                        done = true
                        val (rightIndex, dropCount) = rightStack.pop()

                        val accum = extraRightCount - t
                        val ttt = removableRights.first { it[0] == rightIndex }[1]
                        if (accum < ttt && dropCount < 0) {
                            rightStack.push(rightIndex to dropCount + 1)
                            counts[rightIndex]--
                            t--
                            done = false

                            break
                        } else {
                            counts[rightIndex] += dropCount
                            t += dropCount
                        }
                    }

                    if (done) {
                        break
                    }

                    for (i in rightStack.size until removableRights.size) {
                        val (rightIndex, maxExtraCount) = removableRights[i]

                        if (rightIndex > firstHighestNegativeIndex) {
                            break
                        }

                        val need = t.coerceAtMost(maxExtraCount)
                        val have = counts[rightIndex]

                        val can = need.coerceAtLeast(have)
                        val n = can
                        t -= n

                        counts[rightIndex] -= n
                        rightStack.push(rightIndex to n)

                        if (t == 0) {
                            break
                        }
                    }

                    if (t == 0) {
                        processLeft()
                    }
                }
            } else {
                processLeft()
            }
        }
    }

//    println(Solution().removeInvalidParentheses("((a)((b)((c)"))
    println(Solution().removeInvalidParentheses("(k()(((()"))
//    println(Solution().removeInvalidParentheses("())()))())))"))
}

