package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Quote implements Serializable{
	@Id
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer quoteNumber;
	private String forecast;
	private Double balio;
	@XmlIDREF
	private Question question;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Apustua> apustuak = new Vector<Apustua>(); 

	public Quote() {
		super();
	}
	
	public Quote(Double balio, String forecast, Question question) {
		this.balio = balio; 
		this.forecast = forecast; 
		this.question=question;
	}
	
	public Quote(Double balio, String forecast) {
		this.balio = balio; 
		this.forecast = forecast; 
	}

	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}
	
	public Double getQuote() {
		return balio;
	}

	public void setQuote(Double balio) {
		this.balio = balio;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public Integer getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(Integer quoteNumber) {
		this.quoteNumber = quoteNumber;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public String toString(){
		return "Forecast: "+forecast+"; Quote: "+Double.toString(balio);
	}
	
	public void addApustua(Apustua a) {
		this.apustuak.add(a);
	}
	
	public void removeApustua(Apustua a) {
		boolean aurkitua = false; 
		int i = 0; 
		while( i<apustuak.size() && !aurkitua) {
			if(apustuak.get(i).getApostuaNumber()==a.getApostuaNumber()) {
				apustuak.remove(i); 
				aurkitua = true; 
			}
			i++; 
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null) {
			Quote q = (Quote)o;
			return this.getQuoteNumber()==q.getQuoteNumber();
		} else {
			return false;
		}
	}
	
	
}
