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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.oculus.boot.config.PublicAPI
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient
import pl.poznan.put.oculus.oculuspatientsdbservice.service.PatientService
import java.net.URI

@RestController
@RequestMapping("patients")
@PublicAPI
@Api(value = "manage patients", description = "create and retrieve patients")
class PatientController (
        private val service: PatientService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], params = ["id"])
    @ApiOperation(value = "retrieve patient by his id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "patient exists"),
        ApiResponse(code = 204, message = "patient does not exist", response = Unit::class)
    ])
    fun getPatient(
            @RequestParam @ApiParam(value = "id of patient to retrieve", required = true) id: String
    ): ResponseEntity<Patient> {
        val patient = service.getPatient(id)
        return if (patient != null) ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patient)
        else ResponseEntity
                .noContent()
                .build()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "create new patient")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "patient created")
    ])
    fun addPatient(
            @RequestBody @ApiParam(value = "desired patient data", required = true) patient: Patient
    ): ResponseEntity<Patient> {
        val created = service.addPatient(patient)
        return ResponseEntity
                .created(URI("/patients?id=${patient.id}"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(created)
    }

}