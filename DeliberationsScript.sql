-- usuarios

-- contraseña root V3rY=$tR0nG=P@$$w0rd$

-- exportar a archivo sudo mysqldump  -p Acme-Bay > pruebaX.sql
-- importar este archivo sudo mysql -u root -p < este arcihvo


start transaction;


drop database if exists `Deliberations`;
create database `Deliberations`;
use `Deliberations`;
-- DROP USER 'acme-user';
-- DROP USER 'acme-manager';
-- CREATE USER 'acme-user'@'%' IDENTIFIED BY 'ACME-Us3r-P@ssw0rd';
-- CREATE USER 'acme-manager'@'%' IDENTIFIED BY 'ACME-M@n@ger-6874';
-- privilegios
grant select, insert, update, delete
on `Deliberations`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter,
create temporary tables, lock tables, create view, create routine,
alter routine, execute, trigger, show view
on `Deliberations`.* to 'acme-manager'@'%';



