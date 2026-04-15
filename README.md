# Earthquake Monitoring System

## 1. Project Overview

This project is a web application for fetching, storing, and displaying recent earthquake data from the USGS API.

The backend is built with Spring Boot and provides REST endpoints for managing earthquake data.  
The frontend is built with React and Bootstrap and is responsible for all user interaction.

---

## 2. Project Setup

### Clone repository
git clone https://github.com/simona155/earthquake-app.git

---

## 3. Database Setup (PostgreSQL)

- Install PostgreSQL locally
- Create a database: earthquake_db
- Configure credentials in: application.properties
  
---

## 4. Running the Backend

- Open project in IntelliJ IDEA
- Wait for Maven dependencies to load
- Run EarthquakeApplication



---

## 5. Running the Frontend
- cd earthquake-frontend
- npm install
- npm run dev
- 
---

## 6. How the Application Works

- The application automatically fetches earthquake data from the USGS API
- Only earthquakes with magnitude greater than 2.0 are stored
- Existing data is cleared before each new fetch to avoid duplicates
- The frontend communicates with the backend via REST API

To load data:

- Click the **"Fetch Latest"** button in the frontend

No manual API calls are required.

---

## 7. Assumptions

- PostgreSQL is running locally
- Database is empty at startup
- Time is stored as Unix timestamp (milliseconds)
- Data is refreshed manually via frontend button
- Only significant earthquakes (mag > 2.0) are stored

---

## 8. Optional Features Implemented

- Delete earthquake by ID
- Filtering earthquakes after a given timestamp
- Bootstrap-based responsive UI
- Loading indicator
- Magnitude-based color badges
