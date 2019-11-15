package pl.poznan.put.oculus.oculuspatientsdbservice.service

import org.slf4j.LoggerFactory
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

    fun addMetrics(metrics: PatientMetrics) = patientMetricsRepository.insert(metrics)
            .also { logger.info("added metrics ${it.id}") }

    fun updateMetrics(metrics: PatientMetrics) = patientMetricsRepository.save(metrics)
            .also { logger.info("updated metrics ${it.id}") }

    companion object {
        private val logger = LoggerFactory.getLogger(PatientMetricsService::class.java)
    }
}
