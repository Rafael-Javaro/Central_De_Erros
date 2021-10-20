INSERT INTO users (email, name, password, created_at) VALUES ('user@mail.com', 'teste', 'password', CURRENT_TIMESTAMP);
INSERT INTO users (email, name, password, created_at) VALUES ('user2@mail.com', 'teste2', 'password2', CURRENT_TIMESTAMP);
INSERT INTO users (email, name, password, created_at) VALUES ('user3@mail.com', 'teste3', 'password3', CURRENT_TIMESTAMP);

INSERT INTO errors_table (level, description, log, origin) VALUES ('WARNING', 'Unused vars', 'LOG243: UNUSED VARS IN LINE 23', 'Mobile Application Beta');
INSERT INTO errors_table (level, description, log, origin) VALUES ('ERROR', 'Null Pointer Exception', 'LOG243: NULL POINTER EXCEPTION IN LINE 1009', 'Client Application Alpha');
