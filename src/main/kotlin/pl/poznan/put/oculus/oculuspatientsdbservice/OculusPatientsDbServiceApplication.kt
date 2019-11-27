package pl.poznan.put.oculus.oculuspatientsdbservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("pl.poznan.put.oculus.boot", "")
class OculusPatientsDbServiceApplication

fun main(args: Array<String>) {
    runApplication<OculusPatientsDbServiceApplication>(*args)
}
