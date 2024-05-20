package org.freedu.dipticlassmanager.models

data class User(
    val userId: String,
    val name: String,
    val email: String,
    val role: String // "student" or "teacher"
)
