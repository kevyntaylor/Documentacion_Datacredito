SET search_path TO ebs;

-- User default
insert into ebs."user" ("id", "active", "create_date", "edit_date", "email", "password")
values (0, true, now(), now(), 'ebs@ebs.com', '$2a$10$w8WPi3QsmLnRl7fTjl6zf.iIY3nE8GinD.tcpJ7JLu8zWHs0APTUy');

-- Params connection to Datacredito
insert into ebs."param" ("id", "description", "value")
values (0, 'URL WSDL Datacredito', 'https://demo-servicesesb.datacredito.com.co:443/wss/dhws3/services/DHServicePlus?wsdl');

insert into ebs."param" ("id", "description", "value")
values (1, 'Usuario OKTA', '2-891502277');

insert into ebs."param" ("id", "description", "value")
values (2, 'Clave OKTA', 'oktaEBS1');

insert into ebs."param" ("id", "description", "value")
values (3, 'Usuario para consulta', '891502277');

insert into ebs."param" ("id", "description", "value")
values (4, 'Clave corta', '58FPE');

commit;