import kotlinx.coroutines.*

suspend fun  main(args: Array<String>) {
    println(Runtime.getRuntime().availableProcessors())
    println("Start")
    go0()
    test()
    println("Stop2")
}

suspend fun test() {

}

suspend fun go0() = coroutineScope {
    println("go0 ${Thread.currentThread().name}")

    val t = withTimeoutOrNull(2000) {
            go1()
        }

    println("Stop")
    println(t)
}

suspend fun go1() {
    println("go1 ${Thread.currentThread().name}")
    withContext(Dispatchers.Default) {
        go()
    }
    println("Stop3")
}

suspend fun go() = coroutineScope {
    async {
        val jclass = JClass()
        jclass.start()
    }

    for(i in 1..10) {
        launch {
            gogo(i)
        }
        println("started $i")
    }
    println(this.coroutineContext)
}

suspend fun gogo(i: Int) = coroutineScope {
    println("gogo ${Thread.currentThread().name}")
    launch {
        delay(1000L)
        println("+$i")
    }

    launch {
        delay(800L)
        println(i)
    }
    println(this.coroutineContext)
}
