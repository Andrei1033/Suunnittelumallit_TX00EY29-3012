package Assignment_12_Proxy.proxy;

import Assignment_12_Proxy.model.User;

import java.time.LocalDate;

public interface DocumentInterface {

    String getId();
    LocalDate getCreationDate();
    String getContent(User user);

}
