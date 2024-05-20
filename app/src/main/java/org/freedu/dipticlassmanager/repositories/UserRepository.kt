package org.freedu.dipticlassmanager.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.freedu.dipticlassmanager.models.User

class UserRepository(private val firebaseAuth: FirebaseAuth, private val firebaseDatabase: FirebaseDatabase) {

    // Function to register a new user
    fun registerUser(email: String, password: String, name: String, role: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid ?: ""
                    val user = User(userId, name, email, role)
                    saveUserToDatabase(userId, user)
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to save user details to the database
    private fun saveUserToDatabase(userId: String, user: User) {
        firebaseDatabase.reference.child("users").child(userId).setValue(user)
    }

    // Function to log in user
    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to log out user
    fun logoutUser(callback: () -> Unit) {
        firebaseAuth.signOut()
        callback()
    }

    // Function to update user profile
    fun updateUserProfile(userId: String, name: String, callback: (Boolean, String?) -> Unit) {
        val userUpdates = hashMapOf<String, Any>(
            "name" to name
            // Add other fields you want to update
        )

        firebaseDatabase.reference.child("users").child(userId)
            .updateChildren(userUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    // Function to delete user account
    fun deleteUserAccount(callback: (Boolean, String?) -> Unit) {
        val user = firebaseAuth.currentUser
        user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }
}