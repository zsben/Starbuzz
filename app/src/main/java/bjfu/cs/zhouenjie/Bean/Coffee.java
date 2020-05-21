package bjfu.cs.zhouenjie.home.Bean;

public class Coffee {
    private String name;
    private int ImageId;
    private double price;

    public Coffee(String name, int imageId, double price) {
        this.name = name;
        ImageId = imageId;
        this.price = price;
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
