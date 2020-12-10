package by.harevich.RestaurantApp.Request;

import javax.validation.constraints.NotNull;

public class UpdateFoodRequestModel {

    @NotNull(message = "Id cannot be null")
    private Integer id;

    private String image;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotNull(message = "Weight cannot be null")
    private Double weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
