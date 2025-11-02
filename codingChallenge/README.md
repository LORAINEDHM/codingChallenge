

# STEP 1: launch docker desktop
/!\ the following commands must be executed at the root of Dockerfile file

# STEP 2: RUN THE TESTS

- **docker compose --profile test run --rm --build tester**
(The --rm flag cleans up the temporary test container after it finishes.)


-----------------------
Example of logs :

![img.png](img.png)

# STEP 3: RUN THE APP

- **docker compose up -d --build app**


# STEP 4: ACCESS THE API

curl http://localhost:8080/users/1
use java.net.ServerSocket

# STEP 5: STOP THE APPLICATION

- **docker compose down**