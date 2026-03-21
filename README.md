# Movie Catalog

**Kotlin Multiplatform** + **Compose Multiplatform** app (Android + iOS).

- **Package / applicationId:** `com.moviecatalog`
- **Gradle project name:** `movie-catalog`
- **iOS bundle:** `com.moviecatalog.app` — change in `iosApp/Configuration/Config.xcconfig` if needed

## Requirements

- JDK 17+
- Android Studio / IntelliJ with KMP support
- Xcode (for iOS)

## Android

Using **Make** (same style as `mobile-banking-android`: `make help` lists targets):

```bash
make assembleDebug    # debug APK
make runAppDebug      # assemble + install + open (needs device + adb)
make detekt
make ktlintCheck      # Kotlin style (CI)
make ktlintFormat     # auto-fix style
make lint             # format + check (like Stone `make lint` idea)
make check            # detekt + ktlintCheck + assembleDebug
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
