package p20xx

import util.expect

fun main() {
    class Solution {
        fun wateringPlants(plants: IntArray, capacity: Int): Int {
            var current = capacity

            var index = 0

            var result = 0
            while (index < plants.size) {
                if (current < plants[index]) {
                    current = capacity
                    result += index * 2
                }
                current -= plants[index]
                result++
                index++
            }

            return result
        }
    }

    expect {
        Solution().wateringPlants(
            intArrayOf(), 1
        )
    }
}