package org.freedu.dipticlassmanager.repositories

import com.google.firebase.database.FirebaseDatabase
import org.freedu.dipticlassmanager.models.Notice

class NoticeRepository(private val firebaseDatabase: FirebaseDatabase) {

    // Function to add a new notice
    fun addNotice(title: String, content: String, createdBy: String, callback: (Boolean, String?) -> Unit) {
        val noticeId = firebaseDatabase.reference.child("notices").push().key ?: ""
        val timestamp = System.currentTimeMillis()
        val newNotice = Notice(noticeId, title, content, createdBy, timestamp)

        firebaseDatabase.reference.child("notices").child(noticeId)
            .setValue(newNotice)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to update a notice
    fun updateNotice(noticeId: String, updatedNotice: Notice, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("notices").child(noticeId)
            .setValue(updatedNotice)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to delete a notice
    fun deleteNotice(noticeId: String, callback: (Boolean, String?) -> Unit) {
        firebaseDatabase.reference.child("notices").child(noticeId)
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