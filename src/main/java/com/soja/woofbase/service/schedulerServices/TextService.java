package com.soja.woofbase.service.schedulerServices;


import com.soja.woofbase.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.smsapi.BasicAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

@Service
public class TextService {

    private final TextCreatorService textCreatorService;


    @Autowired
    public TextService(TextCreatorService textCreatorService) {
        this.textCreatorService = textCreatorService;
    }

    public void sendTextAlert(User user) {

        try {
            String passwordHash = "00000000000000000000000000000000000";
            BasicAuthClient client = new BasicAuthClient("jarek.soja@gmail.com", passwordHash);
            //TODO implement variables in application.properties
            SmsFactory smsApi = new SmsFactory(client);
            String phoneNumber = user.getMobileNumber();
            SMSSend action = smsApi.actionSend()
                    .setText("")
                    .setTo(user.getMobileNumber());
                //TODO creating text body, in relation to alert genus
            StatusResponse result = action.execute();

            for (MessageResponse status : result.getList()) {
                System.out.println(status.getNumber() + " " + status.getStatus());
            }
        } catch (ClientException e) {
            /**
             * 101 Niepoprawne lub brak danych autoryzacji.
             * 102 Nieprawidłowy login lub hasło
             * 103 Brak punków dla tego użytkownika
             * 105 Błędny adres IP
             * 110 Usługa nie jest dostępna na danym koncie
             * 1000 Akcja dostępna tylko dla użytkownika głównego
             * 1001 Nieprawidłowa akcja
             */
            e.printStackTrace();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }
    }
}
