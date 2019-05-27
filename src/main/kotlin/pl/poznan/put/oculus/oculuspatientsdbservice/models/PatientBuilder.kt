package pl.poznan.put.oculus.oculuspatientsdbservice.models

class PatientBuilder private constructor(
        private var patient: Patient
){
    companion object {
        fun from(old: Patient) = PatientBuilder(old)
    }

    fun withMetrics(newMetrics: String? = null): PatientBuilder {
        patient = Patient(
                patient.id,
                patient.firstName,
                patient.lastName,
                patient.pesel,
                patient.email,
                patient.phone,
                patient.password,
                newMetrics
            )
        return this
    }

    fun build() = patient
}