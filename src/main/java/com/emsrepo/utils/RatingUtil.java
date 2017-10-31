package com.emsrepo.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.emsrepo.domain.Comment;

public class RatingUtil {
	public static Map<String, Double> calRates(List<Comment> comments) {
		Map<String, Double> rates = new HashMap<String, Double>(); 
		
		int total = 0;
		int numOfPositive = 0;
		int numOfNeutral = 0;
		int numOfNegative = 0;
		double positiveRate = 0.00;
		double neutralRate = 0.00;
		double negativeRate = 0.00;
		
		for (Iterator<Comment> iterator = comments.iterator(); iterator.hasNext();) {
			Comment comment = iterator.next();
			switch (comment.getRating()) {
			case 1:
				numOfPositive++;
				break;
			case 0:
				numOfNeutral++;
				break;
			case -1:
				numOfNegative++;
				break;
			}
		}
		
		total = numOfPositive + numOfNeutral + numOfNegative;
		
		if (total != 0) {
			positiveRate = (numOfPositive * 1.00 / total) * 100;
			neutralRate = (numOfNeutral * 1.00 / total) * 100;
			negativeRate = 100.00 - positiveRate - neutralRate;
			
			System.out.println("positiveRate:" + positiveRate);
			System.out.println("neutralRate:" + neutralRate);
			System.out.println("negativeRate:" + negativeRate);
			
			rates.put("positiveRate", positiveRate);
			rates.put("neutralRate", neutralRate);
			rates.put("negativeRate", negativeRate);
			
			return rates;
		}
		
		return null;
	}
}
