package com.cts.mfpe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mfpe.exception.IPTreatmentPackageNotFoundException;
import com.cts.mfpe.exception.SpecialistNotFoundException;
import com.cts.mfpe.model.AilmentCategory;
import com.cts.mfpe.model.IPTreatmentPackage;
import com.cts.mfpe.model.SpecialistDetail;
import com.cts.mfpe.repository.IPTreatmentPackageRepository;
import com.cts.mfpe.repository.SpecialistDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IPTreatmentOfferingServiceImpl implements IPTreatmentOfferingService {

	@Autowired
	IPTreatmentPackageRepository treatmentPackRepository;

	@Autowired
	SpecialistDetailRepository specialistRepository;

	@Override
	public List<IPTreatmentPackage> findAllIPTreatmentPackages() {

		List<IPTreatmentPackage> treatmentPackages = treatmentPackRepository.findAll();
		log.info("[IPTreatmentPackage details:] "+ treatmentPackages);
		return treatmentPackages;
	}

	@Override
	public IPTreatmentPackage findIPTreatmentPackageByName(AilmentCategory ailment, String packageName) throws IPTreatmentPackageNotFoundException {

		IPTreatmentPackage treatmentPackage = treatmentPackRepository.findByName(ailment, packageName)
					.orElseThrow(() -> new IPTreatmentPackageNotFoundException("IP Treatment Package not found"));
		
		log.info("[IPTreatmentPackage ("+packageName+") detail:] "+ treatmentPackage);
		return treatmentPackage;
	}

	@Override
	public List<SpecialistDetail> findAllSpecialists() {

		List<SpecialistDetail> specialists = specialistRepository.findAll();
		log.info("[Specialist details:] " + specialists);
		return specialists;
	}
	@Override
	public List<SpecialistDetail> findSpecialistByExpertise(AilmentCategory areaOfExpertise) {
		List<SpecialistDetail> specialists = specialistRepository.findAll();
		List<SpecialistDetail>  s=new ArrayList<>();
		for(SpecialistDetail sa:specialists) {
			if(sa.getAreaOfExpertise().equals(areaOfExpertise))
			{
				s.add(sa);
			}
		}
		return s;
	}
	
	@Override
	public SpecialistDetail addSpecialists(SpecialistDetail specialist) {
		specialistRepository.save(specialist);
		return specialist;
	}
	
	@Override
	public boolean deleteSpecialists(int specialistId) throws SpecialistNotFoundException {
		// TODO Auto-generated method stub
		SpecialistDetail s=specialistRepository.findById(specialistId).orElse(null);
		if(s!=null)
		{
			specialistRepository.delete(s);
			return true;
		}
		else
		{ 
			throw new SpecialistNotFoundException("Specialistnotfound");
		}
	
		
	}

	@Override
	public IPTreatmentPackage updatePackage(IPTreatmentPackage ipTreatmentPackage) throws IPTreatmentPackageNotFoundException {
		// TODO Auto-generated method stub
		IPTreatmentPackage ipTreatmentPackage2 = treatmentPackRepository.findById(ipTreatmentPackage.getTreatmentPackageId()).orElse(null);
		if(ipTreatmentPackage2!=null) {
			treatmentPackRepository.save(ipTreatmentPackage);
		}
		else {
			log.error("No Treatment Package found with package id : "+ipTreatmentPackage.getTreatmentPackageId());
			throw new IPTreatmentPackageNotFoundException("No Package to update");
		}
		
		return ipTreatmentPackage;
		
	}

}
