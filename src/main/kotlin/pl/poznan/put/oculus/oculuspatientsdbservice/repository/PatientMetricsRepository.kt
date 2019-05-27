package pl.poznan.put.oculus.oculuspatientsdbservice.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics

interface PatientMetricsRepository : MongoRepository<PatientMetrics, String>