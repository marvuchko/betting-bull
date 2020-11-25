CREATE TABLE IF NOT EXISTS public.player(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by VARCHAR(255) DEFAULT 'ADMIN',
    updated_by VARCHAR(255) DEFAULT 'ADMIN',
    first_name VARCHAR(120) NOT NULL,
    last_name VARCHAR(120) NOT NULL,
    date_of_birth TIMESTAMP NOT NULL,
    career_start_date TIMESTAMP NOT NULL,
    id_team BIGINT
);

CREATE TABLE IF NOT EXISTS public.team(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by VARCHAR(255) DEFAULT 'ADMIN',
    updated_by VARCHAR(255) DEFAULT 'ADMIN',
    name VARCHAR(120) UNIQUE,
    preferred_currency VARCHAR(3) DEFAULT 'USD'
);

ALTER TABLE public.player ADD CONSTRAINT table_fk
FOREIGN KEY(id_team) REFERENCES public.team(id);