package misBeans;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import misClases.*;
import misDaos.DAOFactory;
import misDaos.JPADAOFactory;

public class EstadisticasBean {

	private PieChartModel pieModel1;
	  
	private BarChartModel animatedModel2;
	
	int max;
	
	private JPADAOFactory jpaFactory = (JPADAOFactory) DAOFactory.getDAOFactory(DAOFactory.JPA); //Se pide el Factory de JPA	 
    List<Usuario> usuarios;
    
	@PostConstruct
    public void init() {
		createPieModels();
        createAnimatedModels();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
      
    private void createPieModels() {
        createPieModel1();
    }
 
    private void createPieModel1() {
    	usuarios = jpaFactory.getUsuariosDAO().getAll();
        pieModel1 = new PieChartModel();
              
        int cantM = 0;
        int cantF = 0;
        
        for (Usuario u : usuarios){
        	if(u.getSexo().equals("M"))
        		cantM++;
        	else if(u.getSexo().equals("F"))
        		cantF++;
        }
        
        pieModel1.set("Masculinos", cantM);
	    pieModel1.set("Femeninos", cantF);
	   	     
	    pieModel1.setTitle("Usuarios");
	    pieModel1.setLegendPosition("w");
	}
	 	
    private void createAnimatedModels() {    
    	Calendar cal = Calendar.getInstance();
    	usuarios = jpaFactory.getUsuariosDAO().getByYear(cal.get(Calendar.YEAR));
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Usuarios en el ultimo mes");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(max);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        Calendar cal = Calendar.getInstance();
        
        ChartSeries masculino = new ChartSeries();
        ChartSeries femenino = new ChartSeries();
        
        int[] cantM = new int[12];
        int[] cantF = new int[12];
        
        for (Usuario u : usuarios){
        	cal.setTime(u.getDate());
        	if (u.getSexo().equals("M")){
        		cantM[cal.get(Calendar.MONTH)]++;
        	}
        	else if (u.getSexo().equals("F")){
        		cantF[cal.get(Calendar.MONTH)]++;
        	}
        }
        
        max = 0;
        
        for(int i=0;i<12;i++){
        	if(cantM[i] > max)
        		max = cantM[i];
        	if(cantF[i] > max)
        		max = cantF[i];
        	masculino.set(i+1, cantM[i]);
        	femenino.set(i+1, cantF[i]);
        }
        
        masculino.setLabel("Masculino");
        femenino.setLabel("Femenino");
                
        model.addSeries(masculino);
        model.addSeries(femenino);
         
        return model;
    }
     
}
