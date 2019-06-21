package pl.poznan.put.oculus.oculuspatientsdbservice.models

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
@ApiModel(description = "details of patient")
data class Patient (
        @Id
        val id: String?,
        val firstName: String,
        val lastName: String,
        val pesel: Long,
        val email: String,
        val phone: String,
        @ApiModelProperty(notes = "password for remote access to inference results")
        val password: String?
)