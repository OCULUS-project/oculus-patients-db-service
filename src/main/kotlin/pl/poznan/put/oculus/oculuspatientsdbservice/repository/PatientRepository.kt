package pl.poznan.put.oculus.oculuspatientsdbservice.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient

interface PatientRepository : MongoRepository<Patient, String>