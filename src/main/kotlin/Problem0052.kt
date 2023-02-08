fun main() {
    class Solution {
        fun totalNQueens(n: Int): Int {
            val pos = arrayListOf(-1)

            var result = 0

            while (pos.isNotEmpty()) {
                if (pos.size == n + 1) {
                    pos.removeAt(n)

                    result++
                }

                val last = pos.last()
                pos.remove(last)

                (last + 1 until n).firstOrNull { new ->
                    val index = pos.size

                    pos.filterIndexed { i, n ->
                        n == new || (index - i) == (n - new) || (index - i) == (new - n)
                    }.isEmpty()
                }?.let {
                    pos += it
                    pos += -1
                }
            }

            return result
        }
    }
}

