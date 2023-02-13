package p00xx

fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class Solution {
        fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
            val root = ListNode(Int.MAX_VALUE).also { it.next = head }

            val array = arrayOfNulls<ListNode>(n + 1)

            var index = 0

            var t: ListNode? = root
            while (t != null) {
                array[index++] = t

                t = t.next
                index %= array.size
            }

            array[index]?.also {
                it.next = array[(index + 2) % array.size]?.takeIf { n -> n != it }
            }


            return root.next
        }
    }

    val a = Solution().removeNthFromEnd(ListNode(1).also { it.next = ListNode(2) }, 1)
    println(a)
}

