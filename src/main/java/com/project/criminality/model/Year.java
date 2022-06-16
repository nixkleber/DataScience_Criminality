package com.project.criminality.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Year {

	@Id
	private int year;
	private int cases;
	private int attempts;
	private double clearancerate;
	private int suspects;
	private int sunder6;
	private int s6to8;
	private int s8to10;
	private int s10to12;
	private int s12to14;
	private int s14to16;
	private int s16to18;
	private int s18to21;
	private int s21to23;
	private int s23to25;
	private int s25to30;
	private int s30to40;
	private int s40to50;
	private int s50to60;
	private int s60plus;
}
