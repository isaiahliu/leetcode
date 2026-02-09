package p37xx

import util.expect
import kotlin.math.max
import kotlin.math.min

fun main() {
    class LazySegmentTree(private val n: Int) {
        inner class Node {
            var min: Int = 0
            var max: Int = 0
            var todo: Int = 0
        }

        private fun apply(node: Int, todo: Int) {
            val cur = tree[node]
            cur.min += todo
            cur.max += todo
            cur.todo += todo
        }

        private val tree: Array<LazySegmentTree.Node> = Array(2 shl (32 - Integer.numberOfLeadingZeros(n - 1))) { Node() }

        fun update(ql: Int, qr: Int, f: Int) {
            update(1, 0, n - 1, ql, qr, f)
        }

        fun findFirst(ql: Int, qr: Int, target: Int): Int {
            return findFirst(1, 0, n - 1, ql, qr, target)
        }

        private fun spread(node: Int) {
            val todo = tree[node].todo
            if (todo == 0) {
                return
            }
            apply(node * 2, todo)
            apply(node * 2 + 1, todo)
            tree[node].todo = 0
        }

        private fun maintain(node: Int) {
            tree[node].min = min(tree[node * 2].min, tree[node * 2 + 1].min)
            tree[node].max = max(tree[node * 2].max, tree[node * 2 + 1].max)
        }

        private fun update(node: Int, l: Int, r: Int, ql: Int, qr: Int, f: Int) {
            if (ql <= l && r <= qr) {
                apply(node, f)
                return
            }
            spread(node)
            val m = (l + r) / 2
            if (ql <= m) {
                update(node * 2, l, m, ql, qr, f)
            }
            if (qr > m) {
                update(node * 2 + 1, m + 1, r, ql, qr, f)
            }
            maintain(node)
        }

        private fun findFirst(node: Int, l: Int, r: Int, ql: Int, qr: Int, target: Int): Int {
            if (l > qr || r < ql || target < tree[node].min || target > tree[node].max) {
                return -1
            }
            if (l == r) {
                return l
            }
            spread(node)
            val m = (l + r) / 2
            var idx = findFirst(node * 2, l, m, ql, qr, target)
            if (idx < 0) {
                idx = findFirst(node * 2 + 1, m + 1, r, ql, qr, target)
            }
            return idx
        }
    }

    class Solution {
        fun longestBalanced(nums: IntArray): Int {
            val n = nums.size
            val t = LazySegmentTree(n + 1)

            val last: MutableMap<Int, Int> = hashMapOf()
            var ans = 0
            var curSum = 0

            for (i in 1..n) {
                val x = nums[i - 1]
                val v = if (x % 2 > 0) 1 else -1
                val j = last[x]
                if (j == null) {
                    curSum += v
                    t.update(i, n, v)
                } else {
                    t.update(j, i - 1, -v)
                }
                last[x] = i

                val l = t.findFirst(0, i - 1 - ans, curSum)
                if (l >= 0) {
                    ans = i - l
                }
            }
            return ans
        }
    }

    expect {
        Solution().longestBalanced(
            intArrayOf(30, 3, 21, 22, 52, 40, 12, 43, 21)
        )
    }
}
