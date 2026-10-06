# Android Product Flavors Showcase

Three coffee loyalty apps. One Android codebase. Zero duplicated logic.

Companies running more than one branded app usually start with the best intentions and end up with three repos that quietly drift apart. A fix lands in app A on Tuesday. Nobody remembers to port it to app B. Three months later a customer on app C reports a bug that was already patched everywhere else. This repo is a working answer to that problem, built with Gradle's product flavors instead of a fork-and-maintain setup.

## The idea

```
        Shared Android Codebase
                 │
         ┌───────┼───────┐
         │       │       │
      Brewline CafeNova RoastHouse
         │       │       │
       App A    App B    App C
```

All three apps are the same `HomeScreen`, the same `BrandConfig`, the same business logic, compiled three times with different settings. Brewline, CafeNova, and RoastHouse are invented brand names built for this demo. They don't belong to any real coffee company, and the app IDs, API URLs, and icons here are all placeholders.

Each flavor only touches four things:

- the application ID (so all three can sit on the same phone as separate apps)
- the API base URL it points to
- whether the loyalty rewards feature is turned on
- app name, launcher icon, and brand color

Everything else, the screen, the navigation, the `BrandConfig` object that reads build settings into a typed model, lives once in `main/` and gets compiled into all three apps unchanged.

## Why product flavors instead of three repos

Maintaining near-identical Android apps as separate codebases means every bug fix, every feature, and every dependency bump gets done three times, and someone eventually forgets one of them. Gradle product flavors push that problem down to the build system: you write the app once, and the flavor dimension decides which app ID, which icon, which feature flags, and which backend each build gets. Same APK-building pipeline, three outputs.

The part worth calling out is the discipline this forces on the shared code. Nothing under `main/` is allowed to ask "which brand am I?" There's no `if (BuildConfig.FLAVOR == "brewline")` anywhere in this project. `HomeScreen.kt` and `BrandConfig.kt` only read configuration values (a URL, a boolean, a color resource). The flavor folders configure the app. They don't fork its logic. That line matters more than the three icons do.

## Architecture

```
android-product-flavors-showcase/
├── app/
│   ├── build.gradle.kts                  # flavorDimensions + productFlavors block
│   └── src/
│       ├── main/                          # shared Compose screen, BrandConfig, resources
│       │   ├── java/.../BrandConfig.kt    # typed wrapper around BuildConfig fields
│       │   ├── java/.../HomeScreen.kt     # the one screen every flavor ships
│       │   └── res/                       # shared strings, theme, no brand-specific assets
│       ├── brewline/
│       │   └── res/ (launcher icon, colors.xml, strings.xml app_name)
│       ├── cafenova/
│       │   └── res/...
│       └── roasthouse/
│           └── res/...
├── .github/workflows/build-all-flavors.yml   # matrix build: brewline, cafenova, roasthouse
└── README.md
```

### What each flavor actually sets

| Flavor | Application ID suffix | API base URL | Loyalty feature | Launcher mark |
|---|---|---|---|---|
| Brewline | `.brewline` | `api.brewline.example.com` | on | amber ring |
| CafeNova | `.cafenova` | `api.cafenova.example.com` | on | blue diamond |
| RoastHouse | `.roasthouse` | `api.roasthouse.example.com` | **off** | brown square |

RoastHouse ships with `FEATURE_LOYALTY_ENABLED = false` on purpose. It's there to prove the flag actually changes what the app does (the loyalty card never renders) and not just how it looks.

## Demo

[Screenshot: three installed apps side by side on one device or emulator, different icons, different names, different colors]

Because each flavor gets its own `applicationId`, Android treats them as three unrelated apps. All three install side by side on the same device without conflicting.

## Metric

`main/` holds 8 files and 181 lines. The three flavor folders combined hold 18 files and 140 lines, almost all of it tiny XML (a color value, an app name string, a launcher icon drawable).

**56% of the total code lives in `main/`, shared across all three apps**, and the number understates it: the flavor-side lines are configuration, not logic. Every behavioral line of code in this project is written once.

## CI: one push builds all three

`.github/workflows/build-all-flavors.yml` runs a GitHub Actions build matrix over `[Brewline, Cafenova, Roasthouse]`. Each matrix job checks out the repo, sets up JDK 17, and runs `./gradlew assemble<Flavor>Debug`, so a single push produces three APKs in one CI run instead of three separate pipelines.

## Stack

Kotlin, Jetpack Compose, Gradle product flavors, GitHub Actions matrix builds, AGP 8.4, compileSdk 34.

## Run it

```bash
./gradlew assembleBrewlineDebug
./gradlew assembleCafenovaDebug
./gradlew assembleRoasthouseDebug
```

Each command produces its own APK under `app/build/outputs/apk/<flavor>/debug/`, with its own package name, its own app name, and its own icon. Install all three on one emulator and they run as separate apps.

## License

MIT. See [LICENSE](LICENSE).
