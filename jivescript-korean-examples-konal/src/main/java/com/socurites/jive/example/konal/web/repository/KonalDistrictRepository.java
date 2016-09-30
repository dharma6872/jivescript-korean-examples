package com.socurites.jive.example.konal.web.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.socurites.jive.example.konal.web.domain.KonalDistrict;
import com.socurites.jive.util.file.JiveFileUtils;

/**
 * 날씨 조회를 위한 지역정보 도메인 객체 조회 Repository.
 * 
 * @author socurites
 *
 */
@Repository
public class KonalDistrictRepository {
	/**
	 * 날씨 검색이 가능한 표진 지역명 객체 리스트.
	 */
	private static List<KonalDistrict> districts = new ArrayList<KonalDistrict>();
	
	static {
		File districtFile  = new File(JiveFileUtils.class.getClassLoader().getResource("district/output.txt").getFile());
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(districtFile);
			
			String[] tokens = null;
			KonalDistrict district = null;
			while ( scanner.hasNextLine() ) {
				tokens = scanner.nextLine().split("\t");
				district = new KonalDistrict();
				district.setSido(tokens[0]);
				district.setSigungu(tokens[1]);
				district.setTown(tokens[2]);
				
				districts.add(district);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
	}
	
	/**
	 * 입력파라미터를 기준으로 날씨조회가 가능한 표준 지역명 객체를 리턴.
	 * 
	 * @param sigungu
	 * @param sido
	 * @param town
	 * @return
	 */
	public KonalDistrict getStandardDistrcit(String sido, String sigungu, String town) {
		if ( !StringUtils.isBlank(sido) && !StringUtils.isBlank(sigungu) && !StringUtils.isBlank(town) ) {			// 시도 시군구 읍면동
			for ( KonalDistrict district : districts ) {
				if ( district.getSido().equals(sido) 
				  && district.getSigungu().equals(sigungu)
				  && district.getTown().equals(town)
						) {
					return district;
				}
				
				if ( district.getSido().equals(sido) 
				  && district.getSigungu().equals(sigungu)
						) {
					return district;
				}
				
				if ( district.getSido().equals(sido) ) {
					return district;
				}
			}
		} else if ( !StringUtils.isBlank(sido) && !StringUtils.isBlank(sigungu) && StringUtils.isBlank(town) ) {	// 시도 시군구
			for ( KonalDistrict district : districts ) {
				if ( district.getSido().equals(sido) 
				  && district.getSigungu().equals(sigungu)
						) {
					return district;
				}
				
				if ( district.getSido().equals(sido) ) {
					return district;
				}
			}
		} else if ( !StringUtils.isBlank(sido) && StringUtils.isBlank(sigungu) && StringUtils.isBlank(town) ) {		// 시도
			for ( KonalDistrict district : districts ) {
				if ( district.getSido().equals(sido) ) {															// 매칭된 첫번째 지역을 리턴
					return district;
				}
			}
		} else if ( StringUtils.isBlank(sido) && !StringUtils.isBlank(sigungu) && StringUtils.isBlank(town) ) {		// 시군구
			for ( KonalDistrict district : districts ) {
				if ( district.getSigungu().equals(sigungu) ) {															// 매칭된 첫번째 지역을 리턴
					return district;
				}
			}
			
		} else if ( StringUtils.isBlank(sido) && !StringUtils.isBlank(sigungu) && !StringUtils.isBlank(town) ) {	// 시군구 읍면동
			for ( KonalDistrict district : districts ) {
				if ( district.getSigungu().equals(sigungu)
				  && district.getTown().equals(town)
						) {
					return district;
				}
			}
		}
		
		
		return null;
	}

	
}
