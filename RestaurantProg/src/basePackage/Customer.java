package basePackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private long mobileNumber;
    private int codeArea;
    private String customerName;
    private long postalCode;
    String timeStamp;

    public Customer(long mobileNumber, int codeArea, String customerName, long postalCode){
        this.mobileNumber=mobileNumber;
        this.codeArea=codeArea;
        this.customerName=customerName;
        this.postalCode=postalCode;
        this.timeStamp=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCodeArea(int codeArea) {
        this.codeArea = codeArea;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPostalCode(long postalCode) {
        this.postalCode = postalCode;
    }

    public long getMobileNumber() {
        return this.mobileNumber;
    }

    public int getCodeArea() {
        return this.codeArea;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public long getPostalCode() {
        return this.postalCode;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }
}
