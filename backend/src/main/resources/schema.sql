DROP TABLE IF EXISTS exchange_rate CASCADE;

CREATE TABLE IF NOT EXISTS exchange_rate
(
    currency VARCHAR(3) NOT NULL,
    date     DATE       NOT NULL,
    rate     DECIMAL(19, 4),
    PRIMARY KEY (currency, date)
)