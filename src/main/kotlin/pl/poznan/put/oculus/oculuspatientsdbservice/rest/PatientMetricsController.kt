package pl.poznan.put.oculus.oculuspatientsdbservice.rest

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.oculus.oculuspatientsdbservice.config.PublicAPI
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics
import pl.poznan.put.oculus.oculuspatientsdbservice.service.PatientMetricsService

@RestController
@RequestMapping("patients/metrics")
@PublicAPI
@Api(value = "manage patient metrics", description = "create and retrieve patient metrics")
class PatientMetricsController (
        private val service: PatientMetricsService
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], params = ["patientId"])
    @ApiOperation(value = "retrieve patient metrics by patient")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "patient metrics exists"),
        ApiResponse(code = 204, message = "patient metrics does not exist", response = Unit::class)
    ])
    fun getMetricsByPatient(
            @RequestParam @ApiParam(value = "id of patient to get metrics for", required = true) patientId: String
    ): ResponseEntity<List<PatientMetrics>> {
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
    @ApiOperation(value = "retrieve patient metrics by its id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "patient metrics exists"),
        ApiResponse(code = 204, message = "patient metrics does not exist", response = Unit::class)
    ])
    fun getMetricsById(
            @RequestParam @ApiParam(value = "id of patient metrics to retrieve", required = true) id: String
    ): ResponseEntity<PatientMetrics> {
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
    @ApiOperation(value = "create new patient metrics")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "patient metrics created")
    ])
    fun addMetrics(
            @RequestBody @ApiParam(value = "desired patient metrics data", required = true) metrics: PatientMetrics
    ) = ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.addOrUpdateMetrics(metrics))

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "update patient metrics")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "patient metrics updated")
    ])
    fun updateMetrics(
            @RequestBody @ApiParam(value = "updated patient metrics", required = true) metrics: PatientMetrics
    ) = ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.addOrUpdateMetrics(metrics))
}