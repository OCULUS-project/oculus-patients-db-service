package pl.poznan.put.oculus.oculuspatientsdbservice.repository

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.updateFirst
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient

class PatientRepositoryCustomImpl (
        private val mongoTemplate: MongoTemplate
) : PatientRepositoryCustom {

}