ALTER TABLE TICKETS ADD name VARCHAR(255) NULL;
UPDATE TICKETS SET name = "<no name>";
ALTER TABLE TICKETS MODIFY name VARCHAR(255) NOT NULL;