CREATE TABLE IF NOT EXISTS singer
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(60),
    last_name VARCHAR(40),
    birth_date date,
    version INTEGER
);

CREATE TABLE IF NOT EXISTS album
(
    id SERIAL PRIMARY KEY,
    singer_id INTEGER REFERENCES singer(id),
    title VARCHAR(100),
    release_date date,
    version INTEGER
);

CREATE TABLE IF NOT EXISTS instrument
(
    id SERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS singer_instrument
(
    singer_id INTEGER REFERENCES singer(id),
    instrument_id INTEGER REFERENCES instrument(id)
);

INSERT INTO singer(id, first_name, last_name, birth_date, version)
    VALUES(10, 'Michael', 'Jackson', '1958-08-29', 1) ON CONFLICT DO NOTHING;
INSERT INTO album(id, singer_id, title, release_date, version)
    VALUES(5, 10, 'Thriller', '1982-11-30', 1) ON CONFLICT DO NOTHING;
INSERT INTO instrument(id) VALUES(3) ON CONFLICT DO NOTHING;
INSERT INTO singer_instrument(singer_id, instrument_id)
    VALUES(10, 3) ON CONFLICT DO NOTHING;