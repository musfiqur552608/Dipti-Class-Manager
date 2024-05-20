package org.freedu.dipticlassmanager.viewmodels

import androidx.lifecycle.ViewModel
import org.freedu.dipticlassmanager.models.Attendance
import org.freedu.dipticlassmanager.repositories.AttendanceRepository

class AttendanceViewModel(private val attendanceRepository: AttendanceRepository) : ViewModel() {

    fun markAttendance(studentId: String, classId: String, isPresent: Boolean, callback: (Boolean, String?) -> Unit) {
        attendanceRepository.markAttendance(studentId, classId, isPresent, callback)
    }

    fun updateAttendance(attendanceId: String, updatedAttendance: Attendance, callback: (Boolean, String?) -> Unit) {
        attendanceRepository.updateAttendance(attendanceId, updatedAttendance, callback)
    }

    fun deleteAttendance(attendanceId: String, classId: String, callback: (Boolean, String?) -> Unit) {
        attendanceRepository.deleteAttendance(attendanceId, classId, callback)
    }
}