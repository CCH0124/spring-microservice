postgresql install

```yml
version: '3.7'
services:
  postgres:
    container_name: postgres
    image: postgres
    restart: always
    ports:
      - "5432:5432"
    hostname: postgres
    environment:
      - POSTGRES_PASSWORD=123456
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
      - postgresql-data:/var/lib/postgresql/data
    healthcheck:
        test: ["CMD-SHELL", "pg_isready -U postgres"]
        interval: 30s
        timeout: 10s
        retries: 5


volumes:
  postgresql-data:
```

```bash=
docker-compose up -d
docker-compose down --volumes # remove
```

```bash=
employee-# \dt
       List of relations
 Schema | Name | Type  | Owner
--------+------+-------+--------
 public | user | table | itachi
(1 row)

```

