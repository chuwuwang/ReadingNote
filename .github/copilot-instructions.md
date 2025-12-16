# GitHub Copilot instructions for ReadingNote

Purpose
- This repository is a **personal knowledge & samples archive** (Android, Kotlin, Payments, Rust, Flutter, diagrams, and sample messages), not a single runnable product. Treat edits as documentation and curated examples rather than application code changes.

High-level structure (start here)
- `README.md` — project index and links to major sections
- `Android/`, `Kotlin/`, `Flutter/`, `Java/`, `Python/` — language / platform-specific notes and example projects
- `Architecture/` — system diagrams (.puml) that show big-picture flows (POS, Gateway, Processor, RiskControl)
- `Sample/` — many concrete payment, ISO, and XML samples (useful when working on payments features)
- `Gradle/Gradle Command.txt` — helpful Gradle commands (e.g., `./gradlew signingReport`)

What an AI agent should do first
1. Read `README.md` and `Architecture/*.puml` to understand the repository's scope and common domain terms (POS, Acquirer, Gateway, Base24, EMV, Fiserv samples).  
2. When asked to implement a feature, identify the sub-folder (Android/Kotlin/Flutter/etc.) and treat that subproject independently (open in Android Studio or run its Gradle wrapper if present).  
3. If editing notes, preserve bilingual content (Chinese/English) and original intent; avoid unilateral deletions of historical notes.

Patterns & conventions to follow
- Files are primarily documentation, diagrams, and samples. Prefer non-destructive edits: add new files or append clarifying comments rather than rewriting long notes.  
- Architecture diagrams are PlantUML (`.puml`); update diagrams only when you understand the flow (POS → Gateway → Transaction → Base24 / RiskControl).  
- Payment samples in `Sample/` are authoritative test vectors — do not change them without a clear reason and linked test case.

Build, test, and debug notes
- This is not a single build target; to build a subproject:  
  - Open the relevant folder (e.g., an Android app under `Android/`) in **Android Studio**.  
  - If a Gradle wrapper is present within that subproject, use `./gradlew assembleDebug` or other tasks (see `Gradle/Gradle Command.txt` for examples like `./gradlew signingReport`).
- There is no global CI or workflow in `.github/workflows/` — add workflow files only when you add runnable modules or tests that benefit from automation.

Editing and PR guidance
- Keep changes focused and small. For documentation or note updates, one PR per topic is preferred.  
- Use English for new top-level documentation and add bilingual (Chinese) notes inline if it's a translation or local context.  
- When adding or modifying sample data (e.g., XML, ISO messages), include a short reason and a test or README entry explaining its purpose.

Examples (where to look)
- To understand transaction flows: `Architecture/Processor instruction transaction system.puml` and `PayBy order transaction system.puml`.  
- Payment request/response samples: `Sample/Fiserv Sample/*.xml` and `Sample/isu.txt`.  
- Kotlin idioms and examples: `Kotlin/` (e.g., the `run/apply/let/also/with` note file)

Limitations & safety
- Do not add secrets, keys, or credentials to the repo. If you need to add an example that shows keys, use clearly fake placeholder values and document them.
- If a requested change touches production-ready code in another repo (this repo is a notes collection), ask the maintainer which repo should own the change.

If uncertain, ask these clarifying questions
- Which subproject do you want changed or tested? (Android, Kotlin, Flutter, or just docs/samples?)
- Should new content be English-first or bilingual?  
- Do you want me to add CI/workflows for any runnable modules?

---

Per-subproject build & run (concise)
- Android
  - Open the Android subfolder (e.g., `Android/`) in **Android Studio**. If there is a Gradle wrapper (look for `gradlew`), use it: `./gradlew :<module>:assembleDebug` or `./gradlew signingReport` (see `Gradle/Gradle Command.txt`). Common problems: missing `local.properties` (set `sdk.dir`), mismatched Android SDK versions, or missing keystores.
- Flutter
  - Commands are documented in `Other/flutter.md`. Typical steps: `flutter --version`, `flutter pub get`, then `flutter run` or `flutter build apk`. Use `flutter doctor` to troubleshoot SDK/device issues.
- Kotlin / JVM
  - If a subproject has a `build.gradle`/`settings.gradle`, use the Gradle wrapper: `./gradlew build` or `./gradlew test` inside that submodule.
- Python (runnable sample added)
  - A small testable sample lives in `Sample/minimal-python/`. To run locally: `python -m venv .venv && source .venv/bin/activate && pip install -e .[test] && pytest`.

CI, PR templates & runnable sample
- This repo historically has no global CI. For this repo I added a minimal Python CI workflow that runs tests for `Sample/minimal-python/` and a `PULL_REQUEST_TEMPLATE.md` to standardize reviews.
- If you want CI for Android/Flutter, we can add per-platform workflows (setup Java/Android SDK or Flutter SDK steps) and a small sample Android/Flutter module to verify builds in CI.

How to use Copilot in this repo (short guide)
- For humans using Copilot in the editor: give clear, small prompts and reference the relevant folder, e.g., "Add a README explaining how to run `Sample/minimal-python/` tests". Be explicit about language and PR requirements (English-first, include tests).
- For automated agents: prefer task-focused prompts and include acceptance criteria. Example prompt an AI agent can follow:
  - "Create a minimal Github Actions workflow that runs Python tests in `Sample/minimal-python/` and fails the CI if tests fail. Add a README describing how to run tests locally. Commit as two separate files with concise commit messages."
- Safety & expectations: do not add secrets/keys. For changes to sample payment data, include a short note explaining why the sample was modified and link to a test case verifying the change.

Files to inspect when working
- `README.md` — repo index and high-level pointers
- `Architecture/*.puml` — system diagrams and flow context
- `Sample/` — canonical data samples and the new `Sample/minimal-python/` test sample
- `Gradle/Gradle Command.txt` and `Other/flutter.md` — useful local commands and tips

---

If you'd like, I can now:
- add CI workflows for Android or Flutter builds (requires choosing which submodule to make runnable),
- or add a small Android/Flutter sample to demonstrate full build/test in CI.

Tell me which you'd prefer and I will proceed. — GitHub Copilot (Raptor mini (Preview))
