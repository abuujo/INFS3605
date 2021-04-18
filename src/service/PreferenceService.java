package service;

import dto.*;

public class PreferenceService {

	public static void setPreferenceNames(Preference p) {
		p.setFirstPrefName(SponsorService.getSponsorBySponsorId(p.getFirstPref()).getSponsorName());		
		p.setSecondPrefName(SponsorService.getSponsorBySponsorId(p.getSecondPref()).getSponsorName());
		p.setThirdPrefName(SponsorService.getSponsorBySponsorId(p.getThirdPref()).getSponsorName());
		p.setFourthPrefName(SponsorService.getSponsorBySponsorId(p.getFourthPref()).getSponsorName());
		p.setFifthPrefName(SponsorService.getSponsorBySponsorId(p.getFifthPref()).getSponsorName());
		p.setSixthPrefName(SponsorService.getSponsorBySponsorId(p.getSixthPref()).getSponsorName());
		p.setIPLevel(p.getIPLevel());
	}
}
