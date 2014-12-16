#!/bin/bash


echo "Introducir contraseña root"
echo  ' copia esta si es la de dp --> V3rY=$tR0nG=P@$$w0rd$' 

mysql -u root -p < DeliberationsScript.sql
cd Deliberations

mvn clean install

echo "borrando Deliberations (si estaba) de Tomcat"
rm -r -f /var/lib/tomcat7/webapps/Deliberations
rm /var/lib/tomcat7/webapps/Deliberations.war

echo "copiando el war"
cp target/Deliberations-1.80.war /var/lib/tomcat7/webapps/Deliberations.war

echo "reiniciando tomcat"
service tomcat7 restart


echo "debes de tener census y auth funcionando para que esto funcione"

echo "debes de esperar un poco a que tomcat reinicie y monte todo los wars de nuevo, recuerda que tienes que tener funcionando census y auth para que este subsitema funcione"

