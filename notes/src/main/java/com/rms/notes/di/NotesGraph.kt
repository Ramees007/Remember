package com.rms.notes.di

import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Scope

@GraphExtension(NotesScope::class)
class NotesGraph {
}

@Scope
annotation class NotesScope