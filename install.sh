cd firebase-kotlin-sdk
./gradlew assemble
./gradlew publishToMavenLocal
cd ../
./gradlew shared:carthageUpdate
./gradlew shared:podspec
cd ios
pod install
