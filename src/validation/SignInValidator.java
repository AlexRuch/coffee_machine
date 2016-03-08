package validation;

import interaction.InteractionUsersDB;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by alexey on 06/03/16.
 */
@FacesValidator("signInValidator")
public class SignInValidator implements Validator {

    private InteractionUsersDB interactionUsersDB;
    private boolean userStatus;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String userEmail = (String) component.getAttributes().get("userEmail");
        String userPassword = (String) component.getAttributes().get("userPassword");

        interactionUsersDB = new InteractionUsersDB();
        userStatus = interactionUsersDB.checkUser(userEmail, userPassword);

        if (userPassword == null || userEmail == null){
            return;
        }
        if (!userStatus){
            throw new ValidatorException(new FacesMessage("Incorrect password or e-mail"));
        }
    }
}
