REM lancer si besoin le service mysql (net start mysql)
set MYSQL_HOME=C:\Prog\DB\MySql5
cd /d %~dp0
"%MYSQL_HOME%\bin\mysql"  -u root -p < insert_minibank_db.sql
pause