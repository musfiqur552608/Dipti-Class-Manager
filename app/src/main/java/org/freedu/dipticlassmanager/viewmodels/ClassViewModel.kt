package org.freedu.dipticlassmanager.viewmodels

import androidx.lifecycle.ViewModel
import org.freedu.dipticlassmanager.models.Classes
import org.freedu.dipticlassmanager.repositories.ClassRepository

class ClassViewModel(private val classRepository: ClassRepository) : ViewModel() {

    fun addClass(className: String, teacherId: String, callback: (Boolean, String?) -> Unit) {
        classRepository.addClass(className, teacherId, callback)
    }

    fun updateClass(classId: String, updatedClass: Classes, callback: (Boolean, String?) -> Unit) {
        classRepository.updateClass(classId, updatedClass, callback)
    }

    fun deleteClass(classId: String, callback: (Boolean, String?) -> Unit) {
        classRepository.deleteClass(classId, callback)
    }
}