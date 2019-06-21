package pl.poznan.put.oculus.oculuspatientsdbservice.rest

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient
import pl.poznan.put.oculus.oculuspatientsdbservice.service.PatientService
import java.net.URI

@RestController
@RequestMapping("patients")
class PatientController (
        internal val service: PatientService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], params = ["id"])
    fun getPatient(@RequestParam id: String): ResponseEntity<Patient> {
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
    fun addPatient(@RequestBody patient: Patient): ResponseEntity<Patient> {
        val created = service.addPatient(patient)
        return ResponseEntity
                .created(URI("/patients?id=${patient.id}"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(created)
    }

}