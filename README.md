# Crowd & Safety Predictor — Spring Boot Backend

This backend is adapted to the uploaded React/Vite frontend and its current `src/services/api.js` contract. It uses Java 21, Spring Boot 3.5, Spring Data JPA and MySQL 8.4.

## APIs implemented

- `GET /api/places?search=&category=&city=` — returns places in the shape already used by the React app.
- `GET /api/places/{id}` — returns one place.
- `POST /api/predict` — accepts `{ "placeId":"victoria-memorial", "date":"2026-11-22", "time":"18:00" }` and returns the results structure used by the React results page.
- `GET /api/safety/notices?placeId=&type=&severity=` — safety notices enriched with place details.
- `GET /api/trends/heatmap/{id}` — 7-day x 24-hour crowd heatmap.
- `GET /api/metadata/cities` and `/api/metadata/categories` — filter values.
- `GET /api/health` — health check.

## Database setup

1. Start MySQL Server 8.4.
2. In MySQL Workbench, connect to your local MySQL server and run:

```sql
CREATE DATABASE crowd_safety;
```

3. Set the environment variables `DB_USERNAME` and `DB_PASSWORD` to your MySQL credentials. The default username is `root`; the default password is blank. Do not publish credentials to GitHub.

The application creates/updates three tables using JPA: `places`, `crowd_observations`, and `prediction_records`. On the first run, it imports the 12 clearly labelled sample places from `src/main/resources/places.json` and creates weekday/weekend hourly sample observations (576 rows). It does not reseed if the places table already contains data.

## Run in VS Code

1. Install the VS Code Extension Pack for Java (Microsoft).
2. Install Maven if you do not already have it (`mvn -version` should work).
3. Open this folder in VS Code.
4. In the VS Code terminal run:

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`. Test `http://localhost:8080/api/health` in your browser.

## Connect the React app

1. In the React project, replace `src/services/api.js` with `frontend-integration/api.js` from this backend ZIP.
2. Create a `.env` file in the React project root:

```env
VITE_API_URL=http://localhost:8080
```

3. Restart the Vite dev server (`npm run dev`). Vite environment variables are read at startup.
4. The React service functions keep their existing names, so components should not need to be rewritten.

When deployed, set `VITE_API_URL` in Vercel to the public backend URL and redeploy the frontend. Restrict CORS to your exact Vercel domain before public production use; the starter currently uses permissive CORS to simplify local integration.

## Prediction methodology and limitations

The predictor is a transparent rule-based prototype, not a trained AI model and not a live crowd sensor. It selects the weekday/weekend sample hourly index for the chosen place and hour, adds a small deterministic date variation, and maps the 0–100 index to Low (0–39), Moderate (40–69), or High (70–100). Crowd-related risk labels are preliminary and do not establish that a venue is safe or unsafe. Sample records are illustrative and must not be represented as verified live data.

## Notes

- Existing frontend uses string place IDs such as `victoria-memorial`; the backend preserves those IDs.
- If you later edit the sample records in `places.json`, clear the three tables before first-run seeding again, or add a proper admin/data-import workflow.
- This ZIP is source code. It has not been run against your laptop's MySQL credentials or deployed to a public server; those environment-specific steps must be completed locally.
