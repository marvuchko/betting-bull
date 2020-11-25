CREATE TABLE IF NOT EXISTS public.contract_fee(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by VARCHAR(255) DEFAULT 'ADMIN',
    updated_by VARCHAR(255) DEFAULT 'ADMIN',
    id_team BIGINT NOT NULL,
    id_player BIGINT NOT NULL,
    transfer_fee FLOAT NOT NULL DEFAULT 0,
    team_commission FLOAT NOT NULL DEFAULT 0,
    total_fee FLOAT NOT NULL DEFAULT 0
);