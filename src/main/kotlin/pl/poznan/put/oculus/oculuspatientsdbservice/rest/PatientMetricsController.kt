package pl.poznan.put.oculus.oculuspatientsdbservice.rest

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics
import pl.poznan.put.oculus.oculuspatientsdbservice.service.PatientMetricsService

@RestController
@RequestMapping("patients/metrics")
class PatientMetricsController (
        private val service: PatientMetricsService
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], params = ["patientId"])
    fun getMetricsByPatient(@RequestParam patientId: String): ResponseEntity<List<PatientMetrics>> {
        val metrics = service.getMetricsByPatient(patientId)
        return if (metrics.isNotEmpty()) ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(metrics)
        else ResponseEntity
            .noContent()
            .build()
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], params = ["id"])
    fun getMetricsById(@RequestParam id: String): ResponseEntity<PatientMetrics> {
        val metrics = service.getMetricsById(id)
        return if (metrics != null) ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(metrics)
        else ResponseEntity
                .noContent()
                .build()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addMetrics(@RequestBody metrics: PatientMetrics) = ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.addOrUpdateMetrics(metrics))

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateMetrics(@RequestBody metrics: PatientMetrics) = ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.addOrUpdateMetrics(metrics))
}