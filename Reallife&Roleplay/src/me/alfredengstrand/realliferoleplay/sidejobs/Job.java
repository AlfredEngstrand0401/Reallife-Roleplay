package me.alfredengstrand.realliferoleplay.sidejobs;

public interface Job {
	
	public int getSalary();
	public String getTitle();
	public void update();
	public void setupBossbar();
	public void removeBossbar();
	public float getXP();
	public void addXP(float amount);
	public void removeXP(float amount);
	public void setXP(float amount);
	public void complete();

}
