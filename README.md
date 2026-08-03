# E-Hub

A freelancing/job-portal exam platform: categories → subcategories → skills, qualifications,
location data, and a skill-certification exam flow (candidates take timed multiple-choice exams
and get a persisted, scored result).

This is a from-scratch rewrite of a 2018 university project (a `javax.servlet` + Hibernate 3.x
skeleton with no build tool, no tests, and no committed front-end) onto a modern Spring Boot
REST API. There is no front-end — the original JSP pages were never committed to version
control and are not recoverable, so this is a backend REST API only. The original source is
preserved for reference under [`legacy/EHub`](legacy/EHub) and tagged `legacy-snapshot` in git
history.

## Stack

- Java 21, Spring Boot (Maven, `spring-boot-starter-parent`)
- Spring Data JPA + Flyway (versioned migrations, `ddl-auto: validate`)
- MySQL (`mysql-connector-j`)
- Spring Security: stateless JWT auth, BCrypt password hashing
- springdoc-openapi (Swagger UI)
- Lombok
- Tests: JUnit 5, Mockito, `@WebMvcTest`/`@DataJpaTest` slices, Testcontainers (real MySQL) for
  repository and end-to-end integration tests

## Local setup

Prerequisites: JDK 21, Docker (for a local MySQL container and for running the Testcontainers
test suite).

1. Start MySQL:
   ```bash
   docker run --name ehub-mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=ehub -p 3306:3306 -d mysql:8
   ```
2. Set required environment variables (or export them in your shell):
   - `JWT_SECRET` — required, at least 32 characters, e.g. `openssl rand -base64 48`
   - `DB_HOST` / `DB_PORT` / `DB_NAME` / `DB_USERNAME` / `DB_PASSWORD` — optional, default to
     `localhost` / `3306` / `ehub` / `root` / *(empty)*, matching the Docker command above
3. Run the app:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Swagger UI: http://localhost:8080/swagger-ui.html · Health check: http://localhost:8080/actuator/health

### Running tests

```bash
./mvnw verify
```

Repository tests and full end-to-end flow tests use Testcontainers to spin up a real MySQL
container, so Docker must be running. Unit tests and `@WebMvcTest` slice tests don't need it.

## Auth model

- `POST /api/auth/register` and `POST /api/auth/login` return a JWT; send it as
  `Authorization: Bearer <token>` on subsequent requests.
- New registrations always get the `USER` role. There is no self-service way to become `ADMIN`
  (admin-only write access to categories/skills/exams/questions) — promote a user by updating
  `app_user.role` directly in the database. This is a deliberate gap, not an oversight: building
  an admin-invite/promotion flow is a follow-up, not required for the core rewrite.
- Lookup data (categories, subcategories, skills, qualifications, countries, cities) and exam
  listings are publicly readable; writes to them require `ADMIN`. Taking an exam (starting an
  attempt, answering, submitting, viewing your own results) requires any authenticated user.

## Keeping dependencies current

- `.github/dependabot.yml` opens weekly PRs for Maven and GitHub Actions dependencies.
- `.github/workflows/ci.yml` runs `./mvnw verify` (including the Testcontainers suite, since
  `ubuntu-latest` ships with Docker) on every push and PR to `master`, so Dependabot bumps are
  validated automatically before merge.
- Spring Boot's `spring-boot-starter-parent` BOM manages transitive dependency versions, so most
  upgrades are a single-line `pom.xml` bump.
