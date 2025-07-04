CREATE TABLE patients
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(255)                NOT NULL,
    email      VARCHAR(255)                NOT NULL UNIQUE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE doctors
(
    id               UUID PRIMARY KEY,
    name             VARCHAR(255)                NOT NULL,
    started_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    finished_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    duration_seconds BIGINT                      NOT NULL DEFAULT 0,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    cost             NUMERIC(19, 2)              NOT NULL DEFAULT 0.00
);

CREATE TABLE appointments
(
    id      UUID PRIMARY KEY,
    doctor  UUID                        NOT NULL,
    patient UUID                        NOT NULL,
    date    TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
);