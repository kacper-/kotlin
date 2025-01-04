import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.ln

var threadStarted = AtomicInteger(0)
var threadFinished = AtomicInteger(0)
var k = 0

val mx: ThreadMXBean = ManagementFactory.getThreadMXBean()

fun main(args: Array<String>) {
    println("Start")
    Thread {
        val start = Date().time
        while (threadFinished.get() < 10) {
            System.out.printf(
                "count = %d / %d%n",
                threadStarted.get() - threadFinished.get(),
                mx.getAllThreadIds().size
            )
            try {
                Thread.sleep(250)
            } catch (e: InterruptedException) {
                throw java.lang.RuntimeException(e)
            }
        }
        val stop = Date().time
        println("time = " + (stop - start))
    }.start()

    k = 0
    while (k < 10) {
        start(k)
        k++
    }

    println("Stop " + mx.allThreadIds.size)
}

fun start(k: Int) = GlobalScope.async {
    threadStarted.incrementAndGet()
    val n = k
    var a = (n + 2).toDouble()
    for (i in 0..49) {
        try {
            for (j in 0..9999999) {
                a += ln(a)
            }
            Thread.sleep(8 + (12 * n.toLong()))
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
    println("Thread $n finished with = $a")
    threadFinished.incrementAndGet()
}
