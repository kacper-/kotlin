import kotlinx.coroutines.*
import kotlinx.coroutines.future.await

suspend fun  main(args: Array<String>) {
    println(Runtime.getRuntime().availableProcessors())
    println("Start of processing")
    go0()
    delay(800L)
    println("End of processing")
}

suspend fun test() = coroutineScope {
    launch {
        println("test - "+this.coroutineContext)
        delay(2500)
        println("End of test")
    }
}

suspend fun go0() = coroutineScope {

    println("go0 ${Thread.currentThread().name}")

    val t = withTimeoutOrNull(2000) {
        launch {  go1() }
        launch { test() }
        }

    println("Join withTimeoutOrNull")
    println(t)

}

suspend fun go1() {
    println("go1 ${Thread.currentThread().name}")
    withContext(Dispatchers.IO) {
        go()
    }
    println("Join withContext")
}

suspend fun go() = coroutineScope {
    launch {
        println("CompletableFuture START")
        val jclass = JClass()
        val str = jclass.run().await()
        println(str)
    }

    for(i in 1..10) {
        launch {
            gogo(i)
        }
        println("started $i")
    }
    println("go - "+this.coroutineContext)
}

suspend fun gogo(i: Int) = coroutineScope {
    //println("gogo ${Thread.currentThread().name}")
    launch {
        delay(1300L)
        println("+$i")
    }

    launch {
        delay(800L)
        println(i)
    }
    println("gogo - "+this.coroutineContext)
}
