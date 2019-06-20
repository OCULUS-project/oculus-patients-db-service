package pl.poznan.put.oculus.oculuspatientsdbservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientBuilder
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetricsBuilder
import pl.poznan.put.oculus.oculuspatientsdbservice.repository.PatientMetricsRepository
import pl.poznan.put.oculus.oculuspatientsdbservice.repository.PatientRepository

@Service
class PatientDbServiceApp (
        private val patientRepository: PatientRepository,
        private val patientMetricsRepository: PatientMetricsRepository
) {
    fun getPatient(id: String) = patientRepository.findByIdOrNull(id)

    fun addPatient(patient: Patient) = patientRepository.insert(patient)

    fun getPatientMetrics(patientId: String): PatientMetrics {
        val patient = getPatient(patientId)!!
        return getPatientMetrics(patient)
    }

    private fun getPatientMetrics(patient: Patient): PatientMetrics {
        return if (patient.metrics != null) getMetrics(patient.metrics)!!
        else PatientMetrics()
    }

    private fun getMetrics(id: String) = patientMetricsRepository.findByIdOrNull(id)

    fun addMetricsForPatient(patientId: String, newMetrics: PatientMetrics): PatientMetrics {
        val metrics = PatientMetricsBuilder.from(newMetrics)
                .withUpdatedAt()
                .build()

        val inserted = patientMetricsRepository.insert(metrics)
        addOrUpdateMetrics(patientId, inserted.id!!)
        return inserted
    }

    private fun addOrUpdateMetrics(patientId: String, metricsId: String) {
        val patient = patientRepository.findByIdOrNull(patientId) ?: throw Exception("no patient with given id")
        if (patient.metrics != metricsId) {
            if (patient.metrics != null) patientMetricsRepository.deleteById(patient.metrics)
            val updated = PatientBuilder.from(patient)
                    .withMetrics(metricsId)
                    .build()
            patientRepository.save(updated)
        }
    }
}