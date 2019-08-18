## Run tests from command line and generate reports with command
```
gradlew clean test -PbrowserType=Chrome -Penv=PROD allureServe
```

## Or add to VM options
```
-Denv=PROD -DbrowserType=Chrome
```

