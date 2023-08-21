package p06xx

import util.expect

fun main() {
    class Solution {
        fun minStickers(stickers: Array<String>, target: String): Int {
            val sortedStickers = stickers.map { String(it.toCharArray().also { it.sort() }) }

            fun cut(sticker: String, word: String): String? {
                val str = StringBuilder()

                var idx1 = 0
                var idx2 = 0

                while (idx1 < sticker.length && idx2 < word.length) {
                    when {
                        sticker[idx1] < word[idx2] -> {
                            idx1++
                        }

                        sticker[idx1] > word[idx2] -> {
                            str.append(word[idx2++])
                        }

                        else -> {
                            idx1++
                            idx2++
                        }
                    }
                }

                while (idx2 < word.length) {
                    str.append(word[idx2++])
                }

                return str.takeIf { it.length < word.length }?.toString()
            }

            val cache = hashMapOf<Pair<String, Int>, Int?>()

            fun process(word: String, stickerIndex: Int): Int? {
                if (stickerIndex >= sortedStickers.size) {
                    return null
                }

                val cacheKey = word to stickerIndex
                if (cacheKey in cache) {
                    return cache[cacheKey]
                }

                val sticker = sortedStickers[stickerIndex]

                val cutWord = cut(sticker, word)?.ifEmpty {
                    return 1
                }

                var result = process(word, stickerIndex + 1)
                if (cutWord != null) {
                    process(cutWord, stickerIndex)?.let { it + 1 }?.also { p2 ->
                        if (result?.takeIf { it < p2 } == null) {
                            result = p2
                        }
                    }
                }

                cache[cacheKey] = result

                return result
            }

            return process(String(target.toCharArray().also { it.sort() }), 0) ?: -1
        }
    }

    expect {
        Solution().minStickers(
            arrayOf("with", "example", "science"), "thehat"
        )
    }
}