package pl.poznan.put.oculus.oculuspatientsdbservice.rest

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.oculus.oculuspatientsdbservice.service.PatientDbServiceApp
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics

@RestController
class PatientMetricsController (
        val app: PatientDbServiceApp
) {
    @GetMapping("metrics", produces = [MediaType.APPLICATION_JSON_VALUE], params = ["patientId"])
    fun getMetrics(@RequestParam patientId: String) = ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(app.getPatientMetrics(patientId))

    @PostMapping("metrics/{patientId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addMetrics(@PathVariable patientId: String, @RequestBody metrics: PatientMetrics) = ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(app.addMetricsForPatient(patientId, metrics))
}