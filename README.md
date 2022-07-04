# microservices

### Services
* cambio-service
* book-service

### Endpoints:
| Service                | EndPoint                                                   |
|------------------------|------------------------------------------------------------|
| api-gateway            | http://localhost:8765                                      |
| Naming-server (Eureka) | http://localhost:8761                                      |
| book-service           | {api-gateway}/api-gateway/book-service/{amount}/{currency} |
| cambio-service         | {api-gateway}/cambio-service/{from}/{to}/{amout}           |
| api docs               | {api-gateway}/swagger-ui.html                              |

### Dependencies
* Spring (Actuator, boot, config-client, jpa, web)
* Flyway
* Eureka
* Feign
* Resilience4j
* OpenAPI (Swagger)