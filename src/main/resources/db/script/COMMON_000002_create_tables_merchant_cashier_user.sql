CREATE TABLE merchants (
    id SERIAL PRIMARY KEY,
    merchant_id INT NOT NULL,
    chat_id INT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(12) NOT NULL,
    email VARCHAR(255),
    biz_name VARCHAR(255),
    biz_address VARCHAR(1000),
    is_activated BOOLEAN NOT NULL,
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12)
);
CREATE INDEX idx_merchants_merchant_id ON merchants(merchant_id);
CREATE INDEX idx_merchants_chat_id ON merchants(chat_id);

CREATE TABLE cashiers (
    id SERIAL PRIMARY KEY,
    f_merchants_id INT NOT NULL,
    cashier_id INT NOT NULL,
    chat_id INT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(12) NOT NULL,
    is_activated BOOLEAN NOT NULL,
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12),
    CONSTRAINT fk_f_merchants_id FOREIGN KEY (f_merchants_id) REFERENCES merchants(id)
);
CREATE INDEX idx_cashiers_cashier_id ON cashiers(cashier_id);
CREATE INDEX idx_cashiers_chat_id ON cashiers(chat_id);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    f_merchants_id INT NOT NULL,
    f_cashiers_id INT NOT NULL,
    user_type VARCHAR(32) NOT NULL,
    username VARCHAR(32) NOT NULL,
    secret_key VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(5000) NOT NULL,
    mfa_token VARCHAR(5000) NOT NULL,
    expiry_at TIMESTAMP(6) NOT NULL,
    is_required_change_pwd BOOLEAN NOT NULL,
    last_login_at TIMESTAMP(6),
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12),
    CONSTRAINT fk_f_merchants_id FOREIGN KEY (f_merchants_id) REFERENCES merchants(id),
    CONSTRAINT fk_f_cashiers_id FOREIGN KEY (f_cashiers_id) REFERENCES cashiers(id)
);
CREATE INDEX idx_users_username ON users(username);

CREATE TABLE user_devices (
    id SERIAL PRIMARY KEY,
    f_users_id INT NOT NULL,
    device_id VARCHAR(255) NOT NULL,
    system_remark VARCHAR(2500),
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12),
    CONSTRAINT fk_f_users_id FOREIGN KEY (f_users_id) REFERENCES user_devices(id)
);
CREATE INDEX idx_user_devices_device_id ON user_devices(device_id);
