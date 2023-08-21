package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxNumber(nums1: IntArray, nums2: IntArray, k: Int): IntArray {
            var max = ""
            var maxResult: IntArray = intArrayOf()

            fun IntArray.buildDequeue(requiredSize: Int): LinkedList<Int> {
                val result = LinkedList<Int>()
                if (requiredSize == 0) {
                    return result
                }

                var index = 0
                var remainingSize = this.size
                var dequeueSize = 0

                while (index < this.size) {
                    if (result.isEmpty() || result.peekLast() >= this[index]) {
                        result.add(this[index++])
                        dequeueSize++
                    } else {
                        break
                    }
                }

                while (index < this.size && requiredSize < remainingSize) {
                    if (result.isNotEmpty() && result.peekLast() < this[index]) {
                        result.pollLast()
                        dequeueSize--
                        remainingSize--
                    } else {
                        result.add(this[index++])
                        dequeueSize++
                    }
                }

                while (dequeueSize < requiredSize) {
                    result.add(this[index++])
                    dequeueSize++
                }

                while (dequeueSize > requiredSize) {
                    result.pollLast()
                    dequeueSize--
                }

                return result
            }

            for (i in (k - nums2.size).coerceAtLeast(0)..nums1.size.coerceAtMost(k)) {
                val dequeues = arrayOf(nums1.buildDequeue(i), nums2.buildDequeue(k - i))

                val str = StringBuilder()
                val array = IntArray(k)
                repeat(k) {
                    val num = when {
                        dequeues[0].isEmpty() -> {
                            dequeues[1].pop()
                        }

                        dequeues[1].isEmpty() -> {
                            dequeues[0].pop()
                        }

                        dequeues[1].peek() > dequeues[0].peek() -> {
                            dequeues[1].pop()
                        }

                        dequeues[1].peek() < dequeues[0].peek() -> {
                            dequeues[0].pop()
                        }

                        else -> {
                            var maxQueueIndex: Int? = null
                            var index = 1
                            while (maxQueueIndex == null) {
                                maxQueueIndex = 0
                                val next1 = dequeues[1].getOrNull(index) ?: break

                                maxQueueIndex = 1
                                val next0 = dequeues[0].getOrNull(index) ?: break

                                when {
                                    next0 > next1 -> {
                                        maxQueueIndex = 0
                                    }

                                    next1 > next0 -> {
                                        maxQueueIndex = 1
                                    }

                                    else -> {
                                        maxQueueIndex = null
                                        index++
                                    }
                                }
                            }

                            dequeues[maxQueueIndex!!].pop()
                        }
                    }

                    array[it] = num
                    str.append(num.toString())
                }

                if (str.toString() > max) {
                    max = str.toString()
                    maxResult = array
                }
            }

            return maxResult
        }
    }

    expect {
        Solution().maxNumber(
            intArrayOf(
                3, 4, 6, 5
            ), intArrayOf(
                9, 1, 2, 5, 8, 3
            ), 5
        ).toList()
    }
}

