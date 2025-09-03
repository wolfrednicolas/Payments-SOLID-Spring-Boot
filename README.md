# Payments SOLID Spring Boot

Demonstration of **SOLID** principles with dynamic payment processors.  
- **SRP**: classes with clear responsibilities (service, processors, controller, error handler).  
- **OCP**: adding a new payment method is done by creating another `@Component("key")` without modifying the service.  
- **LSP**: each `PaymentProcessor` can replace another without breaking the contract.  
- **ISP**: `PaymentProcessor` is minimal; `Refundable` is opt-in.  
- **DIP**: `OrderService` depends on the interface and receives a `Map<String, PaymentProcessor>` from Spring.  

---

## ⚙️ Technologies Used

- Java 21  
- Spring Boot 3+  
- PostgreSQL  
- Docker  
- JUnit & Mockito for testing  

---

## 🛠 Step 1: Set up PostgreSQL Database using Docker

### 1.1. Pull the PostgreSQL Docker Image

Download the official PostgreSQL image from Docker Hub:

```bash
docker pull postgres
```

### 1.2. Create and Run a PostgreSQL Container

Create a container named `solid-principles` with the following configuration:

- **Username:** `postgres` (default)  
- **Password:** `mypassword`  
- **Port:** `5432`  

```bash
docker run --name solid-principles -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -d postgres
```

This will start a PostgreSQL instance listening on port `5432` of your local machine.

---

## 📄 Step 2: Configure Environment Variables

If you're using **VSCode**, create a `.env` file at the root of the project based on the `.env.example` file:

```bash
cp .env.example .env
```

Then, edit the new `.env` file and replace the default values with your actual PostgreSQL container values:

```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=postgres
DB_USER=postgres
DB_PASSWORD=mypassword
```

---

## ▶️ Step 3: Run the Application

Make sure your PostgreSQL container is running, then start the Spring Boot application using:

```bash
./mvnw spring-boot:run
```

Or run it from your IDE of choice.

---

## 🧪 Running Tests

You can run the unit tests with:

```bash
./mvnw test
```

---

## 🌐 Example API Requests

```bash
curl -X POST http://localhost:8080/api/payments      -H "Content-Type: application/json"      -d '{"method":"card","amount":149.90,"currency":"USD"}'

curl -X POST http://localhost:8080/api/payments/paypal/refunds/PAYPAL-123 -i
```

---

## 🚀 CI (GitHub Actions)

Includes a GitHub Actions workflow (`.github/workflows/maven.yml`) that compiles and runs tests with Java 21.

---

## 🗂️ Flyway

Runs automatically at application startup against H2.  
See `/src/main/resources/db/migration/V1__init.sql`.

---

### ⚠️ Important

After starting your PostgreSQL container and before launching the service:

- Ensure your database is running and accessible using the credentials provided in your `.env` file.  
- On first run, Flyway will automatically create the `flyway_schema_history` table and apply all pending migrations.  
- You can verify this by checking your database for:  
  - Your application tables (e.g., `users`)  
  - The presence of the `flyway_schema_history` table  

If no tables are created, verify:  
- The connection settings in your `.env` file  
- That migration files exist and follow proper naming inside `src/main/resources/db/migration`  

---

## 📜 License

This project is licensed under the **MIT Academic License**.  
See the [LICENSE](./LICENSE.md) file for details.  

---

## 👤 Author

Created by [Wolfred Montilla](https://github.com/wolfrednicolas)  
