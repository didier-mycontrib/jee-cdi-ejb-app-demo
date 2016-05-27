REM lancer si besoin le service mysql (net start mysql)
set MYSQL_HOME=C:\Prog\DB\MySql5
cd /d %~dp0
REM mot de passe souvent root ou formation ou rien
"%MYSQL_HOME%\bin\mysql"  -u root -p < create_minibank_db.sql
pause