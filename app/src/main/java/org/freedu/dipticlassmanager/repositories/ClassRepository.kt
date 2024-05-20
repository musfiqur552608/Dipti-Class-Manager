package org.freedu.dipticlassmanager.repositories

import com.google.firebase.database.FirebaseDatabase
import org.freedu.dipticlassmanager.models.Classes

class ClassRepository(private val firebaseDatabase: FirebaseDatabase) {

    // Function to add a new class
    fun addClass(className: String, teacherId: String, callback: (Boolean, String?) -> Unit) {
        val classId = firebaseDatabase.reference.child("classes").push().key ?: ""
        val newClass = org.freedu.dipticlassmanager.models.Classes(
            classId,
            className,
            teacherId,
            emptyList(),
            emptyList()
        )

        firebaseDatabase.reference.child("classes").child(classId)
            .setValue(newClass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to update a class
    fun updateClass(classId: String, updatedClass: Classes, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("classes").child(classId)
            .setValue(updatedClass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to delete a class
    fun deleteClass(classId: String, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("classes").child(classId)
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