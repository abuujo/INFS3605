package service;

import dto.*;
import util.Helper;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import dao.CourseDAO;
import dao.EnrolmentDAO;
import dao.StudentDAO;

public class EnrolmentService {
	
	public static List<Enrolment> getAll() {
		return EnrolmentDAO.selectAll();
	}
	
	public static List<Enrolment> getAllCoops() {
		List<Enrolment> list = new ArrayList<Enrolment>();
		List<Enrolment> all = getAll();
		
		for(Enrolment e : all) {
			if(StudentDAO.selectById(e.getStudentId()).getType().equals("Co-op")) {
				list.add(e);
			}
		}
		return list;
	}

	public static void setCourseCode(List<Enrolment> list)  {
		for(Enrolment e : list) {
			Course c = CourseDAO.selectById(e.getCourseId());
			if( c != null) {
				e.setCourseCode(c.getCourseCode());
			} else {
				System.out.println("Course does not exists!");
			}
		}
	}
	
	public static List<Enrolment> getEnrolmentsBySemYear(String semYear) {
		return EnrolmentDAO.selectBySemYear(semYear);
	}
	
	public static List<String> getCoursesBySemYear(String semYear) {
		List<Enrolment> all = getEnrolmentsBySemYear(semYear);
		List<String> courses = new ArrayList<String>();
		
		for(Enrolment e : all) {
			Course c = CourseDAO.selectById(e.getCourseId());
			if(!courses.contains(c.getCourseCode())) {
				courses.add(c.getCourseCode());
			}
		}
		return courses;
	}
	
	public static Map<String, List<Map<String, Integer>>> getFilteredResults(List<Enrolment> results) {
		Map<String, List<Map<String,Integer>>> outer = new HashMap<String, List<Map<String,Integer>>>();
		List<Map<String,Integer>> list = null;
	
		for(Enrolment e : results) {	
			Map<String,Integer> inner = new HashMap<String,Integer>();
			inner.put(e.getCourseCode(), e.getMark());
			
			if(outer.containsKey(e.getzId())) {
				list.add(inner);
				
			} else {
				list = new ArrayList<Map<String,Integer>>();
				list.add(inner);
				
			}
			outer.put(e.getzId(), list);
		}
		return outer;
	}
	
	public static Map<String, Double> setAverage(Map<String, List<Map<String,Integer>>> filtered) {
		Map<String, Double> avgs = new HashMap<String, Double>();
		for(String zId : filtered.keySet()) {
			int termAvg = 0;
			int i = 0;
			//System.out.println("zID: "+zId +" ");
			List<Map<String,Integer>> list = filtered.get(zId);
			for(Map<String,Integer> inner : list) {
				for(String code : inner.keySet()) {
					i++;
					termAvg += inner.get(code);
					//System.out.println("course: "+code);
					//System.out.println("Mark: "+inner.get(code));
				}
			}
			//System.out.println("Term Avg: "+ Helper.round((double)termAvg/(double)i, 2));
			avgs.put(zId, (Helper.round((double)termAvg/(double)i, 2)));
		}
		return avgs;
	}
	
	public static Map<String, Integer> setStudentId(Map<String, List<Map<String,Integer>>> filtered) {
		Map<String, Integer> ids = new HashMap<String, Integer>();
		for(String zId : filtered.keySet()) {
			int termAvg = 0;
			int i = 0;
			//System.out.println("zID: "+zId +" ");
			List<Map<String,Integer>> list = filtered.get(zId);
			for(Map<String,Integer> inner : list) {
				for(String code : inner.keySet()) {
					i++;
					termAvg += inner.get(code);
					//System.out.println("course: "+code);
					//System.out.println("Mark: "+inner.get(code));
				}
			}
			//System.out.println("Term Avg: "+ Helper.round((double)termAvg/(double)i, 2));
			ids.put(zId, StudentDAO.selectByZId(zId).getStudentId());
		}
		return ids;
	}
	
	public static List<String> getSemYearList() {
		List<Enrolment> all = getAll();
		List<String> list = new ArrayList<String>();
		
		for(Enrolment e : all) {
			if(!list.contains(e.getSemYear())) {
				list.add(e.getSemYear());
			}
		}
		Collections.sort(list);
		return list;
	}
	
	public static void setzId(List<Enrolment> list)  {
		for(Enrolment e : list) {
			Student s = StudentDAO.selectById(e.getStudentId());
			e.setzId(s.getzId());
		}
	}
	
	
	public static List<Enrolment> getFLgrades() {
		List<Enrolment> all = getAllCoops();
		List<Enrolment> result = new ArrayList<Enrolment>();
		for(Enrolment e : all) {
			if(e.getMark() >= 0 && e.getMark() < 50) {
				result.add(e);
			}
		}
		return result;
	}
	
	public static List<Enrolment> getPSgrades() {
		List<Enrolment> all = getAllCoops();
		List<Enrolment> result = new ArrayList<Enrolment>();
		for(Enrolment e : all) {
			if(e.getMark() >= 50 && e.getMark() < 65) {
				result.add(e);
			}
		}
		return result;
	}	
	
	public static List<Enrolment> getCRgrades() {
		List<Enrolment> all = getAllCoops();
		List<Enrolment> result = new ArrayList<Enrolment>();
		for(Enrolment e : all) {
			if(e.getMark() >= 65 && e.getMark() < 75) {
				result.add(e);
			}
		}
		return result;
	}	
	
	public static List<Enrolment> getDNgrades() {
		List<Enrolment> all = getAllCoops();
		List<Enrolment> result = new ArrayList<Enrolment>();
		for(Enrolment e : all) {
			if(e.getMark() >= 75 && e.getMark() < 85) {
				result.add(e);
			}
		}
		return result;
	}	
	
	public static List<Enrolment> getHDgrades() {
		List<Enrolment> all = getAllCoops();
		List<Enrolment> result = new ArrayList<Enrolment>();
		for(Enrolment e : all) {
			if(e.getMark() >= 85 && e.getMark() <= 100) {
				result.add(e);
			}
		}
		return result;
	}	
	
	// Sort map by its values
	public static Map<String, Double> sortByValue(Map<String, Double> unsortMap) {

	        // 1. Convert Map to List of Map
	        List<Map.Entry<String, Double>> list =
	                new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
	            public int compare(Map.Entry<String, Double> o1,
	                               Map.Entry<String, Double> o2) {
	                return (o2.getValue()).compareTo(o1.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
	        for (Map.Entry<String, Double> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        /*
	        //classic iterator example
	        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
	            Map.Entry<String, Integer> entry = it.next();
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }*/


	        return sortedMap;
	    }
}
