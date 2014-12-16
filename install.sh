#!/bin/bash

echo "Introducir contraseña root de mysql  copia esta si es la de dp --> V3rY=$tR0nG=P@$$w0rd$"
mysql -u root -p < DeliberationsScript.sql
cd Deliberations

mvn clean install

echo "borrando Deliberations (si estaba) de Tomcat"
rm -r -f /var/lib/tomcat7/webapps/Deliberations
rm /var/lib/tomcat/webapps/Deliberations.war

echo "copiando el war"
cp target/Deliberations-1.80.war /var/lib/tomcat7/webapps/Deliberations.war

echo "reiniciando tomcat"
service tomcat restart

echo "debes de tener census y auth funcionando para que esto funcione"
