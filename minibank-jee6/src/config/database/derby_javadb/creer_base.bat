REM lancer si besoin le service mysql (net start mysql)
set DERBY_HOME=C:\Prog\glassfish4\javadb
REM (en local direct sans serveur) : set DB_URL=jdbc:derby:%DERBY_HOME%\minibank_db_ex2;create=true
set DB_URL=jdbc:derby://localhost:1527/minibank_db_ex2;create=true

cd /d %~dp0

java -cp "%DERBY_HOME%\lib\derby.jar;%DERBY_HOME%\lib\derbyclient.jar;%DERBY_HOME%\lib\derbytools.jar"  -Dij.database=%DB_URL%  org.apache.derby.tools.ij  create_minibank_db.sql
   
pause