package pl.poznan.put.oculus.oculuspatientsdbservice.models

import io.swagger.annotations.ApiModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
@ApiModel(description = "details of single patients examination")
data class PatientMetrics (
        @Id
        val id: String?,
        val patient: String,
        val doctor: String,
        val date: Instant,
        val notes: String?,
        val attributes: Map<String, String>
)
