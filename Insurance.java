public class Insurance {
    private String planName;
    private int amount;

    public Insurance(String planName, int amount){
        this.planName = planName;
        this.amount = amount;
        //members++;
    }

    public int getAmount() {
        return amount;
    }

    public String getPlanName() {
        return planName;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
    
    @Override
    public String toString() {
        return "Insurance{" +
                "planName='" + planName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
