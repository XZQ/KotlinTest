package org.example

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

// https://blog.csdn.net/android_cai_niao/article/details/112849212
// https://juejin.cn/post/6950616789390721037


fun main() {
//    testSignleThread()
//    tesGlobalScopeLaunch()
//    testMultiThread()
//    testRunBlocking()
    testAsync()
}


@OptIn(DelicateCoroutinesApi::class)
fun testAsync() {
    GlobalScope.launch {
        val deferred = async(Dispatchers.IO) {
            delay(3000)
            println("date = 23")
            "Test"
        }
        val date = deferred.await()
        println("date = $date")
    }
}

fun testRunBlocking() {
    val start = System.currentTimeMillis()
    val set = mutableSetOf<String>()
    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async {
            set.add(Thread.currentThread().name)
            n
        }
    }

    runBlocking {
        val sum = deferred.sumOf { it.await().toLong() }
        val useTime = System.currentTimeMillis() - start
        println("end，useTime = ${useTime}，sum = $sum，threadCount = ${set.size}")
        set.forEach(System.out::println)
    }
}


@OptIn(DelicateCoroutinesApi::class)
fun tesGlobalScopeLaunch() {
    val start = System.currentTimeMillis()
    val c = AtomicLong()
    for (i in 1..1_000_000L)
        GlobalScope.launch {
            c.addAndGet(i)
        }

    val useTime = System.currentTimeMillis() - start
    println("end，useTime = ${useTime}，sum = ${c.get()}")
}

// 2s
fun testSignleThread() {
    val start = System.currentTimeMillis()
    var sum = 0L
    for (i in 1..1_000_000L) {
        sum += i
    }
    val useTime = System.currentTimeMillis() - start
    println("end，useTime = ${useTime}，sum = $sum")
}

// 50s
fun testMultiThread() {
    val start = System.currentTimeMillis()
    val atomicLong = AtomicLong()
    for (i in 1..1_000_000L)
        thread(start = true) {
            atomicLong.addAndGet(i)
        }

    val useTime = System.currentTimeMillis() - start
    println("end，useTime = ${useTime}，sum = ${atomicLong.get()}")
}