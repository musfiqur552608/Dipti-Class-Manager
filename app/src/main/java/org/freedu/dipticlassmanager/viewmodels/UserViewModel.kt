package org.freedu.dipticlassmanager.viewmodels

import androidx.lifecycle.ViewModel
import org.freedu.dipticlassmanager.repositories.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun registerUser(email: String, password: String, name: String, role: String, callback: (Boolean, String?) -> Unit) {
        userRepository.registerUser(email, password, name, role, callback)
    }

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        userRepository.loginUser(email, password, callback)
    }

    fun logoutUser(callback: () -> Unit) {
        userRepository.logoutUser(callback)
    }

    fun updateUserProfile(userId: String, name: String, callback: (Boolean, String?) -> Unit) {
        userRepository.updateUserProfile(userId, name, callback)
    }

    fun deleteUserAccount(callback: (Boolean, String?) -> Unit) {
        userRepository.deleteUserAccount(callback)
    }
}