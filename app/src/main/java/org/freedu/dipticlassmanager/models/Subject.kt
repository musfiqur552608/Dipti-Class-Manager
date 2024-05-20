package org.freedu.dipticlassmanager.models

data class Subject(
    val subjectId: String,
    val name: String,
    val teacherId: String,
    val classId: String // The class to which this subject belongs
)
