package pl.poznan.put.oculus.oculuspatientsdbservice.rest

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.poznan.put.oculus.oculuspatientsdbservice.app.PatientDbServiceApp
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient

@RestController
class PatientController (
        private val app: PatientDbServiceApp
) {

    @GetMapping("patient", produces = [MediaType.APPLICATION_JSON_VALUE], params = ["id"])
    fun getPatient(@RequestParam id: String): ResponseEntity<Patient> {
        val patient = app.getPatient(id)
        return if (patient != null) ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patient)
        else ResponseEntity
                .noContent()
                .build()
    }

    @PostMapping("patient", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addPatient(@RequestBody patient: Patient): ResponseEntity<Patient> = ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(app.addPatient(patient))

}