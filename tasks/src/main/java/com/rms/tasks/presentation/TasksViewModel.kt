package com.rms.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rms.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    val flow: StateFlow<TasksUiState> = taskRepository.getAllTasks().map {
        if (it.isEmpty()) TasksUiState.Empty
        else TasksUiState.Tasks(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TasksUiState.Loading
    )

    var taskTimeStamp: Long? = null
        private set

    fun onCheckedChanged(id: Long, isChecked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.update(id, isChecked)
            }
        }
    }

    fun save(txt: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.insert(txt, taskTimeStamp)
            }
        }
    }

    fun setTime(localDate: LocalDate) {
//        val localNow: LocalDateTime = localDate
//// setting UTC as the timezone
//// setting UTC as the timezone
//        val zonedUTC: ZonedDateTime = localNow.atZone(ZoneId.of("UTC"))
//// converting to IST
//// converting to IST
//        val zonedIST: ZonedDateTime = zonedUTC.withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
//        localDate
       // taskTimeStamp = timeStamp
    }

}