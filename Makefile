# Define variables
GRADLEW = gradlew.bat
ADB = adb

# Default target (runs `make build`)
all: build

# Build the debug APK
build:
	$(GRADLEW) assembleDebug

# Run unit and instrumented tests
all-tests:
	$(GRADLEW) test
	$(GRADLEW) uitest

# Run unit tests
test:
	$(GRADLEW) test

# Run instrumentation tests on a connected device/emulator
uitest:
	make start-emulator
	$(GRADLEW) connectedAndroidTest

# Run lint and detekt
lint:
	$(GRADLEW) lint
	$(GRADLEW) detekt

# Clean the project
clean:
	$(GRADLEW) clean

# Install the debug APK on a connected device/emulator
install:
	$(ADB) install -r app/build/outputs/apk/debug/app-debug.apk

# Uninstall the app from the device/emulator
uninstall:
	$(ADB) uninstall com.example.yourapp

# Open the logcat to debug
logcat:
	$(ADB) logcat -s "YourAppTag"

start-emulator:
	@echo "Starting emulator..."
	adb devices | findstr emulator || start cmd /c "emulator -avd Medium_Phone_API_34 -no-snapshot-load"
	@echo "Waiting for device to be ready..."
	adb wait-for-device

.PHONY: all build release test uitest clean install uninstall logcat start-emulator
