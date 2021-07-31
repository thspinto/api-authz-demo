# OpenAPI OPA AuthZ Demo

Run the service
```
./gradlew bootRun
```

Generate openAPI specification
```
./gradlew clean generateOpenApiDocs
```

To start all services:
```
docker-compose up -d
```

If would like to query OPA:
```
curl localhost:8181 -d '{"path":"/api/books", "method":"GET"}'
```

Make requests to the api:
```
# Example with admin token
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJ1c2VybmFtZSI6ImpvaG4uZG9lIiwiZ3JvdXBzIjpbImFkbWluIl19.wO9zu5WPBrI_8xlth1t7jYdYz8wrYgU4s_aC9xcgrWw" localhost:8080/api/books -v

# Example with user token
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJ1c2VybmFtZSI6ImpvaG4uZG9lIiwiZ3JvdXBzIjpbInVzZXIiXX0._VG25kipy6Lh3qveKDSXhXifdTXGQtLNsPyUEfDtUm8" localhost:8080/api/books -v
```

Use [jwt.io](https://jwt.io) to see the jwt claims.

# References

* [Demo Application](https://github.com/eugenp/tutorials/blob/master/spring-boot-modules/spring-boot-springdoc)
* [OPA Voter](https://github.com/open-policy-agent/contrib/tree/main/spring_authz)
