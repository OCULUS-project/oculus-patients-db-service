package pl.poznan.put.oculus.oculuspatientsdbservice.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Patient (
        @Id
        val id: String?,
        val firstName: String,
        val lastName: String,
        val pesel: Long,
        val email: String,
        val phone: String,
        val password: String?,
        val metrics: String?
)