
# launch docker desktop

docker build -t challenge-app .
docker run -p 8080:8080 challenge-app
# Then access the API:
# curl http://localhost:8080/users/1

# use java.net.ServerSocket
