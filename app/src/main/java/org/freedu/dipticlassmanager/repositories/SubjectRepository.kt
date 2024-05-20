package org.freedu.dipticlassmanager.repositories

import com.google.firebase.database.FirebaseDatabase
import org.freedu.dipticlassmanager.models.Subject

class SubjectRepository(private val firebaseDatabase: FirebaseDatabase) {

    // Function to add a new subject
    fun addSubject(subjectName: String, teacherId: String, classId: String, callback: (Boolean, String?) -> Unit) {
        val subjectId = firebaseDatabase.reference.child("subjects").push().key ?: ""
        val newSubject = Subject(subjectId, subjectName, teacherId, classId)

        firebaseDatabase.reference.child("subjects").child(subjectId)
            .setValue(newSubject)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to update a subject
    fun updateSubject(subjectId: String, updatedSubject: Subject, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("subjects").child(subjectId)
            .setValue(updatedSubject)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to delete a subject
    fun deleteSubject(subjectId: String, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("subjects").child(subjectId)
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