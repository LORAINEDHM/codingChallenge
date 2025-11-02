

# STEP 1: launch docker desktop
/!\ the following commands must be executed at the root of Dockerfile file

# STEP 2: RUN THE TESTS

## OPTION 1 (using docker-compose):
- **docker compose --profile test run --rm tester**
(The --rm flag cleans up the temporary test container after it finishes.)


## OPTION 2:
Execute the 2 following commands to run the tests : 
- **docker build -t tests --target tester .** 
(The --target flag tells docker to stop the build after the "tester" stage.)
- **docker run --rm tests**
(The --rm flag cleans up the temporary test container after it finishes.)

-----------------------
Example of logs :

![img.png](img.png)

# STEP 3: RUN THE APP

## OPTION 1 (using docker-compose):
- **docker compose up app**

## OPTION 2:
Execute the 2 following commands to run the app :
- **docker build -t coding-challenge-app .**
- **docker run -p 8080:8080 coding-challenge-app**

# STEP 4: ACCESS THE API

curl http://localhost:8080/users/1
use java.net.ServerSocket

# STEP 5: STOP THE APPLICATION

- **docker compose down**