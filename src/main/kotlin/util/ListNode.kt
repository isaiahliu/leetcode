package util

class ListNode(var `val`: Int, var next: ListNode? = null) {
    override fun toString(): String {
        return "${`val`}${next?.toString()?.let { ", $it" }.orEmpty()}"
    }
}