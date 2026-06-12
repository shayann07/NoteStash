# NoteStash

A small Kotlin Android note-taking sample: create notes (title + body), list them in a 2-column staggered Material grid, and delete with a Snackbar "Undo" action. Persistence is via **Room**, and the screen is wired in classic **MVVM** (Activity → `ViewModel` → `Repository` → `Dao`) with `LiveData` and View Binding.

> **Heads-up:** the previous README claimed Java, full editing, to-dos, reminders, undo/redo, search, sorting, and an MIT license. The real code is Kotlin and only supports create / list / delete. See [Honest limitations](#honest-limitations).

## Status

- Working tree clean on `master` (last commit `abf67ca`, recent history is dominated by GitPulse marker commits).
- Remote: `https://github.com/shayann07/NoteStash.git`.
- This README was rewritten from a code audit; the previous one's feature list did not match the source.

## How it works

- **`MainActivity`** inflates `activity_main.xml` (`CoordinatorLayout` + `MaterialToolbar` + `RecyclerView` in a 2-column staggered grid + `ExtendedFloatingActionButton`). It instantiates `NoteViewModel` via `NoteVMFactory(application)` and observes `viewModel.notes` (LiveData) to drive `NoteAdapter`.
- The FAB launches **`AddNoteActivity`**, which collects a title and body in two `TextInputEditText` fields and calls `viewModel.add(title, body)` on save.
- Each card has a delete button. The adapter's delete callback calls `viewModel.delete(note)` and shows a Snackbar with an **Undo** action that re-inserts a copy via `viewModel.add(note.title, note.body)` — so "Undo" creates a brand-new row with a new auto-generated id, not the original.
- **`NoteRepository`** wraps the DAO: it exposes `notes: LiveData<List<Note>>` from `NoteDao.getAll()` and forwards `insert` / `delete`.
- **`NoteDatabase`** is a singleton Room database with one entity (`Note`) and one DAO (`NoteDao`).
- **`Note`** has only `id` (auto-generated PK), `title`, `body` — no timestamp, no completed flag, no reminder time.
- **`NoteDao`** declares exactly three methods: `getAll()` (`ORDER BY id DESC`), `insert` (`OnConflictStrategy.REPLACE`), and `delete`. There is no `@Update`, no search query, no sort variant.

## Tech stack

- **Language / build:** Kotlin 2.0.21, AGP via `libs.versions.toml`, JVM 11, `kotlin-kapt` (for Room).
- **App config:** `applicationId = com.example.note_takingapp`, `compileSdk = 35`, View Binding enabled.
- **Dependencies:** `androidx.core.ktx`, `appcompat`, `material`, `recyclerview` 1.4.0, `androidx.lifecycle.viewmodel.ktx`, `androidx.lifecycle.livedata.ktx` (2.8.0), Room (`runtime`, `compiler`, `ktx`) 2.7.0, plus default JUnit / AndroidX-JUnit / Espresso.
- **Permissions:** none.

The repo does **not** use Hilt/Dagger, Compose, Navigation Component, WorkManager, Data Binding, Retrofit, or any networking library.

## Project layout

```
NoteStash/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml                       # MainActivity (launcher) + AddNoteActivity
│       ├── java/com/example/note_takingapp/
│       │   ├── MainActivity.kt
│       │   ├── data/
│       │   │   ├── Note.kt                           # Room entity: id, title, body
│       │   │   ├── NoteDao.kt                        # getAll / insert / delete only
│       │   │   ├── NoteDatabase.kt                   # Room singleton
│       │   │   └── NoteRepository.kt
│       │   └── ui/
│       │       ├── AddNoteActivity.kt
│       │       ├── NoteVMFactory.kt
│       │       ├── NoteViewModel.kt
│       │       └── adapter/NoteAdapter.kt
│       └── res/layout/
│           ├── activity_main.xml
│           ├── activity_add_note.xml
│           └── item_note.xml
├── build.gradle.kts
└── gradle/libs.versions.toml
```

## Setup / run

1. Clone, open in Android Studio, let Gradle sync.
2. `local.properties` is currently committed in this repo with a hardcoded SDK path — replace its `sdk.dir` line with your own Android SDK location, or delete the file and let Gradle / the IDE regenerate it.
3. `./gradlew :app:assembleDebug` (or run from the IDE).

## Honest limitations

- **No edit.** `NoteDao` has no `@Update` and `AddNoteActivity` does not accept a note id; tapping a card does not open it. Only create / delete.
- **"Undo" is a re-insert.** The Snackbar undo calls `viewModel.add(note.title, note.body)`, which creates a fresh row with a new id. There is no real undo/redo stack.
- **No to-dos, reminders, search, or sorting.** The previous README listed all of these; none of them have any code or DAO methods backing them. Notes are sorted by descending `id` only — there is no user-selectable sort.
- **`local.properties` is committed** with a hardcoded `sdk.dir = C:\Users\shaya\AppData\Local\Android\Sdk`. It should be `.gitignore`d.
- **No root `.gitignore`.** Build artefacts (`.gradle/`, `.idea/`) and ~780 tracked files indicate the standard Android `.gitignore` was never added.
- **`applicationId = com.example.note_takingapp`** — generic placeholder.
- **No `LICENSE` file**, despite the previous README claiming MIT and linking to one.
- **GitPulse marker leftovers.** The previous `README.md` ended with `<!-- gitpulse:contribution … -->` lines from a contribution-marker tool. Removed in this rewrite.
- **No tests** beyond the default `ExampleUnitTest` / `ExampleInstrumentedTest`.
