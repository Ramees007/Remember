package com.rms.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rms.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TaskDetailVM @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    private var taskDate: LocalDate? = null

    fun save(txt: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.insert(txt, taskDate)
            }
        }
    }

    fun setTime(localDate: LocalDate) {
        taskDate = localDate
    }
}