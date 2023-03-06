package p03xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Twitter {
        val tweetIds = hashMapOf<Int, Int>()

        var id = 0

        val userTweets = hashMapOf<Int, TreeSet<Int>>()

        val lastTweets = hashMapOf<Int, Int>()

        val follows = hashMapOf<Int, MutableSet<Int>>()

        fun postTweet(userId: Int, tweetId: Int) {
            id++

            follows.computeIfAbsent(userId) { hashSetOf() }.add(userId)

            tweetIds[id] = tweetId

            lastTweets[userId] = id
            userTweets.computeIfAbsent(userId) { TreeSet() }.add(id)
        }

        fun getNewsFeed(userId: Int): List<Int> {
            return follows[userId]?.let {
                it.asSequence().sortedByDescending { (lastTweets[it] ?: 0) }.take(10).map {
                    userTweets[it]?.toList()?.takeLast(10).orEmpty()
                }.flatten().sortedDescending().take(10).mapNotNull { tweetIds[it] }.toList()
            }.orEmpty()
        }

        fun follow(followerId: Int, followeeId: Int) {
            follows.computeIfAbsent(followerId) {
                hashSetOf()
            }.add(followeeId)
        }

        fun unfollow(followerId: Int, followeeId: Int) {
            if (followerId != followeeId) {
                follows.computeIfAbsent(followerId) {
                    hashSetOf()
                }.remove(followeeId)
            }
        }
    }

    measureTimeMillis {
        val t = Twitter()
        t.postTweet(1, 5)
        t.follow(1, 2)
        t.follow(2, 1)
        t.getNewsFeed(2).also { println(it) }
        t.postTweet(2, 6)
        t.getNewsFeed(1).also { println(it) }
        t.getNewsFeed(2).also { println(it) }
        t.unfollow(2, 1)
        t.getNewsFeed(1).also { println(it) }
        t.getNewsFeed(2).also { println(it) }
        t.unfollow(1, 2)
        t.getNewsFeed(1).also { println(it) }
        t.getNewsFeed(2).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}

