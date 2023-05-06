package com.rms.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rms.android_util.toDateString
import com.rms.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TaskDetailVM @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    private val _taskDate: MutableStateFlow<String?> = MutableStateFlow(null)
    val taskDate: MutableStateFlow<String?>
        get() = _taskDate

    fun save(txt: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.insert(txt, taskDate.value)
            }
        }
    }

    fun setTime(localDate: LocalDate) {
        _taskDate.value = localDate.toDateString()
    }
}