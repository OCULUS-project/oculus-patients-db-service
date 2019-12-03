package pl.poznan.put.oculus.oculuspatientsdbservice.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient
import pl.poznan.put.oculus.oculuspatientsdbservice.repository.PatientRepository

@Service
class PatientService (
    private val patientRepository: PatientRepository
) {
    fun getPatient(id: String) = patientRepository.findByIdOrNull(id)

    fun getAllPatients(n: Int, size: Int): Page<Patient> = patientRepository.findAll(PageRequest.of(n, size))

    fun addPatient(patient: Patient): Patient = patientRepository.insert(patient)
}