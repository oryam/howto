# HOWTO Nexus

### How to install Nexus OSS with docker

```
docker volume create nexus-data
docker run -d -p 8081:8081 --name nexus --mount source=nexus-data,target=/nexus-data sonatype/nexus3
docker logs -f nexus
```
