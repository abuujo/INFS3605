package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.SponsorDAO;
import dto.*;

public class SponsorService {

	public static List<String> getAllSponsorNames() {
		List<Sponsor> all = getAllSponsors();
		List<String> names = new ArrayList<String>();
		
		for(Sponsor sponsor : all) {
			names.add(sponsor.getSponsorName());
		}
		
		Collections.sort(names);
		
		return names;
	}
	
	public static List<Sponsor> getAllSponsors()  {
		List<Sponsor> list = SponsorDAO.selectAll();
		return list;
	}
	
	
	public static Sponsor getSponsorBySponsorId(int sponsorId) {
		return SponsorDAO.selectById(sponsorId);
	}
	
	public static Sponsor getSponsorBySponsorName(String sponsorName) {
		return SponsorDAO.selectBySponsorName(sponsorName);
	}
}