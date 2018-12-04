public class Insurance {
    private String planName;
    private int amount;
    /* Indicates the number of people using this plan. This may need to be reflected in the requirements log*/
    private int members;

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

    public int getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "planName='" + planName + '\'' +
                ", amount=" + amount +
                '}';
    }
}