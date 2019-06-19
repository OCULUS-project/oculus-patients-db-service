package pl.poznan.put.oculus.oculuspatientsdbservice.models

import org.springframework.data.annotation.Id
import java.time.Instant

data class PatientMetrics (
        @Id
        val id: String?,
        val patient: String,
        val date: Instant,
        val notes: String?,
        val weight: Int?,
        val height: Int?
)
