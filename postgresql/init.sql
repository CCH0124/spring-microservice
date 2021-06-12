CREATE USER itachi WITH ENCRYPTED PASSWORD '12345678';

CREATE DATABASE employee
    WITH
    OWNER = itachi
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

ALTER USER itachi WITH SUPERUSER;
grant all privileges on database employee  to itachi;

\c employee

DROP TABLE IF EXISTS public.user;
CREATE TABLE IF NOT EXISTS public.user (
    id      serial,
    name    varchar(20) NOT NULL,
    age     integer,
    email   varchar(100) NULL,
    tel     varchar NOT NULL,
    primary key (id)
);
ALTER TABLE public.user OWNER TO itachi;