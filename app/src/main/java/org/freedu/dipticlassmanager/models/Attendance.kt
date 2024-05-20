package org.freedu.dipticlassmanager.models

data class Attendance(
    val attendanceId: String,
    val studentId: String,
    val classId: String,
    val date: Long, // Date of attendance
    val isPresent: Boolean // Whether the student is present or not
)
