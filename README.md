## Interview Android Project
#### It is an Android app project used in the Android Developer technical interview at PicPay .

<p/>
   - Here is my linkedin profile: [lucas-ferreira-machado](https://www.linkedin.com/in/lucas-ferreira-machado) .  <p/>
<p/>


## 1. Challenge

This challenge is about issues that impacts on the user experience and prevents the project to scale. _[(see more...)](https://github.com/mobilepicpay/desafio-android)_

#### 1.1 Requirements
1. Keep UI screen state on configurations changes.
2. Identify and fix the lifecycle management issues and null instance issues.
3. Cache the data sent by the server.
4. Improve architecture to handle business logic changes.
5. Improve architecture to handle presentation logic changes.
6. Improve test strategy with automated tests creating unit and instrumented tests.

## 2. My resolution steps
I am passionate about learning mobile development. <p/>
I would really appreciate any suggestions or recommendations. Feel free to contact me. <p/>
I followed this steps to improve the project: <p></p>

## 2.1. [Project] Project improvements.  _[(more...)](https://github.com/lucasferreiramachado/desafio-android/pull/1)_
First, I improved the project to make easier:
- to manage external dependency versions and use the latest stable versions.
- to scale the project in a cleaner and more efficient way.
- the project modularization process later.

**Main changes:**
- Update dependency versions.
- Sort dependency libraries by name to make easier to search for a specific one.
- Improve plugin declarations
- Separate external dependencies in Libraries, Kapt Libraries, Test Libraries and Android Test Libraries.
- Update the Kotlin version to 1.5.21.
- Update the Android Tools Gradle plugin version to 4.2.2.
- Update the Gradle version to 6.7.1.
- Create gradle variables to handle the android versions: minSdkVersion, targetSdkVersion e compileSdkVersion

## 2.2. [UI Tests] Make UI Tests passed. Ensure the new changes don't break the existing user experience.  _[(see more...)](https://github.com/lucasferreiramachado/desafio-android/pull/1)_

**Ensure the new changes don't break the existing user experience.**

#### All UI Tests passed
- **shouldDisplayTitle**:
    - **Given** that application is launched.
    - **When** the screen appears.
    - **Then** It should display the correct screen title.
  <p/>
- **shouldDisplayListItem**:
    - **Given** that application is launched and the screen is loaded.
    - **When** the contact list appears.
    - **Then** It should display the list item with the contact information.

**Main changes:**
- Use [Idling Resources](https://developer.android.com/training/testing/espresso/idling-resource) and [@VisibleForTesting](https://developer.android.com/studio/write/annotations#visible) annotation to wait screen is loaded before validating the ui state in the MainActivity UI tests. <p/>
    - Idling Resources:
        - An idling resource represents an asynchronous operation whose results affect subsequent operations in a UI test. By registering idling resources with Espresso, you can validate these asynchronous operations more reliably when testing your app.
      <p/>
- Make MockWebServer works on UI tests.
    - Customize the testInstrumentationRunner.
    - Customize the Application class to allow customize the base url.
        - TestApp (Application)
        - TestAppConfig (with the base url http://localhost:8080 )
        - TestAppRunner (AndroidJUnitRunner)    <p/>
       