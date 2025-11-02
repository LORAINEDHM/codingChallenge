

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

The server starts after the csv data has finished downloading.

Endpoints:
- All users: GET http://localhost:8080/api/users
- User by ID: GET http://localhost:8080/api/users/{uuid}
- Count by city: GET http://localhost:8080/api/users/countByCity?city={name}


# STEP 5: STOP THE APPLICATION

- **docker compose down**