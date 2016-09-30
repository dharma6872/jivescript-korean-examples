package com.socurites.jive.example.konal.web.domain;

/**
 * 날씨 조회를 위한 지역정보 도메인 객체.
 * 
 * @author socurites
 *
 */
public class KonalDistrict {
	/** 시도. */
	private String sido;
	
	/** 시군구. */
	private String sigungu;
	
	/** 읍면동. */
	private String town;

	/**
	 * @return the sido
	 */
	public String getSido() {
		return sido;
	}

	/**
	 * @param sido the sido to set
	 */
	public void setSido(String sido) {
		this.sido = sido;
	}

	/**
	 * @return the sigungu
	 */
	public String getSigungu() {
		return sigungu;
	}

	/**
	 * @param sigungu the sigungu to set
	 */
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if ( obj == null ) {
			return false;
		} else if ( this == obj ) {
			return true;
		} else if ( obj instanceof KonalDistrict ) {
			KonalDistrict other = (KonalDistrict)obj;
			
			if ( this.sido.equals(other.sido) && this.sigungu.equals(other.sigungu) && this.town.equals(other.town) ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.sido + " " + this.sigungu + " " + this.town;
	}
	
	
}
