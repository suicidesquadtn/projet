package tn.esprit.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;

import tn.esprit.entities.Section;
import tn.esprit.services.GestionSectionLocal;
import tn.esprit.services.gestionSubjectsLocal;
@ManagedBean(name ="chartbean")
@ViewScoped
public class ChartBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

     @EJB
    GestionSectionLocal myService;
	private BarChartModel barchart;
    private Section section;
    private List<Section> sections = new ArrayList<Section>();
    private String input;
    private String input2;
	@EJB
	private gestionSubjectsLocal gsl;
	public BarChartModel getBarchart() {
		return barchart;
	}

	public void setBarchart(BarChartModel barchart) {
		this.barchart = barchart;
	}

    public void  NbrSubjectMonth(){
		barchart = new BarChartModel();
        System.out.println(section.getNom());
    	List<Object[]> listte = gsl.subjectParMois(section);
    	BarChartSeries serie = new BarChartSeries();
    	long[] tab = new long[] { 0,0,0,0,0,0,0,0,0,0,0,0,0 };
    	for (Object[] objects : listte) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
			System.out.println(objects[2]);
			
	    	for (int i = 1; i < 13; i++) {
				if((Integer)objects[2] != 0){
					tab[(Integer)objects[2]] = (Long)objects[0];
				}
	    	}
    	}
    	for (int i = 1; i < 13; i++) {
			serie.set(i, tab[i]);
		}
    	barchart.setTitle("Number of subject by month by section");
    	barchart.addSeries(serie);
    	Axis xAxis = barchart.getAxis(AxisType.X) ;
    	xAxis.setLabel("Mois");
    	Axis yAxis = barchart.getAxis(AxisType.Y) ;
    	yAxis.setLabel("number");
    	
    	System.out.println();
    }
    @PostConstruct
	public void init(){ 
		System.out.println("aaaaaaaaaman ");
	    sections=myService.getAll();
        section=sections.get(0);
        NbrSubjectMonth();
	   
	    //section =new Section();
	 
	}
    public void AfficherSection(){
        System.out.println(input2);
    }
	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}
}
