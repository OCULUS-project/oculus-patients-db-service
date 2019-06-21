package pl.poznan.put.oculus.oculuspatientsdbservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics
import pl.poznan.put.oculus.oculuspatientsdbservice.repository.PatientMetricsRepository

@Service
class PatientMetricsService (
        private val patientMetricsRepository: PatientMetricsRepository
) {
    fun getMetricsByPatient(patientId: String) = patientMetricsRepository.getAllByPatient(patientId)

    fun getMetricsById(id: String) = patientMetricsRepository.findByIdOrNull(id)

    fun addOrUpdateMetrics(metrics: PatientMetrics) = patientMetricsRepository.save(metrics)
}