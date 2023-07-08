package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class MountainArray {
        val array = intArrayOf(1, 2, 3, 5, 3)
        fun get(index: Int): Int {
            return array[index]
        }

        fun length(): Int {
            return array.size
        }
    }

    class Solution {
        fun findInMountainArray(target: Int, mountainArr: MountainArray): Int {
            val cache = hashMapOf<Int, Int>()

            fun getNum(index: Int): Int {
                return cache.computeIfAbsent(index) {
                    mountainArr.get(it)
                }
            }

            val length = mountainArr.length()

            fun findInc(start: Int, end: Int): Int? {
                val startNum = getNum(start)
                val endNum = getNum(end)

                return when {
                    startNum == target -> {
                        start
                    }

                    endNum == target -> {
                        end
                    }

                    end - start <= 1 -> {
                        null
                    }

                    startNum > target || endNum < target -> {
                        null
                    }

                    else -> {
                        val mid = (start + end) / 2
                        val midNum = getNum(mid)

                        when {
                            midNum > target -> {
                                findInc(start, mid)
                            }

                            midNum < target -> {
                                findInc(mid, end)
                            }

                            else -> {
                                mid
                            }
                        }
                    }
                }
            }

            fun findDec(start: Int, end: Int): Int? {
                val startNum = getNum(start)
                val endNum = getNum(end)

                return when {
                    startNum == target -> {
                        start
                    }

                    endNum == target -> {
                        end
                    }

                    end - start <= 1 -> {
                        null
                    }

                    startNum < target || endNum > target -> {
                        null
                    }

                    else -> {
                        val mid = (start + end) / 2
                        val midNum = getNum(mid)

                        when {
                            midNum < target -> {
                                findDec(start, mid)
                            }

                            midNum > target -> {
                                findDec(mid, end)
                            }

                            else -> {
                                mid
                            }
                        }
                    }
                }
            }

            fun findMount(start: Int, end: Int): Int? {
                if (start > end) {
                    return null
                }

                val startNum = getNum(start)

                if (startNum == target) {
                    return start
                }

                if (start == end) {
                    return null
                }

                val mid1 = (start + end) / 2
                val midNum1 = getNum(mid1)

                val mid2 = mid1 + 1
                val midNum2 = getNum(mid2)

                val inc = midNum1 < midNum2

                if (inc) {
                    (findInc(start, mid1) ?: findMount(mid2, end))?.also {
                        return it
                    }
                } else {
                    (findMount(start, mid1) ?: findDec(mid2, end))?.also {
                        return it
                    }
                }

                return null
            }

            return findMount(0, length - 1) ?: -1
        }
    }

    measureTimeMillis {
        Solution().findInMountainArray(
            3, MountainArray()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
