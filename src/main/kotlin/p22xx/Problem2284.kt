package p22xx

import util.expect

fun main() {
    class Solution {
        fun largestWordCount(messages: Array<String>, senders: Array<String>): String {
            val sent = hashMapOf<String, Int>()

            messages.forEachIndexed { index, s ->
                sent[senders[index]] = (sent[senders[index]] ?: 0) + s.count { it == ' ' } + 1
            }

            return sent.maxWith(compareBy<Map.Entry<String, Int>> { it.value }.thenBy { it.key }).key
        }
    }

    expect {
        Solution().largestWordCount(
            arrayOf("Hello userTwooo", "Hi userThree", "Wonderful day Alice", "Nice day userThree"),
            arrayOf("Alice", "userTwo", "userThree", "Alice")
        )
    }
}