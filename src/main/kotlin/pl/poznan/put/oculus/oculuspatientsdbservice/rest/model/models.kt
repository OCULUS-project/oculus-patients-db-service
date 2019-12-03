package pl.poznan.put.oculus.oculuspatientsdbservice.rest.model

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import pl.poznan.put.oculus.oculuspatientsdbservice.models.Patient
import pl.poznan.put.oculus.oculuspatientsdbservice.models.PatientMetrics
import pl.poznan.put.oculus.oculuspatientsdbservice.rest.PatientController
import pl.poznan.put.oculus.oculuspatientsdbservice.rest.PatientMetricsController

class PatientModel (content: Patient) : EntityModel<Patient>(content)

fun Patient.toModel() = PatientModel(this).apply {
    add(linkTo(methodOn(PatientController::class.java).getPatient(id!!)).withSelfRel())
    add(linkTo(methodOn(PatientMetricsController::class.java).getMetricsByPatient(id)).withRel("patientMetrics"))
    add(linkTo(methodOn(PatientController::class.java).getAllPatients(1, 10)).withRel(IanaLinkRelations.COLLECTION  ))
}

class PatientMetricsModel(content: PatientMetrics) : EntityModel<PatientMetrics>(content)

fun PatientMetrics.toModel() = PatientMetricsModel(this).apply {
    add(linkTo(methodOn(PatientMetricsController::class.java).getMetricsById(id!!)).withSelfRel())
    add(linkTo(methodOn(PatientMetricsController::class.java).getMetricsByPatient(patient)).withRel("patientMetrics"))
    add(linkTo(methodOn(PatientMetricsController::class.java).updateMetrics(content!!)).withRel(IanaLinkRelations.EDIT))
}
