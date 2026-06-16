# Flight Manager Application using Kotlin Backend

If you are a Flight Sim player and need a step more to realism here is Flight Manager Application: A solution
for taking your airline simulation to a next level

### Sumary

- [Features](#features)
- [How to download](#download)
- [How to run locally](#run)
- [How to make your customizations](#customs)
- [Concerns !!MUST READ!!](#concerns)

## Story behind this project

I started this project as an aid to my studies sessions using flight simulation as timer, I was surprised on how
flying would help me study, even when I'm at the cockpit actively flying the plane.</p>
So, in my free time I worked on a system that could let me plan my sessions just like a real airline plans flights.
I soon noticed that this could be more than just a study gimmick, that's why we are here now, feel free to use as you like.

# <a id="features">Features</a>

### You can host this application so that you and your friends can see and participate in flights
^^^ Please read the Concerns section to further disclaimers on this
### Admin Options to create, edit and cancel flights
### Flight history
### Boarding Pass generator

# <a id="download">How to download</a>

```bash
git clone https://github.com/dpontoavi/FlightManagerApplication-backend.git
cd FlightManagerApplication-backend
```

---

# <a id="run">How to run locally</a>

### Requirements

- JDK 21+
- Docker Desktop
- IntelliJ IDEA (recommended)

### 1. Start the database

```bash
docker-compose up -d
```

### 2. Configure environment variables

In IntelliJ, go to **Run → Edit Configurations → Environment Variables** and add:

```
ADMIN_LOGIN=your_login
ADMIN_PASSWORD=your_password
JWT_SECRET=your_secret
JWT_AUDIENCE=flightstudy
JWT_DOMAIN=http://localhost:8080
```

### 3. Run the application

```bash
./gradlew run
```

The server will start at `http://localhost:8080`.
On startup, the console will display the admin panel URL:

```
INFO - Admin panel available at: /auth/xxxxxxxxxxxxxxxx
```

This URL changes on every restart for security reasons.

---

# <a id="endpoints">API Endpoints</a>

### Flights

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/v1/flights` | No | List all flights |
| GET | `/api/v1/flights/{id}` | No | Get flight by ID |
| POST | `/api/v1/flights` | Yes | Create a flight |
| PUT | `/api/v1/flights/{id}` | Yes | Update a flight |
| DELETE | `/api/v1/flights/{id}` | Yes | Delete a flight |

### Boarding Passes

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/v1/boarding-passes` | No | List all boarding passes |
| GET | `/api/v1/boarding-passes/{id}` | No | Get boarding pass by ID |
| POST | `/api/v1/boarding-passes` | Yes | Create a boarding pass |
| DELETE | `/api/v1/boarding-passes/{id}` | Yes | Cancel a boarding pass |

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/{random-url}` | Login and get JWT token |

For the payload, post a Json as the following:
```json
    {
        "ADMIN_LOGIN": "your_admin_login_env",
        "ADMIN_PASSWORD": "your_admin_password_env"
    }
```

Protected routes require the header:
```
Authorization: Bearer <token>
```

Tokens expire after **1 hour**.

---

# <a id="env">Environment Variables</a>

| Variable | Description |
|----------|-------------|
| `ADMIN_LOGIN` | Admin username |
| `ADMIN_PASSWORD` | Admin password |
| `JWT_SECRET` | Secret key for JWT signing |
| `JWT_AUDIENCE` | JWT audience (e.g. `flightstudy`) |
| `JWT_DOMAIN` | JWT issuer domain (e.g. `http://localhost:8080`) |

---

# <a id="customs">How to make your customizations</a>

| Prerequisites                                |
|----------------------------------------------|
| Understanding of Kotlin Programming Language |
| Understanding of the Ktor framework          |

Assuming you already have these prerequisites, you can modify/add any feature of the source code

### Also see: [Aircraft Manager Application Frontend](https://github.com/dpontoavi/AirlineManagerApplication-frontend) A implementation of the backend in react for frontend only customizations!

---

# <a id="concerns">Concerns !!PLEASE READ!!</a>

1. I would like to address some concerns regarding the security of this application. As a developer,
I recognize that I do not have a formal background or deep expertise in cybersecurity. Because of this,
I am aware that there may be vulnerabilities or gaps in the current implementation that I am not fully equipped
to identify or mitigate at this stage. That said, I am committed to continuous improvement and have strong intentions to evolve this project into a more
secure and reliable tool over time. Security is not being ignored—it is simply an area that will require further study,
external input, and iterative development to reach a higher standard. It is also important to emphasize that any cloud-based application inherently carries additional security considerations.
Hosting systems in the cloud increases exposure and can introduce potential vulnerabilities if not properly managed. For this reason,
security should always be treated as a critical aspect of development, especially when deploying applications in cloud environments. Although it is completely safe to run locally and using video call app (ie. Discord) to stream your screen to your friends and/or using the boarding pass generator.

2. The flight is assigned exclusively to a single passenger, this could lead to severe problems when using this backend and its associated [frontend](https://github.com/dpontoavi/AirlineManagerApplication-frontend). Remember that this is a **studying tool** made for myself and my girlfriend and not solely intended for flight simulation! I'll consider releasing a version that creates only the flight and the ability to assign multiple passengers.
