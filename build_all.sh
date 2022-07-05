cd api-gateway
mvn spring-boot:build-image -DskipTests

cd ../book-service
mvn spring-boot:build-image -DskipTests

cd ../cambio-service
mvn spring-boot:build-image -DskipTests

cd ../naming-service
mvn spring-boot:build-image -DskipTests

