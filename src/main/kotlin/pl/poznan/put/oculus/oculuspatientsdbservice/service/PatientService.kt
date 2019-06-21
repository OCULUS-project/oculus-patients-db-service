package pl.poznan.put.oculus.oculuspatientsdbservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient
import pl.poznan.put.oculus.oculuspatientsdbservice.repository.PatientRepository

@Service
class PatientService (
    private val patientRepository: PatientRepository
) {
    fun getPatient(id: String) = patientRepository.findByIdOrNull(id)

    fun addPatient(patient: Patient) = patientRepository.insert(patient)
}