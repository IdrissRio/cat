echo "Running CAT on CAT's source code. This will take a while... Please wait."
./gradlew build -x test -PenableOptimization
java -jar cat.jar build/**/*.java src/**/*.java  -entryPoint org.extendj.Cat main -visualise