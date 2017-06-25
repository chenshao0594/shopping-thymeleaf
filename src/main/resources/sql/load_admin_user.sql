create schema IF NOT EXISTS SHOPPING;

INSERT INTO jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by, created_date,last_modified_date) VALUES(1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '' , true, 'en', 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by, created_date,last_modified_date) VALUES(2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '' , true, 'en', 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by, created_date,last_modified_date) VALUES(3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', ''  , true, 'en', 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO jhi_user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by, created_date,last_modified_date) VALUES(4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '' , true, 'en', 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO jhi_authority(name) VALUES('ROLE_ADMIN');
INSERT INTO jhi_authority(name) VALUES('ROLE_USER');
INSERT INTO jhi_authority(name) VALUES('ROLE_CUSTOMER');
INSERT INTO jhi_authority(name) VALUES('ROLE_SYSTEM');

INSERT INTO jhi_user_authority(user_id, authority_name) VALUES(1, 'ROLE_ADMIN');
INSERT INTO jhi_user_authority(user_id, authority_name) VALUES(1, 'ROLE_USER');
INSERT INTO jhi_user_authority(user_id, authority_name) VALUES(3, 'ROLE_ADMIN');
INSERT INTO jhi_user_authority(user_id, authority_name) VALUES(3, 'ROLE_USER');
INSERT INTO jhi_user_authority(user_id, authority_name) VALUES(4, 'ROLE_USER');

-- category
INSERT INTO category(ID, STATUS, CODE , DEPTH, LINEAGE, SORT_ORDER, VISIBLE, created_by, last_modified_by, created_date,last_modified_date) VALUES (-1, true, 'Root', 0, '/', 0, true, 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--COUNRTRY 
INSERT INTO Country(id,name,iso_code) VALUES (1,'Afghanistan','AFG');

--default merchant store
INSERT INTO merchant_store(id,name,code,phone,address,city,POSTAL_CODE,EMAIL_ADDRESS,COUNTRY_ID,state_Province,domain_Name,created_by,last_modified_by, created_date,last_modified_date) VALUES (1, 'DEFAULT', 'DEFAULT' ,99,'chenghui road', 'shanghai', '201204', 'shane@sap.com' ,1,'shanghai','localhost','system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);





