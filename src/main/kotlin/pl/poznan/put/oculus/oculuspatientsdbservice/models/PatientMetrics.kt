package pl.poznan.put.oculus.oculuspatientsdbservice.models

import org.springframework.data.annotation.Id
import java.time.Instant

data class PatientMetrics (
        @Id
        val id: String?,
        val dateOfBirth: Instant?,
        val weight: Int?,
        val height: Int?,
        val updatedAt: Instant?,
        val notes: String?
) {
        constructor() : this(null, null, null, null, null, null)
}