package pl.poznan.put.oculus.oculuspatientsdbservice.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.hateoas.Link
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pl.poznan.put.oculus.boot.exception.OculusException
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics
import pl.poznan.put.oculus.oculuspatientsdbservice.repository.PatientMetricsRepository

@Service
class PatientMetricsService (
        private val patientMetricsRepository: PatientMetricsRepository,
        private val restTemplate: RestTemplate,
        @Value("\${oculus.facts-service}")
        private val factsServiceHost: String
) {
    fun getMetricsByPatient(patientId: String) = patientMetricsRepository.getAllByPatient(patientId)

    fun getMetricsById(id: String) = patientMetricsRepository.findByIdOrNull(id)

    fun addMetrics(metrics: PatientMetrics) = metrics
            .apply { validateAttributes(attributes) }
            .let { patientMetricsRepository.insert(it) }
            .also { logger.info("added metrics ${it.id}") }

    fun updateMetrics(metrics: PatientMetrics) = metrics
            .apply { validateAttributes(attributes) }
            .let { patientMetricsRepository.save(it) }
            .also { logger.info("updated metrics ${it.id}") }

    private fun validateAttributes(attributes: Map<String, String>) = restTemplate.postForEntity(
            "http://${factsServiceHost}/$ATTRIBUTES_VALIDATION_URL",
            AttributesValidationRequest(attributes),
            AttributesValidationResponse::class.java
    ) .let {
        when (it.statusCode) {
            HttpStatus.OK -> Unit
            HttpStatus.UNPROCESSABLE_ENTITY -> throw InvalidAttributesException(it.body!!._links["self"]?.href ?: "")
            else -> throw Exception("validation unsuccessful")
        }
    }

    companion object {

        private const val ATTRIBUTES_VALIDATION_URL = "/facts/attributes/validate"
        private data class AttributesValidationRequest (val attributes: Map<String, String>)
        private data class AttributesValidationResponse (val _links: Map<String, Link>)
        class InvalidAttributesException (
                private val validation: String
        ) : OculusException("Given attributes don't meet requirements") {
            override val details: Map<String, Any>
                get() = mapOf("validation" to validation)
        }

        private val logger = LoggerFactory.getLogger(PatientMetricsService::class.java)
    }
}
