package project04;

import java.text.DecimalFormat;
import java.util.Comparator;



public class RiskFactor {
		
	public static final Comparator <RiskFactor> HDComparator = new  Comparator<RiskFactor>(){

			@Override
			public int compare(RiskFactor thisObj, RiskFactor otherObj) {
				// TODO Auto-generated method stub
				return thisObj.hdDeathRate > otherObj.hdDeathRate ? 1 :-1;
			}
		
		};
		
		public static final Comparator <RiskFactor> MVComparator = new  Comparator<RiskFactor>(){

			@Override
			public int compare(RiskFactor thisObj, RiskFactor otherObj) {
				// TODO Auto-generated method stub
				return thisObj.mvDeathRate > otherObj.mvDeathRate ? 1 :-1;
			}
		
		};
		public static final Comparator <RiskFactor> TBComparator = new  Comparator<RiskFactor>(){

			@Override
			public int compare(RiskFactor thisObj, RiskFactor otherObj) {
				// TODO Auto-generated method stub
				return thisObj.teenBirthRate > otherObj.teenBirthRate ? 1 :-1;
			}
		
		};
		public static final Comparator <RiskFactor> ASComparator = new  Comparator<RiskFactor>(){

			@Override
			public int compare(RiskFactor thisObj, RiskFactor otherObj) {
				// TODO Auto-generated method stub
				return thisObj.adultSmoking > otherObj.adultSmoking ? 1 :-1;
			}
		
		};
		public static final Comparator <RiskFactor> AOComparator = new  Comparator<RiskFactor>(){

			@Override
			public int compare(RiskFactor thisObj, RiskFactor otherObj) {
				// TODO Auto-generated method stub
				return thisObj.adultObsesity > otherObj.adultObsesity ? 1 :-1;
			}
		
		};
            private String state;
            private float hdDeathRate,mvDeathRate,teenBirthRate,adultSmoking,adultObsesity;
            public static final DecimalFormat dFormat = new DecimalFormat("###.##");
            
			public RiskFactor(String state, float hdDeathRate,
					float mvDeathRate, float teenBirthRate, float adultSmoking,
					float adultObsesity) 
			{
				super();
				this.state = state;
				this.hdDeathRate = hdDeathRate;
				this.mvDeathRate = mvDeathRate;
				this.teenBirthRate = teenBirthRate;
				this.adultSmoking = adultSmoking;
				this.adultObsesity = adultObsesity;
			}
			public String getState() {
				return state;
			}
			public void setState(String state) {
				this.state = state;
			}
			public float getHdDeathRate() {
				return hdDeathRate;
			}
			public void setHdDeathRate(float hdDeathRate) {
				this.hdDeathRate = hdDeathRate;
			}
			public float getMvDeathRate() {
				return mvDeathRate;
			}
			public void setMvDeathRate(float mvDeathRate) {
				this.mvDeathRate = mvDeathRate;
			}
			public float getTeenBirthRate() {
				return teenBirthRate;
			}
			public void setTeenBirthRate(float teenBirthRate) {
				this.teenBirthRate = teenBirthRate;
			}
			public float getAdultSmoking() {
				return adultSmoking;
			}
			public void setAdultSmoking(float adultSmoking) {
				this.adultSmoking = adultSmoking;
			}
			public float getAdultObsesity() {
				return adultObsesity;
			}
			public void setAdultObsesity(float adultObsesity) {
				this.adultObsesity = adultObsesity;
			}
            
			@Override
			public String toString()
			{
				return  "state " +this.state +
						" Heart_Diease_DeathRate :" +dFormat.format(this.hdDeathRate)+
						
						" Motor_Vehicle_DeathRate :" +dFormat.format(this.mvDeathRate)+
						" Teen-Birth_Rate : " +dFormat.format(this.teenBirthRate) + 
						 " Adult_Smoking :" +dFormat.format(this.adultSmoking)+
						
						" Adult_Obsesity :" +dFormat.format(this.adultObsesity);
			}
            
}
