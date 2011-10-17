public class Ingredient {
    private String memberOf = null;
    private String type = null;
    private String quantity = null;
    private String quantityUnit = null;

    public Ingredient(String header, String ingredient, String quantity, String unit) {
        this.memberOf = header;
        this.type = ingredient;
        this.quantity = quantity;
        this.quantityUnit = unit;
    }

    public void setGroupHeader(String header) {
        this.memberOf = header;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setQuantityUnit(String unit) {
        this.quantityUnit = unit;
    }

    public String getGroupHeader() {
        return this.memberOf;
    }

    public String getType() {
        return this.type;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getQuantityUnit() {
        return this.quantityUnit;
    }

    public String getMemberOf() {
        return this.memberOf;
    }
}
