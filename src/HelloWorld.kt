class HelloWorld {
    fun run() {
        val person = Person("John Doe", 30)

        val info = with(person) {
            println("Name: $name, Age: $age")
            "Name: $name, Age: $age"
        }

        println("Person Info: $info")
    }
}