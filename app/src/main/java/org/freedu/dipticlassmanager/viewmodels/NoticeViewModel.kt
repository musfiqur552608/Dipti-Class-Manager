package org.freedu.dipticlassmanager.viewmodels

import androidx.lifecycle.ViewModel
import org.freedu.dipticlassmanager.models.Notice
import org.freedu.dipticlassmanager.repositories.NoticeRepository

class NoticeViewModel(private val noticeRepository: NoticeRepository) : ViewModel() {

    fun addNotice(title: String, content: String, createdBy: String, callback: (Boolean, String?) -> Unit) {
        noticeRepository.addNotice(title, content, createdBy, callback)
    }

    fun updateNotice(noticeId: String, updatedNotice: Notice, callback: (Boolean, String?) -> Unit) {
        noticeRepository.updateNotice(noticeId, updatedNotice, callback)
    }

    fun deleteNotice(noticeId: String, callback: (Boolean, String?) -> Unit) {
        noticeRepository.deleteNotice(noticeId, callback)
    }
}