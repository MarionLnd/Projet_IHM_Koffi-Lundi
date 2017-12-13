JFLAGS = -g
JC = javac
J = java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES =	\
				Accueil.java\
				PreAccueil.java\
				TauxOccupationDate.java\
				TauxOccupationPeriode.java\
				TauxNonPresentation.java\
				GalerieInstructions.java\
				Calculs.java\
				ControleurTaux.java\
				ControleurTauxPeriode.java\
				ControleurTauxNonPresentation.java\
				ModeleStatistiques.java\
				ModeleNonPresentation.java\
				Camembert.java\
				Main.java




default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(J) Main

clean:
	$(RM) *.class
