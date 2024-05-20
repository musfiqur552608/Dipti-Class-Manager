package org.freedu.dipticlassmanager.models

data class Notice(
    val noticeId: String,
    val title: String,
    val content: String,
    val createdBy: String, // Teacher ID who created the notice
    val timestamp: Long // Timestamp of notice creation
)
