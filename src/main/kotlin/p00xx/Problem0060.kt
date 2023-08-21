package p00xx

fun main() {
    class Solution {
        fun getPermutation(n: Int, k: Int): String {
            val nums = IntArray(n) { it + 1 }.toMutableList()

            var t = 1
            val mul = IntArray(n - 1) {
                t *= (it + 1)
                t
            }
            mul.reverse()

            var tempK = k - 1
            val result = StringBuilder()
            repeat(n - 1) {
                val tn = tempK / mul[it]

                result.append(nums[tn].toString())
                nums.removeAt(tn)

                tempK %= mul[it]
            }

            result.append(nums[0].toString())

            return result.toString()
        }
    }

    Solution().getPermutation(3, 3)
}

