package rules;

import com.ssa.ed.input.EligibilityDetermination.PlanDetails.SnapPlanData;
import com.ssa.ed.output.PlanInfo;
import com.ssa.ed.output.PlanInfo.PlanApproved;
import com.ssa.ed.output.PlanInfo.PlanDenied;

public class SnapRule {
	public void check() {
		SnapPlanData snapPlanData=new SnapPlanData();
		if(Double.parseDouble(snapPlanData.getFamilyIncome()) <3500.0 && snapPlanData.getIsEmployed().equalsIgnoreCase("n")) {
			PlanInfo planInfo=new PlanInfo();
			planInfo.setPlanName("Snap");
			planInfo.setPlanStatus("AP");
			PlanApproved planApproved=new PlanApproved();
			planApproved.setBenefitAmt("500");
			planApproved.setPlanStartDate("10/7/2019");
			planApproved.setPlanEndDate("10/9/2019");
			planInfo.setPlanApproved(planApproved);
			snapPlanData.setPlanInfo(planInfo);
		}
		
		if(Double.parseDouble(snapPlanData.getFamilyIncome())>3500.0 && snapPlanData.getIsEmployed().equalsIgnoreCase("y")) {
			PlanInfo planInfo=new PlanInfo();
			planInfo.setPlanName("Snap");
			planInfo.setPlanStatus("DN");
			PlanDenied planDenied=new PlanDenied();
			planDenied.setDenialReason("Income Exceeds");
			planInfo.setPlanDenied(planDenied);
			snapPlanData.setPlanInfo(planInfo);
		}
	}
}
