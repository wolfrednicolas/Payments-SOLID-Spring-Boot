-- V1__init.sql
CREATE TABLE IF NOT EXISTS payments_history (
    id IDENTITY PRIMARY KEY,
    order_id VARCHAR(64) NOT NULL,
    method VARCHAR(32) NOT NULL,
    amount DECIMAL(18,2) NOT NULL,
    currency VARCHAR(8) NOT NULL,
    tx_id VARCHAR(128) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
