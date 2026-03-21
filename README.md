# Movie Catalog

**Kotlin Multiplatform** + **Compose Multiplatform** app (Android + iOS).

- **Package / applicationId:** `com.moviecatalog`
- **Gradle project name:** `movie-catalog`
- **iOS bundle:** `com.moviecatalog.app` — change in `iosApp/Configuration/Config.xcconfig` if needed

## Requirements

- JDK 17+
- Android Studio / IntelliJ with KMP support
- Xcode (for iOS)

## CI (GitHub Actions)

Workflow **`.github/workflows/development.yaml`** runs on **push to `main`** and on **pull requests targeting `main`**. Each quality step is a **separate job** so GitHub shows **one status check per job** (easy to require in branch protection):

| Check name on PR | What it runs |
|------------------|--------------|
| **`CI / Detekt`** | `./gradlew detekt` |
| **`CI / KtLint Check`** | `./gradlew ktlintCheck` |
| **`CI / JVM Unit Tests`** | `./gradlew :composeApp:jvmTest` |

**iOS:** **`.github/workflows/build-ios.yml`** → check **`Build iOS app / Xcode build`**.

Runners: Ubuntu + `gradle/actions/setup-gradle` (no Android SDK in CI).

Local equivalent: `make check` (or `make ci`). Use **`make assembleDebug`** when you need a debug APK.

**Branch protection:** Enable **Require status checks to pass**, then add the three **`CI / …`** names above (after at least one run), plus **`Build iOS app / Xcode build`** if you want iOS blocking merges too.

## Android

Using **Make** (`make help` lists targets):

```bash
make assembleDebug    # debug APK
make runAppDebug      # assemble + install + open (needs device + adb)
make detekt
make ktlintCheck      # Kotlin style (CI)
make ktlintFormat     # auto-fix style
make lint             # ktlintFormat + ktlintCheck
make check            # detekt + ktlintCheck + jvmTest (same as CI)
```

Or Gradle directly:

```bash
./gradlew :androidApp:assembleDebug
./gradlew detekt
```

## iOS

Open `iosApp/iosApp.xcodeproj` in Xcode and follow the template’s Compose framework setup.

The shared scheme lives under `iosApp/iosApp.xcodeproj/xcshareddata/xcschemes/` and targets **Movie Catalog.app**.

### Stale build / IDE state

- **Xcode:** if the `.app` under *Products* does not match **Movie Catalog.app**, use *Product → Clean Build Folder* (⇧⌘K) and rebuild. If it persists, delete the project’s folder under *Derived Data* (Xcode → Settings → Locations → Derived Data).
- **Android Studio:** the `iosApp [movie-catalog.iosApp]` label comes from `rootProject.name` in `settings.gradle.kts`. Run *Sync Project with Gradle Files*; if the iOS module name is wrong, *Invalidate Caches / Restart* and check `.idea` for **`movie-catalog.iosApp.iml`** only (no duplicate `.iosApp.iml`).

The current sample (Met museum list + Koin + Navigation Compose) will be replaced by the **movie catalog** flow (HTTP mock `movie-catalog`, Voyager, Kodein, etc.).
