FlySmart
========

per configurare il logger entrare nelle proprietà del progetto -> java build path -> Libraries

Add jars -> aggiungere i due file jar contenuti in src/lib 

Add class folder -> aggiungere l'intera cartella src/config

MongoDB
========
Scaricare mongo db da http://www.mongodb.org/downloads

Posizionarlo in una cartella vicino al progetto ad esempio se il progetto è in documenti/FlySmart posizionare il db in documenti/mongodb

Creare una cartella data in FlySmart/FlySmart (es: FlySmart/FlySmart/data)

Per avviare mongo aprire la shell e posizionarsi in documenti/mongodb digitare il comando: bin/mongod --dbpath ../FlySmart/FlySmart/data

il db dovrebbe essere in ascolto

Aprire eclipse e carcare la cartella lib

sul file: mongo-java-driver-2.9.3.jar tasto dx->build path->add to build path

sul file: morphia-0.99.jar tasto dx->build path->add to build path

testare l'app

ogni volta bisogna avviare il server MonogDB prima di testare l'applicaizone: bin/mongod --dbpath ../FlySmart/FlySmart/data

per terminarlo ctrl+c nella shell

JUnit
=======
Per usare Junit tasto dx sul progetto -> properties -> Java Build Path -> Libraries -> Add Library -> JUnit -> JUnit4

http://www.vogella.com/articles/JUnit/article.html
