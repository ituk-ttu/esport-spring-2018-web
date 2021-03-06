CREATE TABLE TICKET_LOGIN_LINKS
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  ticketId INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT TICKET_LOGIN_LINKS_TICKETS_id_fk FOREIGN KEY (ticketId) REFERENCES TICKETS (id)
);
CREATE UNIQUE INDEX TICKET_LOGIN_LINKS_name_uindex ON TICKET_LOGIN_LINKS (name);