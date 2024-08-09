CREATE TABLE actions (
    id SERIAL PRIMARY KEY,
    command VARCHAR(32) NOT NULL UNIQUE,
    message VARCHAR(4000) NOT NULL,
    message_kh VARCHAR(4000),
    parent_id INT,
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12),
    CONSTRAINT fk_parent FOREIGN KEY (parent_id) REFERENCES actions(id)
);
CREATE INDEX idx_actions_command ON actions(command);

CREATE TABLE user_actions (
    id SERIAL PRIMARY KEY,
    f_actions_id INT NOT NULL,
    user_id INT NOT NULL,
    chat_id INT NOT NULL,
    is_bot BOOLEAN NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    from_date_at TIMESTAMP(6) NOT NULL,
    to_date_at TIMESTAMP(6) NOT NULL,
    retry_count INT,
    is_completed BOOLEAN NOT NULL,
    system_remark VARCHAR(2500),
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12),
    CONSTRAINT fk_f_actions_id FOREIGN KEY (f_actions_id) REFERENCES actions(id)
);
CREATE INDEX idx_user_actions_user_id ON user_actions(user_id);
CREATE INDEX idx_user_actions_chat_id ON user_actions(chat_id);

CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    session_id VARCHAR(255) NOT NULL,
    device_id VARCHAR(255) NOT NULL,
    expiry_at TIMESTAMP(6) NOT NULL,
    system_remark VARCHAR(2500),
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12)
);
CREATE INDEX idx_sessions_session_id ON sessions(session_id);

CREATE TABLE auth_codes (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(32) NOT NULL,
    user_type VARCHAR(12) NOT NULL,
    code_type VARCHAR(32) NOT NULL,
    code_value VARCHAR(5000) NOT NULL,
    code_signature VARCHAR(5000) NOT NULL,
    expiry_at TIMESTAMP(6) NOT NULL,
    is_used BOOLEAN NOT NULL,
    retry_count INT,
    parent_id INT,
    system_remark VARCHAR(2500),
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    created_by VARCHAR(12) NOT NULL,
    modified_at TIMESTAMP(6),
    modified_by VARCHAR(12),
    CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES auth_codes(id)
);
CREATE INDEX idx_auth_codes_code_signature ON auth_codes(code_signature);

