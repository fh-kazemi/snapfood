package basePackage;

public class Order {
    private String foodName;
    private int numbersOfFood;
    private int priceOfFood;

    public Order(String foodName,int priceOfFood){
        this.foodName=foodName;
        this.priceOfFood=priceOfFood;

    }


    public Order(String foodName,int numbersOfFood,int priceOfFood){
        this.foodName=foodName;
        this.numbersOfFood=numbersOfFood;
        this.priceOfFood=priceOfFood;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setNumbersOfFood(int numbersOfFood) {
        this.numbersOfFood = numbersOfFood;
    }

    public void setPriceOfFood(int priceOfFood) {
        this.priceOfFood = priceOfFood;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public int getNumbersOfFood() {
        return this.numbersOfFood;
    }

    public int getPriceOfFood() {
        return this.priceOfFood;
    }
}
