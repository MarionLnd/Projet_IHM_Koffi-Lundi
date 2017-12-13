JFLAGS = -g
JC = javac
J = java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES =	\
				Accueil.java\
				Client.java\
				ControleurClient.java\
				ControleurReference.java\
				GalerieInstructions.java\
				ModeleChambre.java\
				ModeleRecherche.java\
				PreAccueil.java\
				RechercheClient.java\
				RechercheReference.java\
				Reservation.java\
				Main.java


default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(J) Main

clean:
	$(RM) *.class
