package org.example.service;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("customRangeValidator")
public class CustomRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            double y = Double.parseDouble(value.toString());
            if (y < -3 || y > 5) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Validation Error", "Value must be between -3 and 5."));
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Validation Error", "Invalid number format."));
        }
    }
}
