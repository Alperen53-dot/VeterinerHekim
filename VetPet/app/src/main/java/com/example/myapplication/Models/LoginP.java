package com.example.myapplication.Models;

public class LoginP {
	private int durum;
	private String mailAdres;
	private boolean trueFalse;
	private String parola;
	private String kadi;
	private String id;
	private String text;

	public void setDurum(int durum){
		this.durum = durum;
	}

	public int getDurum(){
		return durum;
	}

	public void setMailAdres(String mailAdres){
		this.mailAdres = mailAdres;
	}

	public String getMailAdres(){
		return mailAdres;
	}

	public void setTrueFalse(boolean trueFalse){
		this.trueFalse = trueFalse;
	}

	public boolean isTrueFalse(){
		return trueFalse;
	}

	public void setParola(String parola){
		this.parola = parola;
	}

	public String getParola(){
		return parola;
	}

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"LoginP{" + 
			"durum = '" + durum + '\'' + 
			",mailAdres = '" + mailAdres + '\'' + 
			",trueFalse = '" + trueFalse + '\'' + 
			",parola = '" + parola + '\'' + 
			",kadi = '" + kadi + '\'' + 
			",id = '" + id + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
