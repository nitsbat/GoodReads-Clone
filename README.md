# GoodReads-Clone
Clone repository of GoodReads. This project doesn't include the population of cassandra DB. To implement that we have another repo  - https://github.com/nitsbat/bookreads

This project is said too highly scalable and available and very good in performance. 
The site is meant to be very fast despite the fact that we will be using large amounts of book data.

### Note 
This is the main repository for building the website. The Data initialisation repository can be found here: https://github.com/nitsbat/bookreads

The data dump for both is taken from the open library website i.e - https://openlibrary.org/developers/dumps

As we will be populating our database with these records, there will be millions of book flowing through the database. Will do it parallely but then also it will take
take a lot of time and hence we will be using test files for both books and authors data dump.

##### Authors data will be present here : https://github.com/nitsbat/bookreads/blob/master/goodreads/src/main/resources/test-authors
##### Books data will be present here : https://github.com/nitsbat/bookreads/blob/master/goodreads/src/main/resources/test-works

List of books used in this project are : 

"Le verbe en action. grammaire contrastive des temps verbaux (français, allemand, anglais, espagnol)"

"Je marche sous un ciel de traîne"

"AMIR"

"Antoine Pevsner - Dans Les Collections Du Mnam"

"Liberez-vous de votre passé"

"Réussir le commentaire de texte à l'examen profressionnel de rédacteur-chef"

"Sardaigne 2002"

"Manuel méthodique de préparation. Examens et concours administratifs, concours d'entrée en Institut de formation des cadres de santé"

"L'entrée en IFMK dans la poche"

"Réussir le DPAP 2002"

"Des icônes pour prier"

"Fragmentarium"

"L'Annuel Des Arts 1998"

"Thés, infusions, santé"

"Manuel de pilotage de montgolfières"

"Le Tour du monde en famille"

"La révolution"

"Noms de lieux en Bourbonnais"

"Relazione sul Congresso Tipografico de Feltre letta in Assemblea Generale della Societa dei Compositori-tipografi di Firenze"

"Arts martiaux tome 2"

"Les frères Goncourt"

"L'espace rural au moyen age"

"La vie d'Ismaïl Férik pacha"

"De jolies fesses"

"La nouvelle femme de 50 ans"

"Le temps des cendres tome II"

"Cardinal de Richelieu"

"Piège à Papa"

"Les vertus curatives du magnésium"

"Merritt intégrale 2"

"L'Utopistique ou les choix politiques du XXIe siècle"

"Peregrines/2-4k7 conseille 23 e/ttc"

"Spooksville - The Wicked Cat"

"L'Ode... hissée !"

"Fiscalité des associations"

"An approach to general systems theory"

"La pensée créatrice"

"Traitement de données avec Excel"

"Le Budget rÃ©inventÃ©"

"A shelter sketchbook"

"Rideaux au crochet"

"Images sur deux roues"

"Le pastel"

"Assembleur sur HP 48"

"Prisons de femmes en europe"

"The Ranch House"

"Comme un echiquier ferme (bilingue hongrois)"

"L'Anarchitecte"

"Les écrits de Martha Harris et Esther Bick = collected papers of Martha Harris and Esther Bick"

"L'élevage des tortues terrestres"

"Construire la ville complexe ?"

"Maigrir facilement en mangeant santé"

"The Ion Miracle"


### You can see the real version of website which is deployed in heroku here - https://book-webread.herokuapp.com/
