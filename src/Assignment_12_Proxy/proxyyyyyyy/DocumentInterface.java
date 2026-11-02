package Assignment_12_Proxy.proxyyyyyyy;

import Assignment_12_Proxy.model.User;

import java.time.LocalDate;

public interface DocumentInterface {

    String getId();
    LocalDate getCreationDate();
    String getContent(User user);

}
