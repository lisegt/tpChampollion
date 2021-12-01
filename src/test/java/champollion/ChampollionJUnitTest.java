package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");			
	}

	@Test
	public void testHeuresPrevues(){
		//5h CM, 10h TD, 10h TP pour UML
		untel.ajouteEnseignement(uml, 5, 10, 10);

		//8h CM, 10h TD, 15h TP pour JAVA
		untel.ajouteEnseignement(java, 8, 10, 15);

		assertEquals(58, untel.heuresPrevues(),"L'enseignant doit avoir 58 h (équivalent TP) de prévues ");
	}

	@Test
	public void testSousService(){
		//5h CM, 10h TD, 10h TP pour UML
		untel.ajouteEnseignement(uml, 5, 10, 10);
		assertTrue(untel.enSousService(),"L'enseignant doit être en sous-service");

		//193h TD pour UML
		untel.ajouteEnseignement(uml, 0, 193, 0);
		assertFalse(untel.enSousService(),"L'enseignant doit être en sous-service");
	}

	@Test
	public void testAPlanifier(){
		//10h TD prévues pour UML
		untel.ajouteEnseignement(uml, 20, 10, 30);

		//1h TD effectuée
		untel.ajouteIntervention(new Intervention(new Date(), 1, 14, TypeIntervention.TD, uml, new Salle("B019", 30)));
		untel.ajouteIntervention(new Intervention(new Date(), 1, 15, TypeIntervention.CM, uml, new Salle("B012", 30)));
		untel.ajouteIntervention(new Intervention(new Date(), 1, 15, TypeIntervention.TP, uml, new Salle("B011", 30)));

		assertEquals(9, untel.resteAPlanifier(uml, TypeIntervention.TD),"Il doit rester 9 h de TD à planifier");
		assertEquals(19, untel.resteAPlanifier(uml, TypeIntervention.CM),"Il doit rester 19 h de CM à planifier");
		assertEquals(29, untel.resteAPlanifier(uml, TypeIntervention.TP),"Il doit rester 29 h de CM à planifier");
	}

	@Test
	public void testAjoutInterventionUEInvalide(){
		untel.ajouteEnseignement(uml, 10, 10, 10);
		try{
			untel.ajouteIntervention(new Intervention(new Date(), 1, 14, TypeIntervention.TD, java, new Salle("B019", 30)));
			fail("Cet appel doit lever une exception");
		} catch (IllegalArgumentException e){
		}
	}

	@Test
	public void testResteAPlanifierUEInvalide(){
		untel.ajouteEnseignement(uml, 10, 10, 10);
		try{
			untel.resteAPlanifier(java, TypeIntervention.TD);
			fail("Cet appel doit lever une exception");
		} catch (IllegalArgumentException e){
		}
	}

	@Test
	public void testServicePrevuInvalide(){
		try{
			ServicePrevu sp = new ServicePrevu(null, uml, 10, 10, 10);
			fail("Cet appel doit lever une exception");
		} catch (NullPointerException e){
		}
	}
}
