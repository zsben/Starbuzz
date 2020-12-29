package bjfu.cs.zhouenjie.Bean;

import java.io.Serializable;

public class Coffee implements Serializable {
    private int id;
    private String name;
    private int ImageId;
    private double price;
    private int number = 1;
    private String desc;
    private boolean isPerfer = false;

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ImageId=" + ImageId +
                ", price=" + price +
                ", number=" + number +
                '}';
    }

    public boolean isPerfer() {
        return isPerfer;
    }

    public void setPerfer(boolean perfer) {
        isPerfer = perfer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Coffee(String name, int imageId, double price, int id, String desc) {
        this.id = id;
        this.name = name;
        ImageId = imageId;
        this.price = price;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
