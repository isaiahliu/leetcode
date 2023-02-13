package p00xx

import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun threeSumClosest(nums: IntArray, target: Int): Int {
            nums.sort()

            var min = nums[0] + nums[1] + nums[2]

            for (i in nums.indices) {
                var sumIJ = Int.MIN_VALUE
                for (j in i + 1 until nums.size) {
                    if (sumIJ > target && nums[j] > 0) {
                        break
                    } else {
                        sumIJ = nums[i] + nums[j]
                    }

                    var sumIJK = Int.MIN_VALUE
                    for (k in j + 1 until nums.size) {
                        if (sumIJK > target) {
                            break
                        } else {
                            sumIJK = nums[i] + nums[j] + nums[k]
                        }

                        if ((sumIJK - target).absoluteValue < (min - target).absoluteValue) {
                            min = sumIJK
                        }
                    }
                }
            }

            return min
        }
    }
}

