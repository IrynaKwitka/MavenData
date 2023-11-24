-- Table: public.clients

-- DROP TABLE IF EXISTS public.clients;

CREATE TABLE IF NOT EXISTS public.clients
(
    client_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    surname character varying(255) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    email character varying(255) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    tel_number character varying(255) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    registration_date character varying(255) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    CONSTRAINT clients_pkey PRIMARY KEY (client_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.clients
    OWNER to postgres;


-- Table: public.orders

-- DROP TABLE IF EXISTS public.orders;

CREATE TABLE IF NOT EXISTS public.orders
(
    order_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    client_id integer,
    data date,
    total_price double precision,
    order_count integer,
    CONSTRAINT order_pkey PRIMARY KEY (order_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.orders
    OWNER to postgres;

-- Table: public.products

-- DROP TABLE IF EXISTS public.products;

CREATE TABLE IF NOT EXISTS public.products
(
    product_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    product_title varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    price float DEFAULT NULL,
    balance int DEFAULT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.products
    OWNER to postgres;
