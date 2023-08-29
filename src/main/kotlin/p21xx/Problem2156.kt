package p21xx

import util.expect

fun main() {
    class Solution {
        fun subStrHash(s: String, power: Int, modulo: Int, k: Int, hashValue: Int): String {
            var p = 1L
            val pow = LongArray(k) {
                p.also {
                    p *= power
                    p %= modulo
                }
            }

            var hash = 0L
            repeat(k) {
                hash += (s[s.length - k + it] - 'a' + 1) * pow[it]
                hash %= modulo
            }

            var resultIndex = s.length

            if (hash.toInt() == hashValue) {
                resultIndex = s.length - k
            }
            var index = s.lastIndex - k
            while (index >= 0) {
                hash -= (s[index + k] - 'a' + 1) * pow[k - 1]
                hash = hash.mod(modulo.toLong())
                hash *= power
                hash += s[index] - 'a' + 1
                hash = hash.mod(modulo.toLong())

                if (hash.toInt() == hashValue) {
                    resultIndex = index
                }

                index--
            }

            return s.substring(resultIndex until resultIndex + k)
        }
    }

    expect {
        Solution().subStrHash(
            "rlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblprlzblpbfleezoimfzxwnk",
            842163460,
            815354026,
            6,
            472331242
        )
    }
}