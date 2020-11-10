CREATE TABLE public.sys_user
(
    user_id bigserial PRIMARY KEY,
    username character varying(20) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    real_name character varying(20) COLLATE pg_catalog."default",
    status smallint,
    created_at timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone
)

CREATE TABLE public.sys_role
(
    role_id serial PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default",
    description character varying(100) COLLATE pg_catalog."default",
    code character varying(255) COLLATE pg_catalog."default",
    status smallint DEFAULT 0,
    created_at timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone,
    authority integer
)

CREATE TABLE public.sys_user_role
(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT sys_user_role_pkey PRIMARY KEY (user_id, role_id)
)

CREATE TABLE public.sys_permission
(
    id serial PRIMARY KEY,
    parent_id integer,
    name character varying(50) COLLATE pg_catalog."default",
    css character varying(30) COLLATE pg_catalog."default",
    href character varying(1000) COLLATE pg_catalog."default",
    type smallint,
    permission character varying(50) COLLATE pg_catalog."default",
    sort integer
)

CREATE TABLE public.sys_role_permission
(
    role_id integer,
    permission_id integer
)