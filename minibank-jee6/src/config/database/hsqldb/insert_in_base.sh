#mysql -u root -p < insert_minibank_db.sql
#sleep 5
java -cp /home/formation/.m2/repository/org/hsqldb/hsqldb/2.3.2/hsqldb-2.3.2.jar org.hsqldb.util.DatabaseManager
# attention:  jdbc:hsqldb:file:devise_db   (url en file: relative et donc au cas par cas selon contexte)