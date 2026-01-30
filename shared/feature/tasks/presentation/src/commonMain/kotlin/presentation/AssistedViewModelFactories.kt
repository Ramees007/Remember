package presentation

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

fun taskDetailsViewModelFactory(
    vmCreator: () -> TaskDetailVM
): ViewModelProvider.Factory = viewModelFactory {
    initializer<TaskDetailVM> {
        vmCreator()
    }
}