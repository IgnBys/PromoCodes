CREATE TABLE promocodes (
                            id INT PRIMARY KEY UNIQUE ,
                            code VARCHAR(24) NOT NULL UNIQUE ,
                            expirationDate TIMESTAMP NOT NULL,
                            discountAmount DECIMAL(10, 2) NOT NULL,
                            currency VARCHAR(3) NOT NULL,
                            maxUsages INT NOT NULL
);
