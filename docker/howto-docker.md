# HOWTO DOCKER

## Clean up disk space

```
# disk usage
docker system df

# remove all stopped containers, etc.
docker system prune
```

## Already exists

In case of similar error as `docker: Error response from daemon: service endpoint with name <your container name> already exists.`
- stop the container
- if not taking effect, try to remove the container (make sure you will be able to recreate it)
- if creating it and starting it still show the error of existance, try to inspect the network `docker network inspect bridge` and try running the command `docker network disconnect --force bridge <container name>`
