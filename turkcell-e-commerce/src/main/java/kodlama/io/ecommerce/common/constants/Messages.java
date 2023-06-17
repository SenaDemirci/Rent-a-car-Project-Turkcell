package kodlama.io.ecommerce.common.constants;

public class Messages {
    public static class Category{
        public static final String NotExists="CATEGORY_NOT_EXISTS";
        public static final String Exists = "CATEGORY_ALREADY_EXISTS";
    }
    public static class Product{
        public static final String NotExists="PRODUCT_NOT_EXISTS";
        public static final String DescriptionLengthValid="PRODUCT_DESCRIPTION_LENGTH_MUST_BETWEEN_10_AND_50_CHARACTERS";
        public static final String UnitPriceValid="UNIT_PRICE_MUST_BIGGER_THAN_ZERO";
        public static final String QuantityValid="QUANTITY_GREATER_THAN_ZERO";
    }
    public static class Sale{
        public static final String NotExists="SALE_NOT_EXISTS";
        public static final String ProductNotExists="PRODUCT_UNAVAILABLE";
        public static final String NotStock="PRODUCT_OU_OF_STOCK";

    }


    public static class Payment{
        public static final String NotExists="PAYMENT_NOT_EXISTS";
        public static final String CardNumberAlreadyExists = "CARD_NUMBER_ALREADY_EXISTS";
        public static final String Failed = "PAYMENT_FAILED";
        public static final String NotEnoughMoney = "NOT_ENOUGH_MONEY";
        public static final String NotAValidPayment = "NOT_A_VALID_PAYMENT";
    }
    public static class Invoice{
        public static final String NotExists="INVOICE_NOT_EXISTS";
    }
}
