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
}