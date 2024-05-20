package org.freedu.dipticlassmanager.viewmodels

import androidx.lifecycle.ViewModel
import org.freedu.dipticlassmanager.models.Subject
import org.freedu.dipticlassmanager.repositories.SubjectRepository

class SubjectViewModel(private val subjectRepository: SubjectRepository) : ViewModel() {

    fun addSubject(subjectName: String, teacherId: String, classId: String, callback: (Boolean, String?) -> Unit) {
        subjectRepository.addSubject(subjectName, teacherId, classId, callback)
    }

    fun updateSubject(subjectId: String, updatedSubject: Subject, callback: (Boolean, String?) -> Unit) {
        subjectRepository.updateSubject(subjectId, updatedSubject, callback)
    }

    fun deleteSubject(subjectId: String, callback: (Boolean, String?) -> Unit) {
        subjectRepository.deleteSubject(subjectId, callback)
    }
}