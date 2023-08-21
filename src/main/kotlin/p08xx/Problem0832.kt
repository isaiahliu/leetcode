package p08xx

import util.expect

fun main() {
    class Solution {
        fun flipAndInvertImage(image: Array<IntArray>): Array<IntArray> {
            return Array(image.size) { r ->
                IntArray(image[r].size) { c ->
                    1 - image[r][image[r].lastIndex - c]
                }
            }
        }
    }

    expect {
        Solution().flipAndInvertImage(
            arrayOf()
        )
    }
}