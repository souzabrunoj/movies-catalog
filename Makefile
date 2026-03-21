.DEFAULT_GOAL := help

GRADLE ?= ./gradlew
ANDROID_APP := :androidApp
KMP_MODULE := :composeApp
APP_ID := com.moviecatalog

.PHONY: help
help: ## List Make targets (default)
	@grep -E '^[a-zA-Z0-9_.-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-28s\033[0m %s\n", $$1, $$2}'

# --- Android ---

.PHONY: assembleDebug
assembleDebug: ## Build debug APK (:androidApp)
	$(GRADLE) $(ANDROID_APP):assembleDebug

.PHONY: installDebug
installDebug: ## Install debug APK on a connected device / emulator
	$(GRADLE) $(ANDROID_APP):installDebug

.PHONY: openDebug
openDebug: ## Launch the debug app (requires adb; uses MainActivity)
	adb shell am start -n $(APP_ID)/.MainActivity

.PHONY: runAppDebug
runAppDebug: ## assembleDebug + installDebug + open launcher (like IDE Run)
	$(GRADLE) $(ANDROID_APP):assembleDebug $(ANDROID_APP):installDebug && $(MAKE) openDebug

.PHONY: clearCache
clearCache: ## Clear app data for $(APP_ID) via adb
	adb shell pm clear $(APP_ID)

# --- iOS / KMP framework (usually called from Xcode) ---

.PHONY: embedIosFramework
embedIosFramework: ## Build Compose framework for Xcode (expects Xcode/Gradle integration env)
	$(GRADLE) $(KMP_MODULE):embedAndSignAppleFrameworkForXcode

# --- Quality ---

.PHONY: detekt
detekt: ## Run Detekt on all modules that apply the plugin
	$(GRADLE) detekt

.PHONY: check
check: ## detekt + assembleDebug (handy before push / CI)
	$(GRADLE) detekt $(ANDROID_APP):assembleDebug

# --- Gradle housekeeping ---

.PHONY: clean
clean: ## ./gradlew clean
	$(GRADLE) clean

.PHONY: stop
stop: ## Stop Gradle daemons
	$(GRADLE) --stop

.PHONY: status
status: ## Gradle daemon status
	$(GRADLE) --status

.PHONY: test
test: ## Run JVM/unit tests (all modules)
	$(GRADLE) test

.PHONY: build
build: ## Alias: same as check (detekt + debug APK)
	@$(MAKE) check