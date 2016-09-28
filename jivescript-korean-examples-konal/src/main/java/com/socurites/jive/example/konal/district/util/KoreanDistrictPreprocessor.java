package com.socurites.jive.example.konal.district.util;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 한국 시도/시군구/읍면동 전처리
 * SKP 날씨 API에 사용할 수 있는 포맷으로 데이터 생성
 * https://developers.skplanetx.com/apidoc/kor/weather/
 * 
 * @author socurites
 *
 */
public class KoreanDistrictPreprocessor {
	public static void main(String[] args) {
		KoreanDistrictPreprocessor runner = new KoreanDistrictPreprocessor();
		
		runner.run();
	}

	private void run() {
		Set<String> sidos = new HashSet<String>();
		Set<String> sigungus = new HashSet<String>();
		Set<String> districts = new HashSet<String>();
		
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("district/input.txt");
		
		Scanner scanner = new Scanner(inputStream);
		String line = null;
		String currentSiGunGu = null;
		while ( scanner.hasNextLine() ) {
			line = scanner.nextLine();
			
			String[] tokens = StringUtils.split(line);
			
			if ( tokens.length == 5 && tokens[0].equals("경기도") && tokens[2].endsWith("시") ) {
				currentSiGunGu = tokens[2];
			}
			
			if ( tokens.length >= 6 ) { 
				if ( tokens[0].equals("경기도") ) {
					sidos.add("경기");
					sigungus.add(currentSiGunGu + " " + tokens[2]);
					districts.add(tokens[4].replaceAll("\\d",""));
//					System.out.println("경기" + "\t" + currentSiGunGu + " " + tokens[2] + "\t" + tokens[4]);
				} else {
					sidos.add(translateSido(tokens[0]));
					sigungus.add(tokens[2]);	
					districts.add(tokens[4].replaceAll("\\d",""));
//					System.out.println(translateSido(tokens[0]) + "\t" + tokens[2] + "\t" + tokens[4]);
					
				}
			}
		}
		
		scanner.close();
		
		for ( String item : sidos ) {
			System.out.println("^" + item + "|");
		}
		
		System.out.println("====");
		
		for ( String item : sigungus ) {
			System.out.println("^" + item + "|");
		}
		
		System.out.println("====");
		
		for ( String item : districts ) {
			System.out.println("^" + item + "|");
		}
		
	}
	
	private String translateSido(String sido) {
		if ( "제주특별자치도".equals(sido) ) {
			return "제주";
		} else if ( "강원도".equals(sido) || sido.endsWith("시") ) {
			return sido.substring(0, 2);
		} else if ( 4 == sido.length() ) {
			return sido.substring(0, 1) + sido.substring(2, 3);
		} else {
			throw new RuntimeException("unsupported sidi");
		}
	}
}
