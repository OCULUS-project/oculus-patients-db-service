package pl.poznan.put.oculus.oculuspatientsdbservice.models

import java.time.Instant

class PatientMetricsBuilder private constructor(
    private var metrics: PatientMetrics
){
    companion object {
        fun from(old: PatientMetrics) = PatientMetricsBuilder(old)
    }

    fun withUpdatedAt(newUpdatedAt: Instant? = Instant.now()): PatientMetricsBuilder {
        metrics = PatientMetrics(
                metrics.id,
                metrics.dateOfBirth,
                metrics.weight,
                metrics.height,
                newUpdatedAt,
                metrics.notes
        )
        return this
    }

    fun build() = metrics
}