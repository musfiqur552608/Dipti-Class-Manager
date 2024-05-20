package org.freedu.dipticlassmanager.repositories

import com.google.firebase.database.FirebaseDatabase
import org.freedu.dipticlassmanager.models.Attendance

class AttendanceRepository(private val firebaseDatabase: FirebaseDatabase) {

    // Function to mark attendance for a student
    fun markAttendance(studentId: String, classId: String, isPresent: Boolean, callback: (Boolean, String?) -> Unit) {
        val attendanceId = firebaseDatabase.reference.child("attendance").child(classId).push().key ?: ""
        val timestamp = System.currentTimeMillis()
        val attendance = Attendance(attendanceId, studentId, classId, timestamp, isPresent)

        firebaseDatabase.reference.child("attendance").child(classId).child(attendanceId)
            .setValue(attendance)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to update attendance record
    fun updateAttendance(attendanceId: String, updatedAttendance: Attendance, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("attendance").child(updatedAttendance.classId).child(attendanceId)
            .setValue(updatedAttendance)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to delete attendance record
    fun deleteAttendance(attendanceId: String, classId: String, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("attendance").child(classId).child(attendanceId)
            .removeValue()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }
}