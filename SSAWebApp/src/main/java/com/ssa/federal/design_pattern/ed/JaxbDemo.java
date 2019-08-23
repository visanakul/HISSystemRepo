package com.ssa.federal.design_pattern.ed;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class JaxbDemo {

	private static final String FILE_PATH = "d:\\ed.xml";

	public static void main(String[] args) {
		EligibilityDetermination ed = new EligibilityDetermination();
		CitizenData citizenData = new CitizenData();
		citizenData.setCaseNum("123");
		citizenData.setDob("20/01/2000");
		citizenData.setFirstName("FN");
		citizenData.setGender("Male");
		citizenData.setLastName("LN");
		citizenData.setPlanSelected("Snap");
		citizenData.setSsn("1234567890");
		PlanDetails planDetails = new PlanDetails();
		SnapPlanData snapPlanData = new SnapPlanData();
		snapPlanData.setFamilyIncome("2000");
		snapPlanData.setIsEmployed("y");

		PlanInfo planInfo = new PlanInfo();
		planInfo.setCaseNum("123");
		PlanApproved planApproved = new PlanApproved();
		planApproved.setBenefitAmt("300");
		planApproved.setPlanStartDate("12/3/2018");
		planApproved.setPlanEndDate("12/5/2020");
		planInfo.setPlanResult(planApproved);
		planInfo.setPlanName("Snap");
		planInfo.setPlanStatus("Active");
		snapPlanData.setPlanInfo(planInfo);
		planDetails.setPlanData(snapPlanData);

		ed.setCitizenData(citizenData);
		ed.setPlanDetails(planDetails);

		System.out.println(ed);
		marshal(ed);
		System.out.println("----------------------------------------------------------");
		unmarshal();

	}

	private static void marshal(EligibilityDetermination ed) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ed.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(ed, System.out);
			marshaller.marshal(ed, new File(JaxbDemo.FILE_PATH));
			System.out.println("XML created...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finish");
		}
	}

	private static void unmarshal() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(EligibilityDetermination.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			EligibilityDetermination ed = (EligibilityDetermination) unmarshaller
					.unmarshal(new File(JaxbDemo.FILE_PATH));
			System.out.println("EligibilityDetermination : " + ed);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finish");
		}
	}

}
