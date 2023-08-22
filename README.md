# Social media API

## Contents

* [Run in Docker](#run-in-docker)
* [API Documentation](#api-overview)

## Run in Docker

You do need a Docker daemon.
If you do not have Docker follow this [link](https://www.docker.com/) to download and install it.

* Run terminal window in directory which you want project located in.
* Clone project using Git:

```text
git clone https://github.com/EugeneBUSUEK/SocialMediaAPI.git
```
* move to project root directory

* Start docker compose process

```text
docker compose up
```

Optionally you can set your own envs in .env file.

### Credentials

.env file:
```file
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/postgres
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWRD=root
MINIO_ACCESS_KEY=admin
MINIO_SECRET_KEY=admin123
MINIO_URL=http://minio:9000
MINIO_BUCKET=socialmedia
JWT_SECRET_KEY=5A7234753777217A25432A462D4A614E645267556B58703273357638792F413F

POSTGRES_PASSWORD=root
POSTGRES_USER=root

MINIO_ROOT_USER=admin
MINIO_ROOT_PASSWORD=admin123
```

---

## API documentation

If yuo want to read api documentation follow this link 

```text
http://<yourhostname>:8081/api/v1/docs/swagger-ui/index.html#/
```
