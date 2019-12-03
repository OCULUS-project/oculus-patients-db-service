package pl.poznan.put.oculus.oculuspatientsdbservice.rest.model

import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import pl.poznan.put.oculus.oculuspatientsdbservice.rest.PatientController

data class PatientsPageResponse (
        val n: Int,
        val size: Int,
        val patients: List<PatientModel>,
        val numberOfPages: Int,
        val numberOfPatients: Long
) : RepresentationModel<PatientsPageResponse>() {
    init {
        add(linkTo(methodOn(PatientController::class.java).getAllPatients(n, size)).withSelfRel())
        add(linkTo(methodOn(PatientController::class.java).getAllPatients(n+1, size)).withRel(IanaLinkRelations.NEXT))
    }
}
