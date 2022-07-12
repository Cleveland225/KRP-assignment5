import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import view.ViewComputer;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

public class final1 {
	public static void main(String[] args) throws Exception {
		OWLOntologyManager manager1 = OWLManager.createOWLOntologyManager();
		OWLOntologyManager manager2 = OWLManager.createOWLOntologyManager();
		OWLOntologyManager manager3 = OWLManager.createOWLOntologyManager();
		OWLOntologyManager manager4 = OWLManager.createOWLOntologyManager();
		
		
		//use the alternative data.
		File file1 = new File("E:/java/eclipse-workspace/owlapi-krp/ncit/ncit_20190429.owl");
		File file2 = new File("E:/java/eclipse-workspace/owlapi-krp/ncit/ncit_20200427.owl");
		File file3 = new File("E:/java/eclipse-workspace/owlapi-krp/ncit/ncit_20210427.owl");
		File file4 = new File("E:/java/eclipse-workspace/owlapi-krp/ncit/ncit_20220426.owl");
		
		OWLOntology o1 = manager1.loadOntologyFromOntologyDocument(file1);
		OWLOntology o2 = manager2.loadOntologyFromOntologyDocument(file2);
		OWLOntology o3 = manager3.loadOntologyFromOntologyDocument(file3);
		OWLOntology o4 = manager4.loadOntologyFromOntologyDocument(file4);
		
		System.out.println("00000");
		
		//task2
		compute_diff(o1, o2, "E:/java/eclipse-workspace/owlapi-krp/out1/20190429-20200427-witnesses.owl");
		compute_diff(o2, o1, "E:/java/eclipse-workspace/owlapi-krp/out1/20200427-20190429-witnesses.owl");
		compute_diff(o2, o3, "E:/java/eclipse-workspace/owlapi-krp/out1/20200427-20210427-witnesses.owl");
		compute_diff(o3, o4, "E:/java/eclipse-workspace/owlapi-krp/out1/20210427-20220426-witnesses.owl");
		compute_diff(o4, o3, "E:/java/eclipse-workspace/owlapi-krp/out1/20220426-20210427-witnesses.owl");
		
		System.out.println("11111");
		
		//task3
		view(o1,o2,"E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp12.owl");
		view(o2,o1,"E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp21.owl");
		view(o2,o3,"E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp23.owl");
		view(o3,o4,"E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp34.owl");
		view(o4,o3,"E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp43.owl");
		System.out.println("22222");
		
		File file12 = new File("E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp12.owl");
		File file21 = new File("E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp21.owl");
		File file23 = new File("E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp23.owl");
		File file34 = new File("E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp34.owl");
		File file43 = new File("E:/java/eclipse-workspace/owlapi-krp/tmp1/tmp43.owl");
		
		System.out.println("33333");
		
		OWLOntologyManager manager12 = OWLManager.createOWLOntologyManager();
		OWLOntologyManager manager21 = OWLManager.createOWLOntologyManager();
		OWLOntologyManager manager23 = OWLManager.createOWLOntologyManager();
		OWLOntologyManager manager34 = OWLManager.createOWLOntologyManager();
		OWLOntologyManager manager43 = OWLManager.createOWLOntologyManager();
		
		System.out.println("44444");
		
		OWLOntology o12 = manager12.loadOntologyFromOntologyDocument(file12);
		OWLOntology o21 = manager21.loadOntologyFromOntologyDocument(file21);
		OWLOntology o23 = manager23.loadOntologyFromOntologyDocument(file23);
		OWLOntology o34 = manager34.loadOntologyFromOntologyDocument(file34);
		OWLOntology o43 = manager43.loadOntologyFromOntologyDocument(file43);
		
		System.out.println("55555");
		
		compute_diff(o1, o12, "E:/java/eclipse-workspace/owlapi-krp/out2/20190429-20200427-view-witnesses.owl");
		compute_diff(o2, o21, "E:/java/eclipse-workspace/owlapi-krp/out2/20200427-20190429-view-witnesses.owl");
		compute_diff(o2, o23, "E:/java/eclipse-workspace/owlapi-krp/out2/20200427-20210427-view-witnesses.owl");
		compute_diff(o3, o34, "E:/java/eclipse-workspace/owlapi-krp/out2/20210427-20220426-view-witnesses.owl");
		compute_diff(o4, o43, "E:/java/eclipse-workspace/owlapi-krp/out2/20220426-20210427-view-witnesses.owl");
		
		System.out.println("66666");
		
		
	}
	
	protected static void compute_diff(OWLOntology ontology1, OWLOntology ontology2, String path) throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology o = manager.createOntology();
		OWLReasonerFactory rf1 = new ReasonerFactory();
		OWLReasoner r1 = rf1.createReasoner(ontology1);
		
		int t = 0;
		
		for(OWLAxiom axiom : ontology2.getAxioms())
		{
			System.out.println("t = " + t);
			if(r1.isEntailed(axiom) == false)
			{
				o.add(axiom);
			}
			t+=1;
		}
		
		t = 0;
//		System.out.println("bababab");
		
		File out1 = new File(path);
		manager.saveOntology(o, new OWLXMLDocumentFormat(), new FileOutputStream(out1));
	}

	protected static void view(OWLOntology ontology1, OWLOntology ontology2, String savepath) throws Exception {
		Set<OWLClass> tmpC1 = ontology1.getClassesInSignature();
		Set<OWLClass> tmpC2 = ontology2.getClassesInSignature();
		tmpC1.retainAll(tmpC2);
		
		Set<OWLObjectProperty> tmpR1 = ontology1.getObjectPropertiesInSignature();
		Set<OWLObjectProperty> tmpR2 = ontology2.getObjectPropertiesInSignature();
		tmpR1.retainAll(tmpR2);
		
		System.out.println("aaaaaa");
		
		ViewComputer.computeViews(tmpC1, tmpR1, ontology2, savepath);
		
	}
}
