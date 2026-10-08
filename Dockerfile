# Runs the tests and every demo on Java 25 without installing a JDK.
#   docker build -t object-relationships .
#   docker run --rm object-relationships
FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY . .
RUN ./mvnw -q -DskipTests compile
CMD ["scripts/verify.sh"]
