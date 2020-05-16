package com.dabba.dabbarest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DabbaRestApplication

fun main(args: Array<String>) {
	runApplication<DabbaRestApplication>(*args)
}
