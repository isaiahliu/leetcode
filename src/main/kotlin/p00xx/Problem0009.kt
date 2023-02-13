package p00xx

fun main() {
    class Solution {
        fun isPalindrome(x: Int): Boolean {
            if (x < 0) {
                return false
            }

            var t = x
            var newNum = 0

            while (t > 0) {
                newNum *= 10
                newNum += t % 10

                t /= 10
            }
            return newNum == x
        }
    }

}

