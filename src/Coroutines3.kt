import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis


suspend fun main(args: Array<String>) {
    val vte = Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher()
    val milis = measureTimeMillis {
        //withContext(Dispatchers.Default) {
        withContext(vte) {
                testStarving()
            }
    }
    println(milis)
    //testStarving2()
}

suspend fun testStarving() = coroutineScope {
    val a1 = async {
        var sum = 2.0
        for (i in 1..100000000) {
            sum += sum / i
            //yield()
        }
        println("first - ${Thread.currentThread().name}")
        sum
    }
    val a2 = async {
        var sum = 2.0
        for (i in 1..100000000) {
            sum += sum / i
            //yield()
        }
        println("second - ${Thread.currentThread().name}")
        sum
    }
    val a3 = async {
        var sum = 2.0
        for (i in 1..100000000) {
            sum += sum / i
            //yield()
        }
        println("third - ${Thread.currentThread().name}")
        sum
    }
    println("result - ${a1.await()} and ${a2.await()} and ${a3.await()}")
}

//suspend fun testStarving2() = coroutineScope {
//    launch {
//        var sum = 2.0
//        for (i in 1..100000) {
//            sum += sum / i
//            //yield()
//        }
//        println("third - ${Thread.currentThread().name}")
//    }
//    launch {
//        println("forth - ${Thread.currentThread().name}")
//    }
//}

