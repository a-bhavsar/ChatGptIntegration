CREATE TABLE IF NOT EXISTS "patient" (
   id SERIAL PRIMARY KEY ,
   first_name varchar(50) NOT NULL,
   last_name varchar(50),
   age INT NOT NULL,
   gender varchar(30) NOT NULL,
   blood_group varchar(30) NOT NULL,
   contact VARCHAR(15) NOT NULL,
   email VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "medical_history" (
    id SERIAL PRIMARY KEY,
    doctor_name varchar(50) NOT NULL,
    department varchar(50) NOT NULL,
    symptoms varchar(100) NOT NULL,
    disease varchar(100) NOT NULL,
    prescription varchar(500) NOT NULL,
    patient_id INT,
    FOREIGN KEY (patient_id) REFERENCES "patient"(id)
);

CREATE TABLE IF NOT EXISTS "medical_appointment" (
    id SERIAL PRIMARY KEY,
    first_name varchar(50),
    last_name varchar(50),
    age INT,
    gender varchar(50),
    blood_group varchar(50),
    symptoms varchar(500),
    prescription varchar(200),
    disease varchar(200),
    department varchar(200),
    next_schedule date,
    recommended_food varchar(500),
    restricted_food varchar(500),
    blood_pressure double precision,
    diabetes_level double precision
);