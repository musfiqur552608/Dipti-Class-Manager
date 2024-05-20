package org.freedu.dipticlassmanager.models

data class Classes(
    val classId: String,
    val name: String,
    val teacherId: String,
    val students: List<String>, // List of student IDs
    val subjects: List<String> // List of subject IDs

)
