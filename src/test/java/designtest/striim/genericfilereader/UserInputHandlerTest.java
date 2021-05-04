package designtest.striim.genericfilereader;

import designtest.striim.genericfilereader.exception.DataReadException;
import designtest.striim.genericfilereader.exception.InvalidUserInputException;
import designtest.striim.genericfilereader.model.UserInput;
import designtest.striim.genericfilereader.util.Whitebox;
import designtest.striim.genericfilereader.validator.UserInputValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserInputHandlerTest {

    private static UserInputValidator inputValidator;
    private static UserInputHandler userInputHandler;

    @BeforeClass
    public static void init(){
        inputValidator = Mockito.mock(UserInputValidator.class);
        userInputHandler = Mockito.mock(UserInputHandler.class);
    }

    @Test
    public void handleUserInput() throws InvalidUserInputException, DataReadException {
        UserInput input = new UserInput();
        when(inputValidator.validate(input)).thenReturn(true);
        when(userInputHandler.handleUserInput(input)).thenReturn(true);

        Assert.assertTrue(userInputHandler.handleUserInput(input));
    }

    @Test(expected = InvalidUserInputException.class)
    public void handleUserInputInvalid() throws InvalidUserInputException, DataReadException {
        UserInput input = new UserInput();
        when(inputValidator.validate(input)).thenReturn(false);
        Whitebox.setInternalState(userInputHandler, "inputValidator", inputValidator);
        when(userInputHandler.handleUserInput(any())).thenCallRealMethod();

        Assert.assertFalse(userInputHandler.handleUserInput(input));
    }
}