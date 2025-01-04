fun main(args: Array<String>) {
    val limit = 100 // Change this to your desired limit
    val primes = mutableListOf<Int>()
    val isPrime = BooleanArray(limit + 1) { true }

    for (p in 2..limit) {
        if (isPrime[p]) {
            primes.add(p)
            for (i in p * p..limit step p) {
                isPrime[i] = false
            }
        }
    }

    println("Prime numbers up to $limit are:")
    println(primes)
    val hw: HelloWorld = HelloWorld()
    hw.run()

    val p1 = Person("John Doe", 30)
    val p2 = Person("John Doe", 30)

    val jc = JClass()
    jc.print(p1, p2)
}