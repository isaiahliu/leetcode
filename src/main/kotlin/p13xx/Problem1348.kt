package p13xx

import java.util.*
import util.expect

fun main() {
    class TweetCounts {
        val tweets = hashMapOf<String, TreeMap<Int, Int>>()
        fun recordTweet(tweetName: String, time: Int) {
            tweets.computeIfAbsent(tweetName) { TreeMap() }.also {
                it[time] = (it[time] ?: 0) + 1
            }
        }

        fun getTweetCountsPerFrequency(freq: String, tweetName: String, startTime: Int, endTime: Int): List<Int> {
            return tweets[tweetName]?.let {
                val period = when (freq) {
                    "minute" -> 60
                    "hour" -> 3600
                    else -> 86400
                }

                val result = arrayListOf<Int>()

                var s = startTime
                var e = (s + period - 1).coerceAtMost(endTime)

                while (s <= endTime) {
                    result.add(it.subMap(s, true, e, true).values.sum())

                    s = e + 1
                    e = (s + period - 1).coerceAtMost(endTime)
                }

                result
            }.orEmpty()
        }
    }

    expect {
        TweetCounts().recordTweet(
            "", 1
        )
    }
}

