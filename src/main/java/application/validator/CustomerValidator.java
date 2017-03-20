package application.validator;

import application.domain.Customer;

public class CustomerValidator implements IValidator<Customer> {

    @Override
    public void validate(Customer entity) throws ValidatorException {
        String msg="";

        if (entity.getName().equals("")) {
            msg += "Customer name is invalid!\n";
        }
        if (entity.getAccountId().equals("")) {
            msg += "Customer ID is invalid!\n";
        }
        //Check if there are any whitespaces
        if (!(entity.getAccountId().length() == entity.getAccountId().replaceAll("\\s+","").length())) {
            msg += "Whitespaces are not allowed in customer ID!\n";
        }
        if (entity.getBalance() < 0) {
            msg += "Customer balance shouldn't be negative!\n";
        }

        if (!msg.equals("")) {
            throw new ValidatorException(msg);
        }
    }

}
