INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('BKDAApplicationID', 'BKDASecret', 'read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);
    
    
    
INSERT INTO users (
  userid,
  username,
  firstname,
  lastname,
  `password`,
  `status`) 
  VALUES (1, 'admin','sa', 'System Admin','$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri',
	1);