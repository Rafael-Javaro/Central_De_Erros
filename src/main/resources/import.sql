INSERT INTO users (email, name, password, created_at) VALUES ('user@mail.com', 'teste', 'password', CURRENT_TIMESTAMP);
INSERT INTO users (email, name, password, created_at) VALUES ('user2@mail.com', 'teste2', 'password2', CURRENT_TIMESTAMP);
INSERT INTO users (email, name, password, created_at) VALUES ('user3@mail.com', 'teste3', 'password3', CURRENT_TIMESTAMP);

INSERT INTO errors_table (level, description, log, origin, created_at) VALUES ('WARNING', 'Unused vars', 'LOG243: UNUSED VARS IN LINE 23', 'Mobile Application Beta', CURRENT_TIMESTAMP);
INSERT INTO errors_table (level, description, log, origin, created_at) VALUES ('ERROR', 'Null Pointer Exception', 'LOG259: NULL POINTER EXCEPTION IN LINE 1009', 'Client Application Alpha', CURRENT_TIMESTAMP);
INSERT INTO errors_table (level, description, log, origin, created_at) VALUES ('WARNING', 'Its over 9000!', 'LOG144: CYCLOMATIC COMPLEXITY ABOVE 9000', 'Client Application Beta', CURRENT_TIMESTAMP);
INSERT INTO errors_table (level, description, log, origin, created_at) VALUES ('INFO', 'Functions over 100 lines should be refactored', 'LOG693: FUNCTION ABOVE 100 LINES. MUST REFACTOR', 'FoodApp', '2016-04-29 00:00:00');