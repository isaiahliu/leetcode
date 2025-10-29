package p33xx

fun main() {
    class Solution {
        fun smallestNumber(n: Int): Int {
            return n.takeHighestOneBit() * 2 - 1
        }
    }
}
