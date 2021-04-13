# midTrans

**Pre-requisites**

1. Android Studio
2. Appium Setup (node version & GUI)
3. Emulator created / real Android device USB connected
4. JAVA / JDK
5. IntellijIDEA OR Eclipse
6. Node.js
7. Homebrew


Mobile Web Automation using Appium - TC1

1. Clone the repository to your local systen
2. Navigate to the project directory on command prompt / Terminal
3. Execute the below command - **mvn clean test -Dplatform="android" -DplatformVersion="11.0" -DdeviceName="emulator-5554"**
4. **mvn site**
5. **allure serve ./midTrans/target/surefire-reports** - creates temporary allure HTML report
6. **TestNG index.html** can also be found at the default location.

Replace - 
-Dplatform = android
-DplatformVersion = <android version>
-DdeviceName = <name of the device>

API JSON Responses compare Utility -
Compares 2 files having different endpoints. Utility hits the endpoint simultanously from 2 files & compares the JSON responses.

