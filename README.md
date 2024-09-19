RestController - CalculatePaymentController --> consists of the API method fetchPayableAmount which returns the final payable amount after calculating discounts.

DTO - CalculatePaymentRequest --> DTO class for receiving the payment request with parameters such as bill and user type.

Service - CalculateDiscountService (Interface and implementing class) --> The controller calls the service method getPayableAmount in the service class to fetch the final payable amount after calculating discounts.

Utility - DiscountCalculatorUtility --> The getPayableAmount method internally calls the utility methods defined in the utility class DiscountCalculatorUtility to carry out operations such as calculating the total amount, grocery based amount, non grocery based amount, user based discounts, bill based discounts etc. Below is a description of the methods and flow used.

Calculate total amount - call calculateTotal method of utility class.
Calculate grocery total - call calculateTotalByType of utility class.
Calculate non grocery total - subtract grocery total from total.
Get user based discount percentage (employee, affiliate etc) for calculating bill based discount amount - call getUserDiscount of utility class.
Revise non grocery final amount - call calculateDiscount method of utility class with right parameters.
Calculate bill based discount amount - call calculateBillDiscount method of utility class with the right parameters (total amount, discount per amount, discount amount)
Arrive at final amount - Add grocery amount to revised grocery amount and subtract bill based discount from that.
Return the final amount.

POJO classes --> Bill, Item, ItemCategory, User and UserCategory
