
To install, simply run
```
sh install.sh
```

Alternatively, you can run the following commands

```
cd firebase-kotlin-sdk
./gradlew assemble
./gradlew publishToMavenLocal
cd ../
./gradlew shared:carthageUpdate
./gradlew shared:podspec
cd ios
pod install
```

If iOS is showing that the shared library is not found, close XCode and run `pod install` again.