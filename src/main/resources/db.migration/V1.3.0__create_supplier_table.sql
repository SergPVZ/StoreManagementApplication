CREATE TABLE IF NOT EXISTS supplier (
    id UUID PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone TEXT,
    address TEXT,
    webside TEXT,
    updatedAt TIMESTAMP
);

-- саплаер
